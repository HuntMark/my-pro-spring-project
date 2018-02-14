package com.apress.prospring4.ch6;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("contactDao")
public class JdbcContactDao implements ContactDao, InitializingBean {

    private Log log = LogFactory.getLog(JdbcContactDao.class);

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Contact> findAll() {
        final String sql = "SELECT id, first_name, last_name, birth_date FROM contact";
        return namedParameterJdbcTemplate.query(sql,
                (ResultSet rs, int rowNum) -> {
                    Contact contact = new Contact();

                    contact.setId(rs.getLong("id"));
                    contact.setFirstName(rs.getString("first_name"));
                    contact.setLastName(rs.getString("last_name"));
                    contact.setBirthDate(rs.getDate("birth_date"));

                    return contact;
                });
    }

    @Override
    public List<Contact> findAllWithDetail() {
        final String sql = "SELECT c.id, c.first_name, c.last_name, c.birth_date," +
                " t.id AS contact_tel_id, t.tel_type, t.tel_number" +
                " FROM contact c  LEFT JOIN contact_tel_detail t ON c.id = t.contact_id";
        return namedParameterJdbcTemplate.query(sql,
                (ResultSet rs) -> {
                    Map<Long, Contact> map = new HashMap<>();
                    while (rs.next()) {
                        Long id = rs.getLong("id");
                        Contact contact = map.get(id);
                        if (contact == null) {
                            contact = new Contact();
                            contact.setId(id);
                            contact.setFirstName(rs.getString("first_name"));
                            contact.setLastName(rs.getString("last_name"));
                            contact.setBirthDate(rs.getDate("birth_date"));
                            contact.setContactTelDetails(new ArrayList<ContactTelDetail>());
                            map.put(id, contact);
                        }
                        Long contactTelDetailId = rs.getLong("contact_tel_id");
                        if (contactTelDetailId > 0) {
                            ContactTelDetail contactTelDetail = new ContactTelDetail();
                            contactTelDetail.setId(contactTelDetailId);
                            contactTelDetail.setContactId(id);
                            contactTelDetail.setTelType(rs.getString("tel_type"));
                            contactTelDetail.setTelNumber(rs.getString("tel_number"));
                            contact.getContactTelDetails().add(contactTelDetail);
                        }
                    }
                    return new ArrayList<>(map.values());
                });
    }

    @Override
    public List<Contact> findByFirstName(String firstName) {
        return null;
    }

    @Override
    public String findLastNameById(Long id) {
        String sql = "SELECT last_name FROM contact WHERE id = :contactId";

        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("contactId", id);

        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, String.class);
    }

    @Override
    public String findFirstNameById(Long id) {
        return jdbcTemplate.queryForObject("SELECT first_name FROM contact WHERE id = ?",
                new Object[]{id}, String.class);
    }

    @Override
    public void insert(Contact contact) {
    }

    @Override
    public void insertWithDetail(Contact contact) {
    }

    @Override
    public void update(Contact contact) {
    }

    @Override
    public void delete(Long contactId) {
    }

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;

        MySQLErrorCodesTranslator errorTranslator = new MySQLErrorCodesTranslator();
        errorTranslator.setDataSource(dataSource);

        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        jdbcTemplate.setExceptionTranslator(errorTranslator);

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(this.jdbcTemplate);
    }

    @Override
    public void afterPropertiesSet() {
        if (dataSource == null) {
            throw new BeanCreationException("Must set dataSource on ContactDao");
        }
        if (jdbcTemplate == null) {
            throw new BeanCreationException("Null JdbcTemplate on ContactDao");
        }
    }
}
