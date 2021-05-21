package com.marketing.bean;

import com.marketing.entity.User;

import javax.ejb.Stateless;

@Stateless
public class LoginBean extends AbstractFacade<User> {

    public LoginBean() {
        super(User.class);
    }

    public User login(String username, String password) {
        logger.debug("Login called with {} {}", username, password);
        User user = this.find(username);
        if (user != null && user.getPassword().equals(password)) {
            logger.debug("user has been found");
            return user;
        }
        return null;
    }
}
