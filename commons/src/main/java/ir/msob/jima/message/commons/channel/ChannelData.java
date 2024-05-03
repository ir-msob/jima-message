package ir.msob.jima.message.commons.channel;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.model.dto.ModelType;
import ir.msob.jima.message.commons.domain.Attachment;
import ir.msob.jima.message.commons.enumeration.ContentType;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChannelData extends ModelType {
    private String subject;
    private Object content;
    private ContentType contentType;
    private Collection<Attachment> attachments = new ArrayList<>();
}
