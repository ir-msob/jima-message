package ir.msob.jima.message.notification.commons.criteria;

import ir.msob.jima.message.commons.criteria.BaseMessageSenderCriteria;

import java.io.Serializable;

public interface BaseNotificationSenderCriteria<ID extends Comparable<ID> & Serializable> extends BaseMessageSenderCriteria<ID> {
}
