package ir.msob.jima.message.email.commons.dto;

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
public abstract class BaseEmailSenderDtoAbstract<ID extends Comparable<ID> & Serializable>
        extends BaseMessageSenderDtoAbstract<ID>
        implements BaseEmailSenderDto<ID> {
}
