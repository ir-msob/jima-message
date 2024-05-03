package ir.msob.jima.message.commons.domain;

import ir.msob.jima.core.commons.model.domain.BaseDomain;

import java.io.Serializable;

public interface BaseMessageSender<ID extends Comparable<ID> & Serializable>
        extends BaseDomain<ID> {

    String getName();

    void setName(String name);

    boolean isDefaultSource();

    void setDefaultSource(boolean defaultSource);

    boolean isEnabled();

    void setEnabled(boolean enabled);
}
