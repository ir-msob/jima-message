package ir.msob.jima.message.commons.domain;

import ir.msob.jima.core.commons.model.BaseModel;

import java.time.Instant;

public interface BaseMessageLog extends BaseModel {

    String getLog();

    void setLog(String log);

    Instant getLogDate();

    void setLogDate(Instant logDate);
}
