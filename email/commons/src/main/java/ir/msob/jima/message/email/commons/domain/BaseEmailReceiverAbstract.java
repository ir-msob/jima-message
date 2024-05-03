package ir.msob.jima.message.email.commons.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.message.commons.domain.BaseMessageReceiverAbstract;
import ir.msob.jima.message.email.commons.enumeration.EmailReceiverType;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseEmailReceiverAbstract extends BaseMessageReceiverAbstract
        implements BaseEmailReceiver {
    private EmailReceiverType emailReceiverType = EmailReceiverType.TO;
}
