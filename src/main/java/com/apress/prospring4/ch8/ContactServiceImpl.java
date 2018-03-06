package com.apress.prospring4.ch8;

import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service("jpaContactService")
@Repository
@Transactional
public class ContactServiceImpl implements ContactService {

    private static final Logger log = getLogger(ContactServiceImpl.class);

    final static String ALL_CONTACT_NATIVE_QUERY =
            "select id, first_name, last_name, birth_date, version from contact";

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

    @Transactional(readOnly = true)
    @Override
    public List<Contact> findAllByNativeQuery() {
        return em.createNativeQuery(ALL_CONTACT_NATIVE_QUERY,
                "contactResult").getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Contact> findByCriteriaQuery(String firstName, String lastName) {
        log.info("Finding contact for firstName: " + firstName + " and lastName: " + lastName);
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Contact> criteriaQuery = cb.createQuery(Contact.class);
        Root<Contact> contactRoot = criteriaQuery.from(Contact.class);
        contactRoot.fetch(Contact_.contactTelDetails, JoinType.LEFT);
        contactRoot.fetch(Contact_.hobbies, JoinType.LEFT);
        criteriaQuery.select(contactRoot).distinct(true);
        Predicate criteria = cb.conjunction();
        if (firstName != null) {
            Predicate p = cb.equal(contactRoot.get(Contact_.firstName), firstName);
            criteria = cb.and(criteria, p);
        }
        if (lastName != null) {
            Predicate p = cb.equal(contactRoot.get(Contact_.lastName), lastName);
            criteria = cb.and(criteria, p);
        }
        criteriaQuery.where(criteria);
        return em.createQuery(criteriaQuery).getResultList();
    }
}
