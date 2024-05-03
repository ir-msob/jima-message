package ir.msob.jima.message.commons.domain;

import ir.msob.jima.core.commons.model.BaseModel;

import java.util.Collection;

public interface BaseMessageReceiver extends BaseModel {

    Collection<String> getReceivers();

    void setReceivers(Collection<String> receivers);
}
