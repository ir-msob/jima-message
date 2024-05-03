package ir.msob.jima.message.commons.service;

import ir.msob.jima.core.commons.annotation.methodstats.MethodStats;
import ir.msob.jima.core.commons.data.BaseQuery;
import ir.msob.jima.core.commons.security.BaseUser;
import ir.msob.jima.crud.service.BaseCrudService;
import ir.msob.jima.message.commons.criteria.BaseMessageSenderCriteria;
import ir.msob.jima.message.commons.domain.BaseMessageSender;
import ir.msob.jima.message.commons.dto.BaseMessageSenderDto;
import ir.msob.jima.message.commons.repository.BaseMessageSenderRepository;
import reactor.core.publisher.Flux;

import java.io.Serializable;

public interface BaseMessageSenderService<
        ID extends Comparable<ID> & Serializable,
        USER extends BaseUser,
        D extends BaseMessageSender<ID>,
        DTO extends BaseMessageSenderDto<ID>,
        C extends BaseMessageSenderCriteria<ID>,
        Q extends BaseQuery,
        R extends BaseMessageSenderRepository<ID, USER, D, C, Q>>
        extends BaseCrudService<ID, USER, D, DTO, C, Q, R> {

    @MethodStats
    Flux<DTO> getEnabledList();

}
