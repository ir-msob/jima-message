package ir.msob.jima.message.commons.domain;

import ir.msob.jima.core.commons.model.domain.BaseDomain;
import ir.msob.jima.message.commons.enumeration.ContentType;

import java.io.Serializable;
import java.util.Collection;

public interface BaseMessageTemplate<ID extends Comparable<ID> & Serializable, Re extends BaseMessageReceiver>
        extends BaseDomain<ID> {

    String getName();

    void setName(String name);

    String getKey();

    void setKey(String key);

    Object getContent();

    void setContent(Object content);

    ContentType getContentType();

    void setContentType(ContentType contentType);

    Collection<Re> getReceivers();

    void setReceivers(Collection<Re> receivers);

    Boolean getEnabled();

    void setEnabled(Boolean enabled);

    ID getSenderId();

    void setSenderId(ID senderId);
}
