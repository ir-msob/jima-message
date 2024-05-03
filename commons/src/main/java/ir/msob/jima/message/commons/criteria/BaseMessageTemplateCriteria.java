package ir.msob.jima.message.commons.criteria;

import ir.msob.jima.core.commons.model.criteria.BaseCriteria;
import ir.msob.jima.core.commons.model.criteria.filter.Filter;

import java.io.Serializable;

public interface BaseMessageTemplateCriteria<ID extends Comparable<ID> & Serializable> extends BaseCriteria<ID> {
    Filter<String> getKey();

    void setKey(Filter<String> key);
}
