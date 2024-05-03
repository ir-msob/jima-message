package ir.msob.jima.message.commons.dto;

import ir.msob.jima.core.commons.model.dto.BaseDto;
import ir.msob.jima.message.commons.domain.BaseMessage;
import ir.msob.jima.message.commons.domain.BaseMessageLog;
import ir.msob.jima.message.commons.domain.BaseMessageReceiver;

import java.io.Serializable;

public interface BaseMessageDto<ID extends Comparable<ID> & Serializable, ML extends BaseMessageLog, Re extends BaseMessageReceiver, MT extends BaseMessageTemplateDto<ID, Re>>
        extends BaseMessage<ID, ML, Re, MT>, BaseDto<ID> {

}
