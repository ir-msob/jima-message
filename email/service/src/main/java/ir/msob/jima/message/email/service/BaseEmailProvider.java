package ir.msob.jima.message.email.service;

import ir.msob.jima.core.commons.annotation.methodstats.MethodStats;
import ir.msob.jima.core.commons.data.BaseQuery;
import ir.msob.jima.core.commons.exception.runtime.CommonRuntimeException;
import ir.msob.jima.core.commons.model.keyvalue.KeyValue;
import ir.msob.jima.core.commons.security.BaseUser;
import ir.msob.jima.message.commons.BaseMessageProvider;
import ir.msob.jima.message.commons.domain.BaseMessageSender;
import ir.msob.jima.message.commons.domain.SendResult;
import ir.msob.jima.message.commons.repository.BaseMessageSenderRepository;
import ir.msob.jima.message.commons.service.BaseMessageSenderService;
import ir.msob.jima.message.email.commons.criteria.BaseEmailSenderCriteria;
import ir.msob.jima.message.email.commons.domain.BaseEmailLog;
import ir.msob.jima.message.email.commons.domain.BaseEmailReceiver;
import ir.msob.jima.message.email.commons.domain.BaseEmailSender;
import ir.msob.jima.message.email.commons.dto.BaseEmailDto;
import ir.msob.jima.message.email.commons.dto.BaseEmailSenderDto;
import ir.msob.jima.message.email.commons.dto.BaseEmailTemplateDto;
import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.io.Serializable;
import java.time.Instant;
import java.util.*;

@CommonsLog
public abstract class BaseEmailProvider<
        ID extends Comparable<ID> & Serializable,
        USER extends BaseUser,
        ML extends BaseEmailLog,
        Re extends BaseEmailReceiver,
        Q extends BaseQuery,
        MeTeDTO extends BaseEmailTemplateDto<ID, Re>,
        MeDTO extends BaseEmailDto<ID, ML, Re, MeTeDTO>,

        MeSe extends BaseEmailSender<ID>,
        MeSeDTO extends BaseEmailSenderDto<ID>,
        MeSeC extends BaseEmailSenderCriteria<ID>
        > implements BaseMessageProvider<ID, ML, Re, MeTeDTO, MeDTO> {

    private final Map<ID, JavaMailSender> sources = new HashMap<>();
    private final Collection<MeSeDTO> senders = new ArrayList<>();
    @Autowired
    BaseMessageSenderService<ID, USER, MeSe, MeSeDTO, MeSeC, Q, ? extends BaseMessageSenderRepository<ID, USER, MeSe, MeSeC, Q>> senderService;
    private JavaMailSender defaultSource;
    @Autowired
    private EmailHelper emailHelper;

    @SneakyThrows
    @MethodStats
    @Override
    public SendResult<ML> send(MeDTO message) {
        SendResult<ML> result = new SendResult<>();
        try {
            JavaMailSender javaMailSender = prepareJavaMailSender(message);
            MimeMessageHelper mimeMessageHelper = emailHelper.buildMimeMessageHelper(message, javaMailSender);
            setSenderFromMessage(mimeMessageHelper, message);
            javaMailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (InterruptedException e) {
            log.error(e);
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            log.error(e);
            result.setFailed(true);
            ML log = getMessageLogClass().getConstructor().newInstance();
            log.setLogDate(Instant.now());
            log.setLog(e.getMessage());
            result.setLog(log);
        }
        return result;
    }


    private void setSenderFromMessage(MimeMessageHelper mimeMessageHelper, MeDTO message) throws jakarta.mail.MessagingException {
        MeSeDTO sender;
        if (message.getMessageTemplate() != null && message.getMessageTemplate().getSenderId() != null) {
            sender = senders
                    .stream()
                    .filter(s -> s.equals(message.getMessageTemplate().getSenderId()))
                    .findFirst()
                    .orElseThrow(() -> new CommonRuntimeException("Can not found sender of senderId {}", message.getMessageTemplate().getSenderId()));
        } else {
            sender = senders
                    .stream()
                    .filter(BaseMessageSender::isDefaultSource)
                    .findFirst()
                    .orElseThrow(() -> new CommonRuntimeException("Can not found sender."));
        }
        if (sender == null) {
            throw new CommonRuntimeException("Can not found sender.");
        }
        mimeMessageHelper.setFrom(sender.getUsername());
    }


    @PostConstruct
    private void startup() {
        senderService.getEnabledList()
                .subscribe(sender -> {
                    senders.add(sender);
                    JavaMailSender javaMailSender = prepareJavaMailSender(sender);
                    sources.put(sender.getDomainId(), javaMailSender);
                    if (sender.isDefaultSource())
                        defaultSource = javaMailSender;
                });
    }

    private JavaMailSender prepareJavaMailSender(MeSeDTO sender) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(sender.getHost());
        mailSender.setPort(sender.getPort());

        mailSender.setUsername(sender.getUsername());
        mailSender.setPassword(sender.getPassword());

        if (sender.getProps() != null) {
            Properties props = mailSender.getJavaMailProperties();
            for (KeyValue<String, Serializable> entry : sender.getProps())
                props.put(entry.getKey(), entry.getValue());
        }

        return mailSender;
    }

    private JavaMailSender prepareJavaMailSender(MeDTO message) {
        JavaMailSender result;
        if (message.getMessageTemplate() == null || message.getMessageTemplate().getSenderId() == null)
            result = defaultSource;
        else
            result = sources.get(message.getMessageTemplate().getSenderId());

        if (result == null)
            throw new CommonRuntimeException("Can not found any sender for email");
        return result;
    }
}
