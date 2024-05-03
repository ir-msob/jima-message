package ir.msob.jima.message.notification.commons.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.message.commons.dto.BaseMessageSenderDtoAbstract;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseNotificationSenderDtoAbstract<ID extends Comparable<ID> & Serializable>
        extends BaseMessageSenderDtoAbstract<ID>
        implements BaseNotificationSenderDto<ID> {
}
