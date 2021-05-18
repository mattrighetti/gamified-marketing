package com.marketing.bean;

import com.marketing.entity.User;

import javax.ejb.Stateless;

@Stateless
public class RegistrationBean extends AbstractFacade<User> {

    public RegistrationBean() {
        super(User.class);
    }

    public boolean register(String username, String password, String email) {
        User user = this.find(username);
        // Username must be unique, therefore a user with the
        // requested username must not already exist in the database
        if (user == null) {
            user = new User();
            user.setAdmin((byte) 0x0);
            user.setBanned((byte) 0x0);
            user.setEmail(email);
            user.setUsername(username);
            user.setPassword(password);
            user.setScore(0);
            user.setAdmin((byte) 0x0);
            user.setBanned((byte) 0x0);
            create(user);
            return true;
        } else {
            // User with that username already exists
            return false;
        }
    }
}
