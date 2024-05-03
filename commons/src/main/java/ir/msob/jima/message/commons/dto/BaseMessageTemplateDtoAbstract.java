package ir.msob.jima.message.commons.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.message.commons.domain.BaseMessageReceiver;
import ir.msob.jima.message.commons.domain.BaseMessageTemplateAbstract;
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
public abstract class BaseMessageTemplateDtoAbstract<ID extends Comparable<ID> & Serializable, Re extends BaseMessageReceiver>
        extends BaseMessageTemplateAbstract<ID, Re>
        implements BaseMessageTemplateDto<ID, Re> {

}
