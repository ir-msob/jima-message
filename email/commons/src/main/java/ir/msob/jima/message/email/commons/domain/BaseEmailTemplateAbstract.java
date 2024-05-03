package ir.msob.jima.message.email.commons.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.message.commons.domain.BaseMessageTemplateAbstract;
import ir.msob.jima.message.email.commons.enumeration.EmailTemplateFileType;
import ir.msob.jima.message.email.commons.enumeration.EmailTemplateType;
import lombok.*;

import java.io.Serializable;
import java.util.Collection;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseEmailTemplateAbstract<ID extends Comparable<ID> & Serializable, Re extends BaseEmailReceiver>
        extends BaseMessageTemplateAbstract<ID, Re>
        implements BaseEmailTemplate<ID, Re> {
    private String subject;
    private EmailTemplateType emailTemplateType;
    private Collection<String> attachments;
    private String templateFile;
    private EmailTemplateFileType templateFileType;
    private String templateFileReplacementKey;

}
