package ir.msob.jima.message.notification.commons.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.message.commons.domain.BaseMessageSenderAbstract;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseNotificationSenderAbstract<ID extends Comparable<ID> & Serializable>
        extends BaseMessageSenderAbstract<ID>
        implements BaseNotificationSender<ID> {
    private String channel;

}
