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
        User user = findUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            logger.debug("user has been found");
            return user;
        }
        return null;
    }

    private User findUserByUsername(String username) {
        return (User) getEntityManager().createNamedQuery("User.selectUserWithUsername")
                .setParameter("username", username)
                .getResultList()
                .get(0);
    }
}
