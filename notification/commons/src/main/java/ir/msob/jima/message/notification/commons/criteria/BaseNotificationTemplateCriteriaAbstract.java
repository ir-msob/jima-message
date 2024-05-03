package ir.msob.jima.message.notification.commons.criteria;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.message.commons.criteria.BaseMessageTemplateCriteriaAbstract;
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
public abstract class BaseNotificationTemplateCriteriaAbstract<ID extends Comparable<ID> & Serializable>
        extends BaseMessageTemplateCriteriaAbstract<ID>
        implements BaseNotificationTemplateCriteria<ID> {
}
