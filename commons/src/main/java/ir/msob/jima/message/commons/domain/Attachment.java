package ir.msob.jima.message.commons.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.model.BaseModel;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Attachment implements BaseModel {
    private String name;
    private String filePath;
}
