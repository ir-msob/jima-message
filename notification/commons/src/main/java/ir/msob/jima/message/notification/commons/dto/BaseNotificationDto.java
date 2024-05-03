package ir.msob.jima.message.notification.commons.dto;

import ir.msob.jima.message.commons.dto.BaseMessageDto;
import ir.msob.jima.message.notification.commons.domain.BaseNotification;
import ir.msob.jima.message.notification.commons.domain.BaseNotificationLog;
import ir.msob.jima.message.notification.commons.domain.BaseNotificationReceiver;

import java.io.Serializable;

public interface BaseNotificationDto<ID extends Comparable<ID> & Serializable, ML extends BaseNotificationLog, Re extends BaseNotificationReceiver, MT extends BaseNotificationTemplateDto<ID, Re>>
        extends BaseNotification<ID, ML, Re, MT>
        , BaseMessageDto<ID, ML, Re, MT> {
}
