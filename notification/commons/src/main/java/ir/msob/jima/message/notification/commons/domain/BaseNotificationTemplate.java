package ir.msob.jima.message.notification.commons.domain;

import ir.msob.jima.message.commons.domain.Attachment;
import ir.msob.jima.message.commons.domain.BaseMessageTemplate;

import java.io.Serializable;
import java.util.Collection;

public interface BaseNotificationTemplate<ID extends Comparable<ID> & Serializable, Re extends BaseNotificationReceiver>
        extends BaseMessageTemplate<ID, Re> {

    String getSubject();

    void setSubject(String subject);

    Boolean getGuaranteedDelivery();

    void setGuaranteedDelivery(Boolean guaranteedDelivery);

    Collection<Attachment> getAttachments();

    void setAttachments(Collection<Attachment> attachments);

}
