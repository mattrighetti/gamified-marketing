package com.marketing.bean;

import com.marketing.entity.User;

import javax.ejb.Stateless;

@Stateless
public class RegistrationBean extends AbstractFacade<User> {

    public RegistrationBean() {
        super(User.class);
    }

    public boolean register(String username, String password, String email) {
        // Username must be unique, therefore a user with the
        // requested username must not already exist in the database
        if (getEntityManager().createNamedQuery("User.selectUserWithUsernameOrEmail")
                .setParameter("username", username)
                .setParameter("email", email)
                .getResultList()
                .isEmpty()) {
            User user = new User();
            user.setAdmin(false);
            user.setBanned(false);
            user.setEmail(email);
            user.setUsername(username);
            user.setPassword(password);
            user.setScore(0);
            create(user);
            return true;
        } else {
            // User with that username already exists
            return false;
        }
    }
}
