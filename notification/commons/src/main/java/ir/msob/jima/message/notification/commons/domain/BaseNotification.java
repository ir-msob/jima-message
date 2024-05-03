package ir.msob.jima.message.notification.commons.domain;

import ir.msob.jima.message.commons.domain.Attachment;
import ir.msob.jima.message.commons.domain.BaseMessage;

import java.io.Serializable;
import java.util.Collection;

public interface BaseNotification<ID extends Comparable<ID> & Serializable, ML extends BaseNotificationLog, Re extends BaseNotificationReceiver, MT extends BaseNotificationTemplate<ID, Re>>
        extends BaseMessage<ID, ML, Re, MT> {

    String getSubject();

    void setSubject(String subject);

    Boolean getGuaranteedDelivery();

    void setGuaranteedDelivery(Boolean guaranteedDelivery);

    Collection<Attachment> getAttachments();

    void setAttachments(Collection<Attachment> attachments);
}
