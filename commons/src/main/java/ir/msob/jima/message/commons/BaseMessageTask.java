package ir.msob.jima.message.commons;

import ir.msob.jima.core.beans.properties.JimaProperties;
import ir.msob.jima.core.commons.annotation.methodstats.MethodStats;
import ir.msob.jima.core.commons.data.BaseQuery;
import ir.msob.jima.core.commons.exception.badrequest.BadRequestException;
import ir.msob.jima.core.commons.exception.domainnotfound.DomainNotFoundException;
import ir.msob.jima.core.commons.security.BaseUser;
import ir.msob.jima.core.commons.security.BaseUserService;
import ir.msob.jima.core.commons.util.GenericTypeUtil;
import ir.msob.jima.message.commons.criteria.BaseMessageCriteria;
import ir.msob.jima.message.commons.criteria.BaseMessageSenderCriteria;
import ir.msob.jima.message.commons.criteria.BaseMessageTemplateCriteria;
import ir.msob.jima.message.commons.domain.*;
import ir.msob.jima.message.commons.dto.BaseMessageDto;
import ir.msob.jima.message.commons.dto.BaseMessageSenderDto;
import ir.msob.jima.message.commons.dto.BaseMessageTemplateDto;
import ir.msob.jima.message.commons.enumeration.MessageStatus;
import ir.msob.jima.message.commons.repository.BaseMessageRepository;
import ir.msob.jima.message.commons.repository.BaseMessageSenderRepository;
import ir.msob.jima.message.commons.repository.BaseMessageTemplateRepository;
import ir.msob.jima.message.commons.service.BaseMessageSenderService;
import ir.msob.jima.message.commons.service.BaseMessageService;
import ir.msob.jima.message.commons.service.BaseMessageTemplateService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.time.Instant;
import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

public abstract class BaseMessageTask<
        ID extends Comparable<ID> & Serializable,
        USER extends BaseUser,
        ML extends BaseMessageLog,
        Re extends BaseMessageReceiver,
        Q extends BaseQuery,

        MeTe extends BaseMessageTemplate<ID, Re>,
        MeTeDTO extends BaseMessageTemplateDto<ID, Re>,
        MeTeC extends BaseMessageTemplateCriteria<ID>,

        Me extends BaseMessage<ID, ML, Re, MeTe>,
        MeDTO extends BaseMessageDto<ID, ML, Re, MeTeDTO>,
        MeC extends BaseMessageCriteria<ID>,

        MeSe extends BaseMessageSender<ID>,
        MeSeDTO extends BaseMessageSenderDto<ID>,
        MeSeC extends BaseMessageSenderCriteria<ID>
        > implements Runnable {

    @Autowired
    protected BaseMessageService<ID, USER, ML, Re, MeTe, Me, MeTeDTO, MeDTO, MeC, Q, ? extends BaseMessageRepository<ID, USER, ML, Re, MeTe, Me, MeC, Q>> messageService;
    @Autowired
    protected BaseMessageTemplateService<ID, USER, Re, MeTe, MeTeDTO, MeTeC, Q, ? extends BaseMessageTemplateRepository<ID, USER, Re, MeTe, MeTeC, Q>> messageTemplateService;
    @Autowired
    protected BaseMessageSenderService<ID, USER, MeSe, MeSeDTO, MeSeC, Q, ? extends BaseMessageSenderRepository<ID, USER, MeSe, MeSeC, Q>> messageSenderService;
    @Autowired
    protected BaseMessageProvider<ID, ML, Re, MeTeDTO, MeDTO> messageProvider;
    @Autowired
    private BaseUserService baseUserService;
    @Autowired
    private JimaProperties jimaProperties;

    protected Class<MeTeC> getMeTeCriteriaClass() {
        return (Class<MeTeC>) GenericTypeUtil.resolveTypeArguments(getClass(), BaseMessageTask.class, 5);
    }


    @SneakyThrows
    @MethodStats
    @Override
    public void run() {
        execute();
    }

    private void execute() throws ExecutionException, InterruptedException, BadRequestException, DomainNotFoundException {
        Collection<MeDTO> messages = getMessages();
        if (messages.isEmpty()) return;
        sendMessages(messages);
        updateMessages(messages);
    }

    private void updateMessages(Collection<MeDTO> messages) throws DomainNotFoundException, BadRequestException, ExecutionException, InterruptedException {
        messageService.updateMany(messages, baseUserService.getSystemUser())
                .toFuture()
                .get();
    }

    private void sendMessages(Collection<MeDTO> messages) {
        for (MeDTO message : messages) {
            SendResult<ML> sendResult = messageProvider.send(message);
            prepareMessage(message, sendResult);
        }
    }

    private void prepareMessage(MeDTO message, SendResult<ML> sendResult) {
        if (sendResult.isFailed()) {
            message.getMessageLogs().add(sendResult.getLog());
            message.setMessageStatus(MessageStatus.FAILED);
        } else {
            message.setMessageStatus(MessageStatus.COMPLETED);
        }
        message.setTryTimes(message.getTryTimes() + 1);
        message.setStatusDate(Instant.now());
    }

    private Collection<MeDTO> getMessages() throws ExecutionException, InterruptedException {
        Collection<MeDTO> result = messageService.getCandidateList(jimaProperties.getMessage().getChunkSize())
                .collectList()
                .toFuture()
                .get();
        result.removeIf(Objects::isNull);
        return result;
    }

}
