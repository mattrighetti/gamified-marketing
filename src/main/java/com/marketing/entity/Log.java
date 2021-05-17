package com.marketing.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@IdClass(LogPK.class)
@Table(name = "log")
public class Log {
    private String user;
    private Timestamp access;

    @Id
    @Column(name = "user", nullable = false, length = 50)
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Id
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

        if (user != null ? !user.equals(log.user) : log.user != null) return false;
        if (access != null ? !access.equals(log.access) : log.access != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (access != null ? access.hashCode() : 0);
        return result;
    }
}
