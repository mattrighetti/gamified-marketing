package com.marketing.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "log", schema = "gamified_marketing")
public class Log {
    private long id;
    private long userId;
    private Timestamp access;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "user_id", nullable = false)
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Column(name = "access", nullable = false)
    public Timestamp getAccess() {
        return access;
    }

    public void setAccess(Timestamp access) {
        this.access = access;
    }
}
