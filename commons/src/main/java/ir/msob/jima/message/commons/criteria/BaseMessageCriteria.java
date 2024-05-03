package ir.msob.jima.message.commons.criteria;

import ir.msob.jima.core.commons.model.criteria.BaseCriteria;

import java.io.Serializable;

public interface BaseMessageCriteria<ID extends Comparable<ID> & Serializable> extends BaseCriteria<ID> {
}
