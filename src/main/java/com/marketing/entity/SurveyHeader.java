package com.marketing.entity;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "survey_header", schema = "gamified_marketing")
@NamedQuery(
        name = "SurveyHeader.selectSurveyHeaderWhereProduct",
        query = "SELECT s FROM SurveyHeader s WHERE s.productId = :productId AND s.id = :surveyId"
)
public class SurveyHeader {
    private long id;
    private Product productId;
    private String name;
    private String instructions;
    private Map<Integer, SurveySection> surveySections;

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
}
