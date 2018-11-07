package com.careline.interview.test.component;

import java.util.Date;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class TestComponent {

    @Autowired
    private JdbcTemplate db;

    @PostConstruct
    void postConstruct() {
        db.update("INSERT INTO tmp ("
                + " key, value, desc"
                + " )"
                + " VALUES ("
                + " ?, ?, ?"
                + " )"
                + " ON DUPLICATE KEY UPDATE"
                + " value = VALUES(value), desc = VALUES(desc)"
                , "TestComponent.Init"
                , "Done."+new Date().getTime()
                , null
        );
    }
    
    // <editor-fold desc="Getters & Setters" defaultstate="collapsed">
    // </editor-fold>
}
