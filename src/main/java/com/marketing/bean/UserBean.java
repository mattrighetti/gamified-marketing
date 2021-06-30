package com.marketing.bean;

import com.marketing.entity.SurveyHeader;
import com.marketing.entity.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class UserBean extends AbstractFacade<User> {

    @EJB
    private ProductBean productBean;

    public UserBean() {
        super(User.class);
    }

    public void banUser(String username) {
        User user = getEntityManager()
                .createNamedQuery("User.selectUserWithUsername", User.class)
                .setParameter("username", username)
                .getResultList()
                .get(0);
        user.setBanned(true);
        this.edit(user);
    }

    public User getUser(String username) {
        return getEntityManager().createNamedQuery("User.selectUserWithUsername", User.class)
                .setParameter("username", username)
                .getResultList()
                .get(0);
    }

    public boolean hasUserCompiledQuestionnaire(String username, long productId) {
        SurveyHeader surveyHeader = getEntityManager()
                .createNamedQuery("SurveyHeader.selectSurveyHeaderWhereProduct", SurveyHeader.class)
                .setParameter("productId", productBean.getProduct(productId))
                .getResultList()
                .get(0);
        return surveyHeader.getCompiledQuestUsers().contains(getUser(username));
    }
}
