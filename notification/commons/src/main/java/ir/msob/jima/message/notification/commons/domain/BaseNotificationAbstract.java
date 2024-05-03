package ir.msob.jima.message.notification.commons.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.message.commons.domain.Attachment;
import ir.msob.jima.message.commons.domain.BaseMessageAbstract;
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
public abstract class BaseNotificationAbstract<ID extends Comparable<ID> & Serializable, ML extends BaseNotificationLog, Re extends BaseNotificationReceiver, MT extends BaseNotificationTemplate<ID, Re>>
        extends BaseMessageAbstract<ID, ML, Re, MT>
        implements BaseNotification<ID, ML, Re, MT> {
    private String subject;
    private Boolean guaranteedDelivery;
    private Collection<Attachment> attachments = new ArrayList<>();
}
