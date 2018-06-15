package com.apress.prospring4.ch8.enversexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

public class EnversExample {

    private static final Logger log = LoggerFactory.getLogger(EnversExample.class);

    public static void main(String[] args) throws InterruptedException {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:app-context-envers-example.xml");
        ctx.refresh();

        MessageService service = ctx.getBean("messageService", MessageService.class);

        log.info("==============================");

        Long messageFromId = createMessageFrom(service);

        log.info("Created messageFrom: {}", service.findById(messageFromId));

        Thread.sleep(100L);

        updateMessageFrom1(service, messageFromId);

        log.info("Updated messageFrom 1: {}", service.findById(messageFromId));

        Thread.sleep(100L);

        updateMessageFrom2(service, messageFromId);

        log.info("Updated messageFrom 2: {}", service.findById(messageFromId));

        log.info("==============================");

        Long messageToId = createMessageTo(service);

        log.info("Created messageTo: {}", service.findById(messageToId));

        Thread.sleep(100L);

        updateMessageTo1(service, messageToId);

        log.info("Updated messageTo 1: {}", service.findById(messageToId));

        Thread.sleep(100L);

        updateMessageTo2(service, messageToId);

        log.info("Updated messageTo 2: {}", service.findById(messageToId));

        log.info("==============================");

        DataSource dataSource = ctx.getBean("dataSource", DataSource.class);

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        List<Map<String, Object>> messagesHistory = jdbcTemplate.queryForList("select * from message_h order by id, audit_revision");
        messagesHistory
                .forEach(messageHistory -> log.info(messageHistory.toString()));
    }

    private static Long createMessageFrom(MessageService service) {
        AbstractMessage message = new MessageFrom();
        message.setText("MessageFrom created!");
        service.save(message);
        return message.getId();
    }

    private static void updateMessageFrom1(MessageService service, Long messageId) {
        AbstractMessage message = service.findById(messageId);
        message.setText("MessageFrom updated 1!");
        service.save(message);
    }

    private static void updateMessageFrom2(MessageService service, Long messageId) {
        AbstractMessage message = service.findById(messageId);
        message.setText("MessageFrom updated 2!");
        service.save(message);
    }

    private static Long createMessageTo(MessageService service) {
        AbstractMessage message = new MessageTo();
        message.setText("MessageTo created!");
        service.save(message);
        return message.getId();
    }

    private static void updateMessageTo1(MessageService service, Long messageId) {
        AbstractMessage message = service.findById(messageId);
        message.setText("MessageTo updated 1!");
        service.save(message);
    }

    private static void updateMessageTo2(MessageService service, Long messageId) {
        AbstractMessage message = service.findById(messageId);
        message.setText("MessageTo updated 2!");
        service.save(message);
    }
}
