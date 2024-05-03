package ir.msob.jima.message.email.commons.domain;

import ir.msob.jima.message.commons.domain.BaseMessageTemplate;
import ir.msob.jima.message.email.commons.enumeration.EmailTemplateFileType;
import ir.msob.jima.message.email.commons.enumeration.EmailTemplateType;

import java.io.Serializable;
import java.util.Collection;

public interface BaseEmailTemplate<ID extends Comparable<ID> & Serializable, Re extends BaseEmailReceiver>
        extends BaseMessageTemplate<ID, Re> {

    String getSubject();

    void setSubject(String subject);

    EmailTemplateType getEmailTemplateType();

    void setEmailTemplateType(EmailTemplateType emailTemplateType);

    Collection<String> getAttachments();

    void setAttachments(Collection<String> attachments);

    String getTemplateFile();

    void setTemplateFile(String templateFile);

    EmailTemplateFileType getTemplateFileType();

    void setTemplateFileType(EmailTemplateFileType templateFileType);

    String getTemplateFileReplacementKey();

    void setTemplateFileReplacementKey(String templateFileReplacementKey);
}
