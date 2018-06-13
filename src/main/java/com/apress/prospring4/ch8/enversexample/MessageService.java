package com.apress.prospring4.ch8.enversexample;

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

@Service
@Repository
@Transactional
public class MessageService {

    @PersistenceContext
    private EntityManager entityManager;

    private MessageRepository repository;

    @Autowired
    public void setRepository(MessageRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Message> findAll() {
        return Lists.newArrayList(repository.findAll());
    }

    public Message findById(Long id) {
        return repository.findOne(id);
    }

    public Message save(Message contact) {
        return repository.save(contact);
    }

    @Transactional(readOnly = true)
    public Message findAuditByRevision(Long id, int revision) {
        AuditReader auditReader = AuditReaderFactory.get(this.entityManager);
        return auditReader.find(Message.class, id, revision);
    }
}
