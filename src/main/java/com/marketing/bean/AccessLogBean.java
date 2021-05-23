package com.marketing.bean;

import com.marketing.entity.Log;
import com.marketing.entity.User;

import javax.ejb.Stateless;
import java.sql.Timestamp;

@Stateless
public class AccessLogBean extends AbstractFacade<Log> {

    public AccessLogBean() {
        super(Log.class);
    }

    public void logUserAccess(User user) {
        Log log = new Log();
        log.setUserId(user.getId());
        log.setAccess(new Timestamp(System.currentTimeMillis()));
        create(log);
    }
}
