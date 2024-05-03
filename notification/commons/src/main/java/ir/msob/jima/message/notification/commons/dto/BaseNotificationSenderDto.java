package ir.msob.jima.message.notification.commons.dto;

import ir.msob.jima.message.commons.dto.BaseMessageSenderDto;
import ir.msob.jima.message.notification.commons.domain.BaseNotificationSender;

import java.io.Serializable;

public interface BaseNotificationSenderDto<ID extends Comparable<ID> & Serializable>
        extends BaseNotificationSender<ID>, BaseMessageSenderDto<ID> {

}
