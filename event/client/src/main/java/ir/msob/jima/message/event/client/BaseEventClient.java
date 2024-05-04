package ir.msob.jima.message.event.client;

import ir.msob.jima.core.commons.client.BaseAsyncClient;
import ir.msob.jima.core.commons.model.channel.ChannelMessage;
import ir.msob.jima.core.commons.security.BaseUser;
import ir.msob.jima.core.commons.util.GenericTypeUtil;
import ir.msob.jima.message.commons.channel.ChannelData;
import ir.msob.jima.message.commons.domain.SendResult;
import ir.msob.jima.message.notification.commons.NotificationHelper;
import ir.msob.jima.message.notification.commons.domain.BaseNotificationLog;
import ir.msob.jima.message.notification.commons.domain.BaseNotificationReceiver;
import ir.msob.jima.message.notification.commons.dto.BaseNotificationDto;
import ir.msob.jima.message.notification.commons.dto.BaseNotificationSenderDto;
import ir.msob.jima.message.notification.commons.dto.BaseNotificationTemplateDto;
import lombok.SneakyThrows;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.time.Instant;
import java.util.Optional;

@CommonsLog
public abstract class BaseEventClient<
        ID extends Comparable<ID> & Serializable,
        USER extends BaseUser,
        ML extends BaseNotificationLog,
        Re extends BaseNotificationReceiver,
        MeTeDTO extends BaseNotificationTemplateDto<ID, Re>,
        MeDTO extends BaseNotificationDto<ID, ML, Re, MeTeDTO>,
        MeSeDTO extends BaseNotificationSenderDto<ID>> {
    @Autowired
    private BaseAsyncClient asyncClient;

    protected abstract MeSeDTO getDefaultSource();

    protected Class<ML> getMessageLogClass() {
        return (Class<ML>) GenericTypeUtil.resolveTypeArguments(getClass(), BaseEventClient.class, 2);
    }

    @SneakyThrows
    public SendResult<ML> send(MeDTO message, Optional<USER> user) {
        SendResult<ML> result = new SendResult<>();
        try {
            ChannelMessage<USER, ChannelData> channelMessage = NotificationHelper.prepareMessage(message);
            asyncClient.send(channelMessage, getDefaultSource().getChannel(), user);
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

}
