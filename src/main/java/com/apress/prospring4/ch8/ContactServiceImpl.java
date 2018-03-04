package com.apress.prospring4.ch8;

import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service("jpaContactService")
@Repository
@Transactional
public class ContactServiceImpl implements ContactService {

    private static final Logger log = getLogger(ContactServiceImpl.class);

    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    @Override
    public List<Contact> findAll() {
        return em.createNamedQuery("Contact.findAll", Contact.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Contact> findAllWithDetail() {
        return em.createNamedQuery("Contact.findAllWithDetail", Contact.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public Contact findById(Long id) {
        return em.createNamedQuery("Contact.findById", Contact.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public Contact save(Contact contact) {
        if (contact.getId() == null) {
            log.info("Inserting new contact");
            em.persist(contact);
        } else {
            em.merge(contact);
            log.info("Updating existing contact");
        }
        log.info("Contact saved with id: " + contact.getId());
        return contact;
    }

    @Override
    public void delete(Contact contact) {
        Contact mergedContact = em.merge(contact);
        em.remove(mergedContact);
        log.info("Contact with id: " + contact.getId() + " deleted successfully");
    }
}
