package com.marketing.bean;

import com.marketing.entity.SurveySection;

import javax.ejb.Stateless;

@Stateless
public class SurveySectionBean extends AbstractFacade<SurveySection> {

    public SurveySectionBean() {
        super(SurveySection.class);
    }

    public SurveySection getSurveyById(int surveySectionId) {
        return getEntityManager().find(SurveySection.class, surveySectionId);
    }
}
