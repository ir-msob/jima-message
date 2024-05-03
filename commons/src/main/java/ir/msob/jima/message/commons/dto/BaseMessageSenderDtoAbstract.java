package ir.msob.jima.message.commons.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.message.commons.domain.BaseMessageSenderAbstract;
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
public abstract class BaseMessageSenderDtoAbstract<ID extends Comparable<ID> & Serializable>
        extends BaseMessageSenderAbstract<ID>
        implements BaseMessageSenderDto<ID> {

}
