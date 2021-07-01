package com.marketing.bean;

import com.marketing.entity.Question;
import com.marketing.entity.SurveySection;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class UnownedEntitiesBean extends AbstractFacade<Object> {
    public UnownedEntitiesBean() {
        super(Object.class);
    }

    private List<Integer> getAllUnownedSurveySectionIds() {
        Query q = getEntityManager().createNativeQuery("SELECT id FROM survey_section WHERE id NOT IN (SELECT DISTINCT survey_section_id FROM survey_header_survey_section)", Integer[].class);
        return q.getResultList();
    }

    private List<Integer> getAllUnownedQuestionIds() {
        Query q = getEntityManager().createNativeQuery("SELECT id FROM question WHERE id NOT IN (SELECT DISTINCT question_id FROM survey_section_question)", Integer[].class);
        return q.getResultList();
    }

    private void removeUnownedQuestions() {
        List<Integer> unownedQuestionIds = getAllUnownedQuestionIds();
        unownedQuestionIds.forEach(id -> {
            getEntityManager().remove(getEntityManager().find(Question.class, id));
        });
    }

    private void removeUnownedSections() {
        List<Integer> unownedSectionIds = getAllUnownedSurveySectionIds();
        unownedSectionIds.stream().filter(x -> x != 2).forEach(id -> {
            getEntityManager().remove(getEntityManager().find(SurveySection.class, id));
        });
    }

    public void removeUnownedEntries() {
        removeUnownedSections();
        removeUnownedQuestions();
    }
}
