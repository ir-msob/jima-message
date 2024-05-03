package ir.msob.jima.message.commons.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.message.commons.domain.BaseMessageAbstract;
import ir.msob.jima.message.commons.domain.BaseMessageLog;
import ir.msob.jima.message.commons.domain.BaseMessageReceiver;
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
public abstract class BaseMessageDtoAbstract<ID extends Comparable<ID> & Serializable, ML extends BaseMessageLog, Re extends BaseMessageReceiver, MT extends BaseMessageTemplateDto<ID, Re>>
        extends BaseMessageAbstract<ID, ML, Re, MT>
        implements BaseMessageDto<ID, ML, Re, MT> {

}
