package ir.msob.jima.message.commons.repository;

import ir.msob.jima.core.commons.data.BaseQuery;
import ir.msob.jima.core.commons.security.BaseUser;
import ir.msob.jima.crud.commons.BaseCrudRepository;
import ir.msob.jima.message.commons.criteria.BaseMessageSenderCriteria;
import ir.msob.jima.message.commons.domain.BaseMessageSender;

import java.io.Serializable;

public interface BaseMessageSenderRepository<
        ID extends Comparable<ID> & Serializable,
        USER extends BaseUser,
        D extends BaseMessageSender<ID>,
        C extends BaseMessageSenderCriteria<ID>,
        Q extends BaseQuery>
        extends BaseCrudRepository<ID, USER, D, C, Q> {
}
