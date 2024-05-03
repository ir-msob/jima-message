package ir.msob.jima.message.commons.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.model.domain.BaseDomainAbstract;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseMessageSenderAbstract<ID extends Comparable<ID> & Serializable> extends BaseDomainAbstract<ID> implements BaseMessageSender<ID> {
    private String name;
    private boolean defaultSource = true;
    private boolean enabled = true;

    public enum FN {
        name, defaultSource, enabled
    }
}
