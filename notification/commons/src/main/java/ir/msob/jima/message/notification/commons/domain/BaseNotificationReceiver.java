package ir.msob.jima.message.notification.commons.domain;

import ir.msob.jima.message.commons.channel.ReceiverType;
import ir.msob.jima.message.commons.domain.BaseMessageReceiver;

public interface BaseNotificationReceiver extends BaseMessageReceiver {

    ReceiverType getReceiverType();

    void setReceiverType(ReceiverType receiverType);
}
