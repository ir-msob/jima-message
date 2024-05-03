package ir.msob.jima.message.email.commons.domain;

import ir.msob.jima.message.commons.domain.BaseMessageReceiver;
import ir.msob.jima.message.email.commons.enumeration.EmailReceiverType;

public interface BaseEmailReceiver extends BaseMessageReceiver {

    EmailReceiverType getEmailReceiverType();

    void setEmailReceiverType(EmailReceiverType emailReceiverType);
}
