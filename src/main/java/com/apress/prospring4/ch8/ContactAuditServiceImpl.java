package com.apress.prospring4.ch8;

import com.google.common.collect.Lists;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service("contactAuditService")
@Repository
@Transactional
public class ContactAuditServiceImpl implements ContactAuditService {

    private ContactAuditRepository contactAuditRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public void setContactAuditRepository(ContactAuditRepository contactAuditRepository) {
        this.contactAuditRepository = contactAuditRepository;
    }

    @Transactional(readOnly = true)
    public List<ContactAudit> findAll() {
        return Lists.newArrayList(contactAuditRepository.findAll());
    }

    public ContactAudit findById(Long id) {
        return contactAuditRepository.findOne(id);
    }

    public ContactAudit save(ContactAudit contact) {
        return contactAuditRepository.save(contact);
    }

    @Transactional(readOnly = true)
    @Override
    public ContactAudit findAuditByRevision(Long id, int revision) {
        AuditReader auditReader = AuditReaderFactory.get(this.entityManager);
        return auditReader.find(ContactAudit.class, id, revision);
    }
}
