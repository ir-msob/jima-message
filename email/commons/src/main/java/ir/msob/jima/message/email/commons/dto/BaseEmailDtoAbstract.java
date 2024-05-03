package ir.msob.jima.message.email.commons.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.message.commons.dto.BaseMessageDtoAbstract;
import ir.msob.jima.message.email.commons.domain.BaseEmailLog;
import ir.msob.jima.message.email.commons.domain.BaseEmailReceiver;
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
public abstract class BaseEmailDtoAbstract<ID extends Comparable<ID> & Serializable, ML extends BaseEmailLog, Re extends BaseEmailReceiver, MT extends BaseEmailTemplateDto<ID, Re>>
        extends BaseMessageDtoAbstract<ID, ML, Re, MT>
        implements BaseEmailDto<ID, ML, Re, MT> {
}
