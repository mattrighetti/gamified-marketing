package com.marketing.bean;

import com.marketing.entity.User;

import javax.ejb.Stateless;

@Stateless(name = "LoginBean")
public class LoginBean extends AbstractFacade<User> {

    public LoginBean() {
        super(User.class);
    }

    public boolean login(String username, String password) {
        User user = this.find(username);
        if (user != null)
            return user.getPassword().equals(password);
        return false;
    }
}
