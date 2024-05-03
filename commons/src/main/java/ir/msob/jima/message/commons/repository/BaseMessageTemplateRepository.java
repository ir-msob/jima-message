package ir.msob.jima.message.commons.repository;

import ir.msob.jima.core.commons.data.BaseQuery;
import ir.msob.jima.core.commons.security.BaseUser;
import ir.msob.jima.crud.commons.BaseCrudRepository;
import ir.msob.jima.message.commons.criteria.BaseMessageTemplateCriteria;
import ir.msob.jima.message.commons.domain.BaseMessageReceiver;
import ir.msob.jima.message.commons.domain.BaseMessageTemplate;

import java.io.Serializable;

public interface BaseMessageTemplateRepository<
        ID extends Comparable<ID> & Serializable,
        USER extends BaseUser,
        Re extends BaseMessageReceiver,
        D extends BaseMessageTemplate<ID, Re>,
        C extends BaseMessageTemplateCriteria<ID>,
        Q extends BaseQuery>
        extends BaseCrudRepository<ID, USER, D, C, Q> {
}
