package com.example.springjdbc.persistence;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;

import javax.sql.DataSource;
import java.util.Objects;

public class JdbcContactDao
        implements ContactDao, InitializingBean {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (Objects.isNull(dataSource)) {
            throw new BeanCreationException("Must set dataSource on ContactDao");
        }
    }

    @Override
    public String findLastNameById(Long id) {
        return null;
    }
}
