package ir.msob.jima.message.email.commons.criteria;

import ir.msob.jima.message.commons.criteria.BaseMessageCriteria;

import java.io.Serializable;

public interface BaseEmailCriteria<ID extends Comparable<ID> & Serializable> extends BaseMessageCriteria<ID> {
}
