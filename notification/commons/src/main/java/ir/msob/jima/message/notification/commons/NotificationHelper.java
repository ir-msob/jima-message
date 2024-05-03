package ir.msob.jima.message.notification.commons;

import ir.msob.jima.core.commons.model.channel.ChannelMessage;
import ir.msob.jima.core.commons.security.BaseUser;
import ir.msob.jima.message.commons.MessageHelper;
import ir.msob.jima.message.commons.channel.ChannelData;
import ir.msob.jima.message.commons.channel.InternalMetadata;
import ir.msob.jima.message.commons.channel.Receiver;
import ir.msob.jima.message.commons.domain.Attachment;
import ir.msob.jima.message.notification.commons.domain.BaseNotification;
import ir.msob.jima.message.notification.commons.domain.BaseNotificationLog;
import ir.msob.jima.message.notification.commons.domain.BaseNotificationReceiver;
import ir.msob.jima.message.notification.commons.domain.BaseNotificationTemplate;
import org.apache.logging.log4j.util.Strings;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class NotificationHelper {

    public static <ID extends Comparable<ID> & Serializable, USER extends BaseUser, ML extends BaseNotificationLog, Re extends BaseNotificationReceiver, MT extends BaseNotificationTemplate<ID, Re>, M extends BaseNotification<ID, ML, Re, MT>> ChannelMessage<USER, ChannelData> prepareMessage(M message) {
        ChannelData channelData = prepareChannelData(message);
        InternalMetadata internalMetadata = prepareInternalMetadata(message);
        return prepareChannelMessage(channelData, internalMetadata);
    }

    public static <USER extends BaseUser> ChannelMessage<USER, ChannelData> prepareChannelMessage(ChannelData channelData, InternalMetadata internalMetadata) {
        ChannelMessage<USER, ChannelData> result = new ChannelMessage<>();
        result.setData(channelData);
        result.setMetadata(prepareMetadata(internalMetadata));
        return result;
    }

    public static Map<String, Serializable> prepareMetadata(InternalMetadata internalMetadata) {
        Map<String, Serializable> result = new HashMap<>();
        result.put(InternalMetadata.FN.messageId.name(), internalMetadata.getMessageId());
        result.put(InternalMetadata.FN.guaranteedDelivery.name(), internalMetadata.isGuaranteedDelivery());
        result.put(InternalMetadata.FN.receivers.name(), internalMetadata.getReceivers());
        return result;
    }

    public static <ID extends Comparable<ID> & Serializable, ML extends BaseNotificationLog, Re extends BaseNotificationReceiver, MT extends BaseNotificationTemplate<ID, Re>, M extends BaseNotification<ID, ML, Re, MT>> InternalMetadata prepareInternalMetadata(M message) {
        InternalMetadata result = new InternalMetadata();
        if (message.getMessageTemplate() != null) {
            message.getMessageTemplate().getReceivers()
                    .forEach(re -> prepareReceiver(result.getReceivers(), re));

            if (message.getMessageTemplate().getGuaranteedDelivery() != null) {
                result.setGuaranteedDelivery(message.getMessageTemplate().getGuaranteedDelivery());
            }
        }
        message.getReceivers()
                .forEach(re -> prepareReceiver(result.getReceivers(), re));

        if (message.getGuaranteedDelivery() != null) {
            result.setGuaranteedDelivery(message.getGuaranteedDelivery());
        }

        return result;
    }

    public static <Re extends BaseNotificationReceiver> void prepareReceiver(Collection<Receiver> receivers, Re receiver) {
        for (Receiver r : receivers) {
            if (r.getReceiverType() == receiver.getReceiverType()) {
                r.getReceivers().addAll(receiver.getReceivers());
                return;
            }
        }
        receivers.add(castReceiver(receiver));
    }

    public static <Re extends BaseNotificationReceiver> Receiver castReceiver(Re receiver) {
        Receiver result = new Receiver();
        result.setReceiverType(receiver.getReceiverType());
        result.setReceivers(receiver.getReceivers());
        return result;
    }

    public static <ID extends Comparable<ID> & Serializable, ML extends BaseNotificationLog, Re extends BaseNotificationReceiver, MT extends BaseNotificationTemplate<ID, Re>, M extends BaseNotification<ID, ML, Re, MT>> ChannelData prepareChannelData(M message) {
        ChannelData result = new ChannelData();
        result.setContentType(MessageHelper.prepareContentType(message));
        result.setSubject(prepareSubject(message));
        result.setContent(MessageHelper.prepareContent(message, result.getContentType()));
        result.setAttachments(prepareAttachment(message));
        return result;
    }

    public static <ID extends Comparable<ID> & Serializable, ML extends BaseNotificationLog, Re extends BaseNotificationReceiver, MT extends BaseNotificationTemplate<ID, Re>, M extends BaseNotification<ID, ML, Re, MT>> Collection<Attachment> prepareAttachment(M message) {
        Collection<Attachment> result = new ArrayList<>();
        if (message.getMessageTemplate() != null)
            result.addAll(message.getMessageTemplate().getAttachments());
        result.addAll(message.getAttachments());
        return result;
    }

    public static <ID extends Comparable<ID> & Serializable, ML extends BaseNotificationLog, Re extends BaseNotificationReceiver, MT extends BaseNotificationTemplate<ID, Re>, M extends BaseNotification<ID, ML, Re, MT>> String prepareSubject(M message) {
        String result = null;
        if (message.getMessageTemplate() != null) {
            result = message.getMessageTemplate().getSubject();
        }
        if (Strings.isNotBlank(message.getSubject())) {
            result = message.getSubject();
        }
        if (result == null)
            result = "";
        result = MessageHelper.setParams(message, result);
        return result;
    }
}
