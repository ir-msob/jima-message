package ir.msob.jima.message.commons;

import ir.msob.jima.core.commons.util.GenericTypeUtil;
import ir.msob.jima.message.commons.domain.BaseMessageLog;
import ir.msob.jima.message.commons.domain.BaseMessageReceiver;
import ir.msob.jima.message.commons.domain.SendResult;
import ir.msob.jima.message.commons.dto.BaseMessageDto;
import ir.msob.jima.message.commons.dto.BaseMessageTemplateDto;

import java.io.Serializable;

public interface BaseMessageProvider<
        ID extends Comparable<ID> & Serializable,
        ML extends BaseMessageLog,
        Re extends BaseMessageReceiver,
        MT extends BaseMessageTemplateDto<ID, Re>,
        MeDTO extends BaseMessageDto<ID, ML, Re, MT>
        > {
    SendResult<ML> send(MeDTO message);

    default Class<ML> getMessageLogClass() {
        return (Class<ML>) GenericTypeUtil.resolveTypeArguments(getClass(), BaseMessageProvider.class, 1);
    }

}
