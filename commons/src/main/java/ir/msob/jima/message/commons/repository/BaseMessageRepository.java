package ir.msob.jima.message.commons.repository;

import ir.msob.jima.core.commons.data.BaseQuery;
import ir.msob.jima.core.commons.security.BaseUser;
import ir.msob.jima.crud.commons.BaseCrudRepository;
import ir.msob.jima.message.commons.criteria.BaseMessageCriteria;
import ir.msob.jima.message.commons.domain.BaseMessage;
import ir.msob.jima.message.commons.domain.BaseMessageLog;
import ir.msob.jima.message.commons.domain.BaseMessageReceiver;
import ir.msob.jima.message.commons.domain.BaseMessageTemplate;

import java.io.Serializable;

public interface BaseMessageRepository<
        ID extends Comparable<ID> & Serializable,
        USER extends BaseUser,
        ML extends BaseMessageLog,
        Re extends BaseMessageReceiver,
        MT extends BaseMessageTemplate<ID, Re>,
        D extends BaseMessage<ID, ML, Re, MT>,
        C extends BaseMessageCriteria<ID>,
        Q extends BaseQuery>
        extends BaseCrudRepository<ID, USER, D, C, Q> {
}
