package ir.msob.jima.message.notification.commons.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.message.commons.domain.Attachment;
import ir.msob.jima.message.commons.domain.BaseMessageTemplateAbstract;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseNotificationTemplateAbstract<ID extends Comparable<ID> & Serializable, Re extends BaseNotificationReceiver>
        extends BaseMessageTemplateAbstract<ID, Re>
        implements BaseNotificationTemplate<ID, Re> {
    private String subject;
    private Boolean guaranteedDelivery;
    private Collection<Attachment> attachments = new ArrayList<>();

}
