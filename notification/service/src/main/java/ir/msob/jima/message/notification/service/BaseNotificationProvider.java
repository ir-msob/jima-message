package ir.msob.jima.message.notification.service;

import ir.msob.jima.core.commons.annotation.methodstats.MethodStats;
import ir.msob.jima.core.commons.client.BaseAsyncClient;
import ir.msob.jima.core.commons.data.BaseQuery;
import ir.msob.jima.core.commons.exception.runtime.CommonRuntimeException;
import ir.msob.jima.core.commons.model.channel.ChannelMessage;
import ir.msob.jima.core.commons.security.BaseUser;
import ir.msob.jima.core.commons.security.BaseUserService;
import ir.msob.jima.message.commons.BaseMessageProvider;
import ir.msob.jima.message.commons.channel.ChannelData;
import ir.msob.jima.message.commons.domain.SendResult;
import ir.msob.jima.message.commons.repository.BaseMessageSenderRepository;
import ir.msob.jima.message.commons.service.BaseMessageSenderService;
import ir.msob.jima.message.notification.commons.NotificationHelper;
import ir.msob.jima.message.notification.commons.criteria.BaseNotificationSenderCriteria;
import ir.msob.jima.message.notification.commons.domain.BaseNotificationLog;
import ir.msob.jima.message.notification.commons.domain.BaseNotificationReceiver;
import ir.msob.jima.message.notification.commons.domain.BaseNotificationSender;
import ir.msob.jima.message.notification.commons.dto.BaseNotificationDto;
import ir.msob.jima.message.notification.commons.dto.BaseNotificationSenderDto;
import ir.msob.jima.message.notification.commons.dto.BaseNotificationTemplateDto;
import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@CommonsLog
public abstract class BaseNotificationProvider<
        ID extends Comparable<ID> & Serializable,
        USER extends BaseUser,
        ML extends BaseNotificationLog,
        Re extends BaseNotificationReceiver,
        Q extends BaseQuery,
        MeTeDTO extends BaseNotificationTemplateDto<ID, Re>,
        MeDTO extends BaseNotificationDto<ID, ML, Re, MeTeDTO>,

        MeSe extends BaseNotificationSender<ID>,
        MeSeDTO extends BaseNotificationSenderDto<ID>,
        MeSeC extends BaseNotificationSenderCriteria<ID>
        > implements BaseMessageProvider<ID, ML, Re, MeTeDTO, MeDTO> {


    private final Map<ID, MeSeDTO> sources = new HashMap<>();
    @Autowired
    BaseUserService baseUserService;
    private MeSeDTO defaultSource;
    @Autowired
    private BaseMessageSenderService<ID, USER, MeSe, MeSeDTO, MeSeC, Q, ? extends BaseMessageSenderRepository<ID, USER, MeSe, MeSeC, Q>> senderService;
    @Autowired
    private BaseAsyncClient asyncClient;

    @SneakyThrows
    @MethodStats
    @Override
    public SendResult<ML> send(MeDTO message) {
        SendResult<ML> result = new SendResult<>();
        try {
            MeSeDTO sender = getNotificationSender(message);
            ChannelMessage<USER, ChannelData> channelMessage = NotificationHelper.prepareMessage(message);
            asyncClient.send(channelMessage, sender.getChannel(), baseUserService.getSystemUser());
        } catch (Exception e) {
            log.error(e);
            result.setFailed(true);
            ML log = getMessageLogClass().getConstructor().newInstance();
            log.setLogDate(Instant.now());
            log.setLog(e.getMessage());
            result.setLog(log);
        }
        return result;
    }


    private MeSeDTO getNotificationSender(MeDTO message) {
        MeSeDTO result;
        if (message.getMessageTemplate() == null || message.getMessageTemplate().getSenderId() == null)
            result = defaultSource;
        else
            result = sources.get(message.getMessageTemplate().getSenderId());

        if (result == null)
            throw new CommonRuntimeException("Can not found any sender for email");
        return result;
    }


    @PostConstruct
    private void initializer() {
        senderService.getEnabledList()
                .subscribe(sender -> {
                    sources.put(sender.getDomainId(), sender);
                    if (sender.isDefaultSource())
                        defaultSource = sender;
                });
    }

}
