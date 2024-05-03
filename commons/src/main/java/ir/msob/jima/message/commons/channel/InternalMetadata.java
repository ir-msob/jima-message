package ir.msob.jima.message.commons.channel;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InternalMetadata implements Serializable {
    private String messageId;
    private boolean guaranteedDelivery = false;
    private ArrayList<Receiver> receivers = new ArrayList<>();

    public enum FN {
        messageId, guaranteedDelivery, receivers
    }
}
