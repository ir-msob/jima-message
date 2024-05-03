package ir.msob.jima.message.commons;

import ir.msob.jima.message.commons.domain.BaseMessage;
import ir.msob.jima.message.commons.domain.BaseMessageLog;
import ir.msob.jima.message.commons.domain.BaseMessageReceiver;
import ir.msob.jima.message.commons.domain.BaseMessageTemplate;
import ir.msob.jima.message.commons.enumeration.ContentType;

import java.io.Serializable;
import java.util.Map;

public class MessageHelper {

    public static <ID extends Comparable<ID> & Serializable, ML extends BaseMessageLog, Re extends BaseMessageReceiver, MT extends BaseMessageTemplate<ID, Re>, M extends BaseMessage<ID, ML, Re, MT>> String setParams(M message, String text) {
        for (Map.Entry<String, String> entry : message.getParams().entrySet()) {
            text = text.replaceAll(entry.getKey(), entry.getValue());
        }
        return text;
    }

    public static <ID extends Comparable<ID> & Serializable, ML extends BaseMessageLog, Re extends BaseMessageReceiver, MT extends BaseMessageTemplate<ID, Re>, M extends BaseMessage<ID, ML, Re, MT>> Object prepareContent(M message, ContentType contentType) {
        Object result = null;
        if (message.getMessageTemplate() != null) {
            result = message.getMessageTemplate().getContent();
        }
        if (message.getContent() != null) {
            result = message.getContent();
        }
        if (result instanceof String
                && (contentType == ContentType.HTML || contentType == ContentType.TEXT))
            result = MessageHelper.setParams(message, String.valueOf(result));
        return result;
    }

    public static <ID extends Comparable<ID> & Serializable, ML extends BaseMessageLog, Re extends BaseMessageReceiver, MT extends BaseMessageTemplate<ID, Re>, M extends BaseMessage<ID, ML, Re, MT>> ContentType prepareContentType(M message) {
        ContentType result = null;
        if (message.getMessageTemplate() != null)
            result = message.getMessageTemplate().getContentType();
        if (message.getContentType() != null) {
            result = message.getContentType();
        }
        return result;
    }
}
