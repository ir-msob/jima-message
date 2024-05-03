package ir.msob.jima.message.commons.dto;

import ir.msob.jima.core.commons.model.dto.BaseDto;
import ir.msob.jima.message.commons.domain.BaseMessageReceiver;
import ir.msob.jima.message.commons.domain.BaseMessageTemplate;

import java.io.Serializable;

public interface BaseMessageTemplateDto<ID extends Comparable<ID> & Serializable, Re extends BaseMessageReceiver>
        extends BaseMessageTemplate<ID, Re>, BaseDto<ID> {

}
