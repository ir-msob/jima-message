package ir.msob.jima.message.notification.commons.criteria;

import ir.msob.jima.message.commons.criteria.BaseMessageTemplateCriteria;

import java.io.Serializable;

public interface BaseNotificationTemplateCriteria<ID extends Comparable<ID> & Serializable> extends BaseMessageTemplateCriteria<ID> {
}
