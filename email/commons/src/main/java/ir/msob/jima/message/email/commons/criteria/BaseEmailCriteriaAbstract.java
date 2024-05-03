package ir.msob.jima.message.email.commons.criteria;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.message.commons.criteria.BaseMessageCriteriaAbstract;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseEmailCriteriaAbstract<ID extends Comparable<ID> & Serializable>
        extends BaseMessageCriteriaAbstract<ID>
        implements BaseEmailCriteria<ID> {
}
