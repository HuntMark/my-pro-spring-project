package com.apress.prospring4.ch9;

import java.util.List;

public interface ContactServiceTxViaXml {
    List<Contact> findAll();

    Contact findById(Long id);

    Contact save(Contact contact);

    long countAll();
}
