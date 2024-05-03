package ir.msob.jima.message.commons.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.model.domain.BaseDomainAbstract;
import ir.msob.jima.message.commons.enumeration.ContentType;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseMessageTemplateAbstract<ID extends Comparable<ID> & Serializable, Re extends BaseMessageReceiver>
        extends BaseDomainAbstract<ID>
        implements BaseMessageTemplate<ID, Re> {
    private String name;
    private String key;
    private Object content;
    private ContentType contentType;
    private Collection<Re> receivers = new ArrayList<>();
    private Boolean enabled = true;
    private ID senderId;

    public enum FN {
        name, key, content, contentType, receivers, enabled, senderId
    }

}
