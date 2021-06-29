package com.marketing.bean;

import com.marketing.entity.User;

import javax.ejb.Stateless;

@Stateless
public class UserBean extends AbstractFacade<User> {
    public UserBean() {
        super(User.class);
    }

    public void banUser(String username) {
        User user = (User) getEntityManager()
                .createNamedQuery("User.selectUserWithUsername")
                .setParameter("username", username)
                .getResultList()
                .get(0);
        user.setBanned(true);
        this.edit(user);
    }

    public User getUser(String username){
        return (User) getEntityManager().createNamedQuery("User.selectUserWithUsername")
                .setParameter("username", username)
                .getResultList()
                .get(0);
    }
}
