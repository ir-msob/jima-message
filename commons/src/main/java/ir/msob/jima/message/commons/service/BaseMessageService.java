package ir.msob.jima.message.commons.service;

import ir.msob.jima.core.commons.annotation.methodstats.MethodStats;
import ir.msob.jima.core.commons.data.BaseQuery;
import ir.msob.jima.core.commons.security.BaseUser;
import ir.msob.jima.crud.service.BaseCrudService;
import ir.msob.jima.message.commons.criteria.BaseMessageCriteria;
import ir.msob.jima.message.commons.domain.BaseMessage;
import ir.msob.jima.message.commons.domain.BaseMessageLog;
import ir.msob.jima.message.commons.domain.BaseMessageReceiver;
import ir.msob.jima.message.commons.domain.BaseMessageTemplate;
import ir.msob.jima.message.commons.dto.BaseMessageDto;
import ir.msob.jima.message.commons.dto.BaseMessageTemplateDto;
import ir.msob.jima.message.commons.repository.BaseMessageRepository;
import reactor.core.publisher.Flux;

import java.io.Serializable;

public interface BaseMessageService<
        ID extends Comparable<ID> & Serializable,
        USER extends BaseUser,
        ML extends BaseMessageLog,
        Re extends BaseMessageReceiver,
        MT extends BaseMessageTemplate<ID, Re>,
        D extends BaseMessage<ID, ML, Re, MT>,
        MT_DTO extends BaseMessageTemplateDto<ID, Re>,
        DTO extends BaseMessageDto<ID, ML, Re, MT_DTO>,
        C extends BaseMessageCriteria<ID>,
        Q extends BaseQuery,
        R extends BaseMessageRepository<ID, USER, ML, Re, MT, D, C, Q>
        > extends BaseCrudService<ID, USER, D, DTO, C, Q, R> {

    @MethodStats
    Flux<DTO> getCandidateList(int chunkSize);
}
