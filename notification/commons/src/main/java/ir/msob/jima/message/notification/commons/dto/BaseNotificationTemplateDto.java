package ir.msob.jima.message.notification.commons.dto;

import ir.msob.jima.message.commons.dto.BaseMessageTemplateDto;
import ir.msob.jima.message.notification.commons.domain.BaseNotificationReceiver;
import ir.msob.jima.message.notification.commons.domain.BaseNotificationTemplate;

import java.io.Serializable;

public interface BaseNotificationTemplateDto<ID extends Comparable<ID> & Serializable, Re extends BaseNotificationReceiver>
        extends BaseNotificationTemplate<ID, Re>, BaseMessageTemplateDto<ID, Re> {

}
