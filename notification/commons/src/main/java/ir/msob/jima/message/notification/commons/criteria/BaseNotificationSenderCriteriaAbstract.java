package ir.msob.jima.message.notification.commons.criteria;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.message.commons.criteria.BaseMessageSenderCriteriaAbstract;
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
public abstract class BaseNotificationSenderCriteriaAbstract<ID extends Comparable<ID> & Serializable>
        extends BaseMessageSenderCriteriaAbstract<ID>
        implements BaseNotificationSenderCriteria<ID> {
}
