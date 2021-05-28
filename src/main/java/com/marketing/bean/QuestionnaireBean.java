package com.marketing.bean;

import com.marketing.entity.SurveyHeader;
import com.marketing.entity.SurveySection;

import javax.ejb.EJB;
import javax.ejb.Stateful;

@Stateful
public class QuestionnaireBean extends AbstractFacade<SurveyHeader> {
    private int currentSection = 0;

    @EJB
    private ProductBean productBean;
    private SurveyHeader survey;

    public QuestionnaireBean() {
        super(SurveyHeader.class);
    }

    public SurveyHeader getProductQuestionnaire(int productId) {
        // TODO in this case I am getting the first result in the list, change it to select possible multi-surveys
        if (survey != null) {
            return this.survey;
        }

        this.survey = (SurveyHeader) getEntityManager()
                .createNamedQuery("SurveyHeader.selectSurveyHeaderWhereProduct")
                .setParameter("productId", productBean.getProduct(productId))
                .getResultList()
                .get(0);
        return this.survey;
    }

    public void nextQuestion() {
        this.currentSection += 1;
    }

    public void previousQuestion() {
        this.currentSection -= 1;
    }
}
