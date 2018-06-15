package com.apress.prospring4.ch8.enversexample;

import org.hibernate.envers.Audited;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Audited
@DiscriminatorValue("TO")
public class MessageTo extends AbstractMessage {
    @Override
    public String toString() {
        return super.toString();
    }
}
