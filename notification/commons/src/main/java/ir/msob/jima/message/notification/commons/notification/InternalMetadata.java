package ir.msob.jima.message.notification.commons.notification;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InternalMetadata {
    private String messageId;
    private boolean guaranteedDelivery = false;
    private Collection<Receiver> receivers = new ArrayList<>();

}
