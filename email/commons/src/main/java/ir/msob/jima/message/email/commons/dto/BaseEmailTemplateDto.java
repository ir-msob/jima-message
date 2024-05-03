package ir.msob.jima.message.email.commons.dto;

import ir.msob.jima.message.commons.dto.BaseMessageTemplateDto;
import ir.msob.jima.message.email.commons.domain.BaseEmailReceiver;
import ir.msob.jima.message.email.commons.domain.BaseEmailTemplate;

import java.io.Serializable;

public interface BaseEmailTemplateDto<ID extends Comparable<ID> & Serializable, Re extends BaseEmailReceiver>
        extends BaseEmailTemplate<ID, Re>, BaseMessageTemplateDto<ID, Re> {

}
