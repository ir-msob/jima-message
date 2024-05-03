package ir.msob.jima.message.email.commons.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.model.keyvalue.KeyValue;
import ir.msob.jima.message.commons.domain.BaseMessageSenderAbstract;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseEmailSenderAbstract<ID extends Comparable<ID> & Serializable>
        extends BaseMessageSenderAbstract<ID>
        implements BaseEmailSender<ID> {
    private String host;
    private Integer port;
    private String username;
    private String password;
    private List<KeyValue<String, Serializable>> props = new ArrayList<>();
}
