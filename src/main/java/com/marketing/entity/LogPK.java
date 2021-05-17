package com.marketing.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

public class LogPK implements Serializable {
    private String user;
    private Timestamp access;

    @Column(name = "user", nullable = false, length = 50)
    @Id
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Column(name = "access", nullable = false)
    @Id
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

        LogPK logPK = (LogPK) o;

        if (user != null ? !user.equals(logPK.user) : logPK.user != null) return false;
        if (access != null ? !access.equals(logPK.access) : logPK.access != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (access != null ? access.hashCode() : 0);
        return result;
    }
}
