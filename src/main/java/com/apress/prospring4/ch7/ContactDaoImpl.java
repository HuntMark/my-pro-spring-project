package com.apress.prospring4.ch7;

import static org.slf4j.LoggerFactory.getLogger;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Repository("contactDao")
public class ContactDaoImpl implements ContactDao {

    private static final Logger log = getLogger(ContactDaoImpl.class);

    private SessionFactory sessionFactory;

    @Override
    @Transactional(readOnly = true)
    public List<Contact> findAll() {
        return this.sessionFactory
                .getCurrentSession()
                .createQuery("from Contact c")
                .list();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contact> findAllWithDetail() {
        return this.sessionFactory
                .getCurrentSession()
                .getNamedQuery("Contact.findAllWithDetail")
                .list();
    }

    @Override
    @Transactional(readOnly = true)
    public Contact findById(Long id) {
        return (Contact) this.sessionFactory
                .getCurrentSession()
                .getNamedQuery("Contact.findById")
                .setParameter("id", id)
                .uniqueResult();
    }

    @Override
    public Contact save(Contact contact) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(contact);
        log.info("Contact saved with id:" + contact.getId());
        return contact;
    }

    @Override
    public void delete(Contact contact) {
        this.sessionFactory.getCurrentSession().delete(contact);
        log.info("Contact deleted with id: " + contact.getId());
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
