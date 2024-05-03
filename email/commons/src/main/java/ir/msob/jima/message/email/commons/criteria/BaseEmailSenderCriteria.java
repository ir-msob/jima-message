package ir.msob.jima.message.email.commons.criteria;

import ir.msob.jima.message.commons.criteria.BaseMessageSenderCriteria;

import java.io.Serializable;

public interface BaseEmailSenderCriteria<ID extends Comparable<ID> & Serializable> extends BaseMessageSenderCriteria<ID> {
}
