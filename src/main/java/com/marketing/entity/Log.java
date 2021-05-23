package com.marketing.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "log")
public class Log {
    private long id;
    private Object userId;
    private Timestamp access;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "user_id", nullable = false)
    public Object getUserId() {
        return userId;
    }

    public void setUserId(Object userId) {
        this.userId = userId;
    }

    @Column(name = "access", nullable = false)
    public Timestamp getAccess() {
        return access;
    }

    public void setAccess(Timestamp access) {
        this.access = access;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Log log = (Log) o;

        if (id != log.id) return false;
        if (userId != null ? !userId.equals(log.userId) : log.userId != null) return false;
        if (access != null ? !access.equals(log.access) : log.access != null) return false;

        return true;
    }
}
