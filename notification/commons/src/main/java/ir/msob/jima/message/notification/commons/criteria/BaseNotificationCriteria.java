package ir.msob.jima.message.notification.commons.criteria;

import ir.msob.jima.message.commons.criteria.BaseMessageCriteria;

import java.io.Serializable;

public interface BaseNotificationCriteria<ID extends Comparable<ID> & Serializable> extends BaseMessageCriteria<ID> {
}
