package ir.msob.jima.message.email.commons.dto;

import ir.msob.jima.message.commons.dto.BaseMessageDto;
import ir.msob.jima.message.email.commons.domain.BaseEmail;
import ir.msob.jima.message.email.commons.domain.BaseEmailLog;
import ir.msob.jima.message.email.commons.domain.BaseEmailReceiver;

import java.io.Serializable;

public interface BaseEmailDto<ID extends Comparable<ID> & Serializable, ML extends BaseEmailLog, Re extends BaseEmailReceiver, MT extends BaseEmailTemplateDto<ID, Re>>
        extends BaseEmail<ID, ML, Re, MT>, BaseMessageDto<ID, ML, Re, MT> {

}
