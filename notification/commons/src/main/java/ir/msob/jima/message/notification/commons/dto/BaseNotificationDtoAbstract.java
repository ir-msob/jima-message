package ir.msob.jima.message.notification.commons.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ir.msob.jima.message.commons.dto.BaseMessageDtoAbstract;
import ir.msob.jima.message.notification.commons.domain.BaseNotificationLog;
import ir.msob.jima.message.notification.commons.domain.BaseNotificationReceiver;
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
public abstract class BaseNotificationDtoAbstract<ID extends Comparable<ID> & Serializable, ML extends BaseNotificationLog, Re extends BaseNotificationReceiver, MT extends BaseNotificationTemplateDto<ID, Re>>
        extends BaseMessageDtoAbstract<ID, ML, Re, MT>
        implements BaseNotificationDto<ID, ML, Re, MT> {
}
