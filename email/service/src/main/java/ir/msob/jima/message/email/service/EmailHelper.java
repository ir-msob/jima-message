package ir.msob.jima.message.email.service;

import ir.msob.jima.core.commons.exception.runtime.CommonRuntimeException;
import ir.msob.jima.core.commons.file.BaseFileClient;
import ir.msob.jima.core.commons.security.BaseUserService;
import ir.msob.jima.message.commons.MessageHelper;
import ir.msob.jima.message.commons.enumeration.ContentType;
import ir.msob.jima.message.email.commons.domain.BaseEmail;
import ir.msob.jima.message.email.commons.domain.BaseEmailLog;
import ir.msob.jima.message.email.commons.domain.BaseEmailReceiver;
import ir.msob.jima.message.email.commons.domain.BaseEmailTemplate;
import ir.msob.jima.message.email.commons.enumeration.EmailReceiverType;
import ir.msob.jima.message.email.commons.enumeration.EmailTemplateType;
import jakarta.activation.DataHandler;
import jakarta.mail.BodyPart;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.util.ByteArrayDataSource;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class EmailHelper {
    private final BaseFileClient fileClient;
    private final BaseUserService baseUserService;

    public <ID extends Comparable<ID> & Serializable, ML extends BaseEmailLog, Re extends BaseEmailReceiver, MT extends BaseEmailTemplate<ID, Re>, M extends BaseEmail<ID, ML, Re, MT>> MimeMessageHelper buildMimeMessageHelper(M message, JavaMailSender javaMailSender) throws MessagingException, ExecutionException, InterruptedException, IOException {
        MimeMessageHelper mimeMessageHelper = buildMessageContent(javaMailSender, message);
        buildMessageSubject(mimeMessageHelper, message);
        buildMessageAttachment(mimeMessageHelper, message);
        addReceivers(mimeMessageHelper, message);
        return mimeMessageHelper;
    }


    private <ID extends Comparable<ID> & Serializable, ML extends BaseEmailLog, Re extends BaseEmailReceiver, MT extends BaseEmailTemplate<ID, Re>, M extends BaseEmail<ID, ML, Re, MT>> void addReceivers(MimeMessageHelper mimeMessageHelper, M message) throws MessagingException {
        if (message.getMessageTemplate() != null)
            for (Re receiver : message.getMessageTemplate().getReceivers()) {
                addReceiver(mimeMessageHelper, receiver);
            }
        for (Re receiver : message.getReceivers()) {
            addReceiver(mimeMessageHelper, receiver);
        }
    }


    private <Re extends BaseEmailReceiver> void addReceiver(MimeMessageHelper mimeMessageHelper, Re receiver) throws MessagingException {
        if (receiver.getEmailReceiverType() == EmailReceiverType.TO)
            for (String r : receiver.getReceivers())
                mimeMessageHelper.setTo(r);
        else if (receiver.getEmailReceiverType() == EmailReceiverType.CC)
            for (String r : receiver.getReceivers())
                mimeMessageHelper.setCc(r);
        else if (receiver.getEmailReceiverType() == EmailReceiverType.BCC)
            for (String r : receiver.getReceivers())
                mimeMessageHelper.setBcc(r);
    }

    private <ID extends Comparable<ID> & Serializable, ML extends BaseEmailLog, Re extends BaseEmailReceiver, MT extends BaseEmailTemplate<ID, Re>, M extends BaseEmail<ID, ML, Re, MT>> void buildMessageAttachment(MimeMessageHelper mimeMessageHelper, M message) throws MessagingException, ExecutionException, InterruptedException, IOException {
        if (message.getMessageTemplate() != null)
            for (String filePath : message.getMessageTemplate().getAttachments()) {
                addAttachment(mimeMessageHelper, filePath);
            }
        for (String filePath : message.getAttachments()) {
            addAttachment(mimeMessageHelper, filePath);
        }
    }

    private void addAttachment(MimeMessageHelper mimeMessageHelper, String filePath) throws MessagingException, ExecutionException, InterruptedException, IOException {
        MimeBodyPart mimeBodyPart = prepareMimeBodyPartOfAttachment(filePath);
        MimeMultipart mimeMultipart = (MimeMultipart) mimeMessageHelper.getMimeMessage().getContent();
        mimeMultipart.addBodyPart(mimeBodyPart);
    }

    private <ID extends Comparable<ID> & Serializable, Re extends BaseEmailReceiver, MT extends BaseEmailTemplate<ID, Re>> MimeBodyPart prepareMimeBodyPartOfAttachment(String filePath) throws ExecutionException, InterruptedException, IOException, MessagingException {
        String filename = filePath.substring(filePath.lastIndexOf("/") + 1);
        InputStream inputStream = fileClient.get(filePath, baseUserService.getSystemUser())
                .toFuture()
                .get();

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setDataHandler(new DataHandler(new ByteArrayDataSource(inputStream, "application/octet-stream")));
        mimeBodyPart.setFileName(filename);
        return mimeBodyPart;
    }

    private <ID extends Comparable<ID> & Serializable, Re extends BaseEmailReceiver, MT extends BaseEmailTemplate<ID, Re>> MimeMessageHelper prepareHelperWithTemplateFile(JavaMailSender javaMailSender, MT template) throws ExecutionException, InterruptedException, MessagingException {
        InputStream inputStream = fileClient.get(template.getTemplateFile(), baseUserService.getSystemUser())
                .toFuture()
                .get();
        MimeMessage mimeMessage = javaMailSender.createMimeMessage(inputStream);
        return newMimeMessageHelper(mimeMessage);
    }

    private MimeMessageHelper newMimeMessageHelper(MimeMessage mimeMessage) throws MessagingException {
        return new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());
    }

    private <ID extends Comparable<ID> & Serializable, Re extends BaseEmailReceiver, MT extends BaseEmailTemplate<ID, Re>> MimeMessageHelper prepareHelperWithContentAndTemplateFile(JavaMailSender javaMailSender, MT template, Object content, ContentType contentType) throws ExecutionException, InterruptedException, IOException, MessagingException {
        MimeMessageHelper mimeMessageHelper = prepareHelperWithTemplateFile(javaMailSender, template);
        if (content instanceof String
                && (contentType == ContentType.HTML || contentType == ContentType.TEXT))
            replaceMimeMessageContent(mimeMessageHelper.getMimeMessage().getContent(), template.getTemplateFileReplacementKey(), String.valueOf(content));
        return mimeMessageHelper;
    }

    private void replaceMimeMessageContent(Object content, String regex, String replacement) throws IOException, MessagingException {
        if (content.getClass().isAssignableFrom(MimeMultipart.class)) {
            MimeMultipart mimeMultipart = (MimeMultipart) content;
            for (int i = 0; i < mimeMultipart.getCount(); i++) {
                BodyPart bodyPart = mimeMultipart.getBodyPart(i);
                if (bodyPart.getContentType().startsWith("multipart/")) {
                    replaceMimeMessageContent(bodyPart.getContent(), regex, replacement);
                } else if (bodyPart.getContentType().startsWith("text/plain")) {
                    bodyPart.setContent(replaceString(String.valueOf(bodyPart.getContent()), regex, replacement), bodyPart.getContentType());
                } else if (bodyPart.getContentType().startsWith("text/html")) {
                    bodyPart.setContent(replaceString(String.valueOf(bodyPart.getContent()), regex, replacement), bodyPart.getContentType());
                }
            }
        }
    }

    private String replaceString(String s, String regex, String replacement) {
        if (Strings.isNotBlank(s))
            return s.replaceAll(regex, replacement);
        return s;
    }


    private <ID extends Comparable<ID> & Serializable, ML extends BaseEmailLog, Re extends BaseEmailReceiver, MT extends BaseEmailTemplate<ID, Re>, M extends BaseEmail<ID, ML, Re, MT>> String prepareSubject(M message) {
        String result = null;
        if (message.getMessageTemplate() != null) {
            result = message.getMessageTemplate().getSubject();
        }
        if (Strings.isNotBlank(message.getSubject())) {
            result = message.getSubject();
        }
        if (result == null)
            result = "";
        result = MessageHelper.setParams(message, result);
        return result;
    }

    private <ID extends Comparable<ID> & Serializable, ML extends BaseEmailLog, Re extends BaseEmailReceiver, MT extends BaseEmailTemplate<ID, Re>, M extends BaseEmail<ID, ML, Re, MT>> void buildMessageSubject(MimeMessageHelper mimeMessageHelper, M message) throws MessagingException {
        String subject = prepareSubject(message);
        mimeMessageHelper.setSubject(subject);
    }

    private <ID extends Comparable<ID> & Serializable, ML extends BaseEmailLog, Re extends BaseEmailReceiver, MT extends BaseEmailTemplate<ID, Re>, M extends BaseEmail<ID, ML, Re, MT>> MimeMessageHelper buildMessageContent(JavaMailSender javaMailSender, M message) throws MessagingException, ExecutionException, InterruptedException, IOException {
        if (message.getMessageTemplate() != null) {
            return prepareMessageContentWithTemplate(javaMailSender, message);
        } else {
            return prepareMessageContentWithMessage(javaMailSender, message);
        }
    }

    private <ID extends Comparable<ID> & Serializable, ML extends BaseEmailLog, Re extends BaseEmailReceiver, MT extends BaseEmailTemplate<ID, Re>, M extends BaseEmail<ID, ML, Re, MT>> MimeMessageHelper prepareMessageContentWithMessage(JavaMailSender javaMailSender, M message) throws MessagingException {
        Object content = MessageHelper.prepareContent(message, message.getContentType());
        return prepareHelperWithContent(javaMailSender, content, message.getContentType());
    }

    private <ID extends Comparable<ID> & Serializable, ML extends BaseEmailLog, Re extends BaseEmailReceiver, MT extends BaseEmailTemplate<ID, Re>, M extends BaseEmail<ID, ML, Re, MT>> MimeMessageHelper prepareHelperWithContent(JavaMailSender javaMailSender, Object content, ContentType contentType) throws MessagingException {
        jakarta.mail.internet.MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = newMimeMessageHelper(mimeMessage);
        if (contentType == ContentType.TEXT)
            helper.setText(String.valueOf(content));
        else if (contentType == ContentType.HTML)
            helper.setText(String.valueOf(content), true);
        else
            throw new CommonRuntimeException("Can not detect ContentType.");
        return helper;
    }

    private <ID extends Comparable<ID> & Serializable, ML extends BaseEmailLog, Re extends BaseEmailReceiver, MT extends BaseEmailTemplate<ID, Re>, M extends BaseEmail<ID, ML, Re, MT>> MimeMessageHelper prepareMessageContentWithTemplate(JavaMailSender javaMailSender, M message) throws MessagingException, ExecutionException, InterruptedException, IOException {
        if (message.getMessageTemplate().getEmailTemplateType() == EmailTemplateType.CONTENT) {
            ContentType contentType = MessageHelper.prepareContentType(message);
            Object content = MessageHelper.prepareContent(message, contentType);
            return prepareHelperWithContent(javaMailSender, content, contentType);
        } else if (message.getMessageTemplate().getEmailTemplateType() == EmailTemplateType.CONTENT_AND_TEMPLATE_FILE) {
            ContentType contentType = MessageHelper.prepareContentType(message);
            Object content = MessageHelper.prepareContent(message, contentType);
            return prepareHelperWithContentAndTemplateFile(javaMailSender, message.getMessageTemplate(), content, contentType);
        } else if (message.getMessageTemplate().getEmailTemplateType() == EmailTemplateType.TEMPLATE_FILE) {
            return prepareHelperWithTemplateFile(javaMailSender, message.getMessageTemplate());
        } else {
            throw new CommonRuntimeException("Can not detect EmailMessageTemplateType, templateId :{}", message.getMessageTemplate().getDomainId());
        }
    }


}
