package ir.msob.jima.message.notification.commons.domain;

import ir.msob.jima.message.commons.domain.BaseMessageSender;

import java.io.Serializable;

public interface BaseNotificationSender<ID extends Comparable<ID> & Serializable> extends BaseMessageSender<ID> {

    String getChannel();

    void setChannel(String channel);
}
