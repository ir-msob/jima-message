package ir.msob.jima.message.email.commons.domain;

import ir.msob.jima.message.commons.domain.BaseMessage;

import java.io.Serializable;
import java.util.Collection;

public interface BaseEmail<ID extends Comparable<ID> & Serializable, ML extends BaseEmailLog, Re extends BaseEmailReceiver, MT extends BaseEmailTemplate<ID, Re>>
        extends BaseMessage<ID, ML, Re, MT> {

    String getSubject();

    void setSubject(String subject);

    Collection<String> getAttachments();

    void setAttachments(Collection<String> attachments);
}
