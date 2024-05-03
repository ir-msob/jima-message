package ir.msob.jima.message.commons.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SendResult<ML extends BaseMessageLog> {
    private boolean failed = false;
    private ML log;
}
