package com.marketing.entity;

import javax.persistence.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "survey_header", schema = "gamified_marketing")
@NamedQueries({
        @NamedQuery(
                name = "SurveyHeader.selectSurveyHeaderWhereProduct",
                query = "SELECT s FROM SurveyHeader s WHERE s.productId = :productId AND s.id = :surveyId"),
        @NamedQuery(
                name = "SurveyHeader.allPastSurveysOrderedByDate",
                query = "select s from SurveyHeader s, Product p where p = s.productId and p.date < :today order by p.date desc")
})
public class SurveyHeader {
    private long id;
    private Product productId;
    private String name;
    private String instructions;
    private Map<Integer, SurveySection> surveySections;
    private List<Answer> answers;
    private List<User> compiledQuestUsers;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    @Column(name = "product_id", nullable = false)
    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "instructions", nullable = true, length = 255)
    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "survey_header_survey_section",
            joinColumns = @JoinColumn(name = "survey_header_id"),
            inverseJoinColumns = @JoinColumn(name = "survey_section_id")
    )
    @MapKeyColumn(name = "section_order")
    public Map<Integer, SurveySection> getSurveySections() {
        return surveySections;
    }

    public void setSurveySections(Map<Integer, SurveySection> surveySections) {
        this.surveySections = surveySections;
    }
    public void addSurveySection(int id, SurveySection surveySection){
        surveySections.put(id, surveySection);
    }


    @OneToMany(mappedBy = "surveyHeaderId")
    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public void addAnswers(Answer answer){
        this.answers.add(answer);
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "survey_header_user",
            joinColumns = @JoinColumn(name = "survey_header_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    public List<User> getCompiledQuestUsers() {
        return compiledQuestUsers;
    }

    public void setCompiledQuestUsers(List<User> compiledQuestUsers) {
        this.compiledQuestUsers = compiledQuestUsers;
    }

    public void addCompiledQuestUsers(User user){
        compiledQuestUsers.add(user);
    }

}
