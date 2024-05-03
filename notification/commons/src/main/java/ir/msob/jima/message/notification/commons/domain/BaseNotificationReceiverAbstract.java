package ir.msob.jima.message.notification.commons.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.message.commons.channel.ReceiverType;
import ir.msob.jima.message.commons.domain.BaseMessageReceiverAbstract;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseNotificationReceiverAbstract extends BaseMessageReceiverAbstract
        implements BaseNotificationReceiver {
    private ReceiverType receiverType = ReceiverType.USER;
}
