package ir.msob.jima.message.commons.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.model.domain.BaseDomainAbstract;
import ir.msob.jima.message.commons.enumeration.ContentType;
import ir.msob.jima.message.commons.enumeration.MessageStatus;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseMessageAbstract<ID extends Comparable<ID> & Serializable, ML extends BaseMessageLog, Re extends BaseMessageReceiver, MT extends BaseMessageTemplate<ID, Re>>
        extends BaseDomainAbstract<ID>
        implements BaseMessage<ID, ML, Re, MT> {
    private MT messageTemplate;
    private Object content;
    private ContentType contentType;
    private Map<String, String> params = new HashMap<>();
    private String key;
    private MessageStatus messageStatus = MessageStatus.INITIALIZED;
    private Instant statusDate = Instant.now();
    private Instant dateToSend = Instant.now();
    private Integer tryTimes = 0;
    private Integer priority = 0;
    private Collection<ML> messageLogs = new ArrayList<>();
    private Collection<Re> receivers = new ArrayList<>();

    public enum FN {
        messageTemplate, content, contentType, messageType, params, key, messageStatus, statusDate, dateToSend, tryTimes, priority, messageLogs, receivers
    }
}
