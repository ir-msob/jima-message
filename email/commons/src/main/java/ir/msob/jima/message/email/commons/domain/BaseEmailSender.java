package ir.msob.jima.message.email.commons.domain;

import ir.msob.jima.core.commons.model.keyvalue.KeyValue;
import ir.msob.jima.message.commons.domain.BaseMessageSender;

import java.io.Serializable;
import java.util.List;

public interface BaseEmailSender<ID extends Comparable<ID> & Serializable> extends BaseMessageSender<ID> {

    String getHost();

    void setHost(String host);

    Integer getPort();

    void setPort(Integer port);

    String getUsername();

    void setUsername(String username);

    String getPassword();

    void setPassword(String password);

    List<KeyValue<String, Serializable>> getProps();

    void setProps(List<KeyValue<String, Serializable>> props);
}
