package ir.msob.jima.message.commons.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.ArrayDeque;
import java.util.Collection;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseMessageReceiverAbstract implements BaseMessageReceiver {
    private Collection<String> receivers = new ArrayDeque<>();

    public enum FN {
        receivers
    }
}
