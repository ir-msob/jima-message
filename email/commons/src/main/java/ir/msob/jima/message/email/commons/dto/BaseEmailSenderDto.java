package ir.msob.jima.message.email.commons.dto;

import ir.msob.jima.message.commons.dto.BaseMessageSenderDto;
import ir.msob.jima.message.email.commons.domain.BaseEmailSender;

import java.io.Serializable;

public interface BaseEmailSenderDto<ID extends Comparable<ID> & Serializable>
        extends BaseEmailSender<ID>, BaseMessageSenderDto<ID> {

}
