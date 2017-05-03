package com.example.springjdbc.domain;

import java.io.Serializable;

public class ContactDetail implements Serializable {

    private Long id;
    private Long contactId;
    private String phoneType;
    private String phoneNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "ContactDetail{" +
                "id=" + id +
                ", contactId=" + contactId +
                ", phoneType='" + phoneType + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
