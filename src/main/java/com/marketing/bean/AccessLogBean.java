package com.marketing.bean;

import com.marketing.entity.Log;

import javax.ejb.Stateless;

@Stateless
public class AccessLogBean extends AbstractFacade<Log> {

    public AccessLogBean() {
        super(Log.class);
    }

    public void logUserAccess(String username) {
        Log log = new Log();
        log.setUser(username);
        create(log);
    }
}
