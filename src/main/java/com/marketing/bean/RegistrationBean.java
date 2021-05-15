package com.marketing.bean;

import com.marketing.entity.User;

import javax.ejb.Stateless;

@Stateless
public class RegistrationBean extends AbstractFacade<User> {
    public RegistrationBean() {
        super(User.class);
    }

    public boolean register(String username, String password) {
        User user = this.find(username);
        // Username must be unique, therefore a user with the
        // requested username must not already exist in the database
        if (user == null) {
            user = new User();
            user.setAdmin(false);
            user.setBanned(false);
            user.setUsername(username);
            user.setPassword(password);
            create(user);
            return true;
        } else {
            // User with that username already exists
            return false;
        }
    }
}
