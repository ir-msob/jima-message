package ir.msob.jima.message.email.commons.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.message.commons.domain.BaseMessageAbstract;
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
public abstract class BaseEmailAbstract<ID extends Comparable<ID> & Serializable, ML extends BaseEmailLog, Re extends BaseEmailReceiver, MT extends BaseEmailTemplate<ID, Re>>
        extends BaseMessageAbstract<ID, ML, Re, MT>
        implements BaseEmail<ID, ML, Re, MT> {
    private String subject;
    private Collection<String> attachments = new ArrayList<>();

}
