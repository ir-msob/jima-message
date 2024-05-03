package ir.msob.jima.message.commons.dto;

import ir.msob.jima.core.commons.model.dto.BaseDto;
import ir.msob.jima.message.commons.domain.BaseMessageSender;

import java.io.Serializable;

public interface BaseMessageSenderDto<ID extends Comparable<ID> & Serializable>
        extends BaseMessageSender<ID>, BaseDto<ID> {

}
