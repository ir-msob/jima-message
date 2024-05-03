package ir.msob.jima.message.commons.criteria;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.core.commons.model.criteria.BaseCriteriaAbstract;
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
public abstract class BaseMessageCriteriaAbstract<ID extends Comparable<ID> & Serializable> extends BaseCriteriaAbstract<ID> implements BaseMessageCriteria<ID> {

}
