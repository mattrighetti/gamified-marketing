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
    private List<User> compiledFrom;

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

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "survey_header_user",
                joinColumns = @JoinColumn(name = "survey_header_id"),
                inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    public List<User> getCompiledFrom() {
        return compiledFrom;
    }

    public void setCompiledFrom(List<User> compiledFrom) {
        this.compiledFrom = compiledFrom;
    }
    
    public void addCompiledFrom(User user){
        compiledFrom.add(user);
    }
}
