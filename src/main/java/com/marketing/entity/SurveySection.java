package com.marketing.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "survey_section", schema = "gamified_marketing")
public class SurveySection {
    private long id;
    private String name;
    private String title;
    private String subheading;
    private List<Question> questions;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "title", nullable = false, length = 50)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "subheading", nullable = true, length = 70)
    public String getSubheading() {
        return subheading;
    }

    public void setSubheading(String subheading) {
        this.subheading = subheading;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "survey_section_question",
            joinColumns = @JoinColumn(name = "survey_section_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
