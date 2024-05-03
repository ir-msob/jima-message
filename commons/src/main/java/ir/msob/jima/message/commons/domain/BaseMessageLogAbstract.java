package ir.msob.jima.message.commons.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.Instant;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseMessageLogAbstract {
    private String log;
    private Instant logDate = Instant.now();

    public enum FN {
        log, logDate
    }
}
