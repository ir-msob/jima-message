package ir.msob.jima.message.commons.domain;

import ir.msob.jima.core.commons.model.domain.BaseDomain;
import ir.msob.jima.message.commons.enumeration.ContentType;
import ir.msob.jima.message.commons.enumeration.MessageStatus;

import java.io.Serializable;
import java.time.Instant;
import java.util.Collection;
import java.util.Map;

public interface BaseMessage<ID extends Comparable<ID> & Serializable, ML extends BaseMessageLog, Re extends BaseMessageReceiver, MT extends BaseMessageTemplate<ID, Re>>
        extends BaseDomain<ID> {

    MT getMessageTemplate();

    void setMessageTemplate(MT messageTemplate);

    Object getContent();

    void setContent(Object content);

    ContentType getContentType();

    void setContentType(ContentType contentType);

    Map<String, String> getParams();

    void setParams(Map<String, String> params);

    String getKey();

    void setKey(String key);

    MessageStatus getMessageStatus();

    void setMessageStatus(MessageStatus messageStatus);

    Instant getStatusDate();

    void setStatusDate(Instant statusDate);

    Instant getDateToSend();

    void setDateToSend(Instant dateToSend);

    Integer getTryTimes();

    void setTryTimes(Integer tryTimes);

    Integer getPriority();

    void setPriority(Integer priority);

    Collection<ML> getMessageLogs();

    void setMessageLogs(Collection<ML> messageLogs);

    Collection<Re> getReceivers();

    void setReceivers(Collection<Re> receivers);
}
