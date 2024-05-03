package ir.msob.jima.message.commons.service;

import ir.msob.jima.core.commons.data.BaseQuery;
import ir.msob.jima.core.commons.security.BaseUser;
import ir.msob.jima.crud.service.BaseCrudService;
import ir.msob.jima.message.commons.criteria.BaseMessageTemplateCriteria;
import ir.msob.jima.message.commons.domain.BaseMessageReceiver;
import ir.msob.jima.message.commons.domain.BaseMessageTemplate;
import ir.msob.jima.message.commons.dto.BaseMessageTemplateDto;
import ir.msob.jima.message.commons.repository.BaseMessageTemplateRepository;

import java.io.Serializable;

public interface BaseMessageTemplateService<
        ID extends Comparable<ID> & Serializable,
        USER extends BaseUser,
        Re extends BaseMessageReceiver,
        D extends BaseMessageTemplate<ID, Re>,
        DTO extends BaseMessageTemplateDto<ID, Re>,
        C extends BaseMessageTemplateCriteria<ID>,
        Q extends BaseQuery,
        R extends BaseMessageTemplateRepository<ID, USER, Re, D, C, Q>
        > extends BaseCrudService<ID, USER, D, DTO, C, Q, R> {
}
