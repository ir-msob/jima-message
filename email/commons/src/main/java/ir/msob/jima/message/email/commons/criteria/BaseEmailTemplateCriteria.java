package ir.msob.jima.message.email.commons.criteria;

import ir.msob.jima.message.commons.criteria.BaseMessageTemplateCriteria;

import java.io.Serializable;

public interface BaseEmailTemplateCriteria<ID extends Comparable<ID> & Serializable> extends BaseMessageTemplateCriteria<ID> {
}
