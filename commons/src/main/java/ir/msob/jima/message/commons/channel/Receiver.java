package ir.msob.jima.message.commons.channel;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public class Receiver implements Serializable {
    private Collection<String> receivers = new ArrayList<>();
    private ReceiverType receiverType = ReceiverType.USER;
}
