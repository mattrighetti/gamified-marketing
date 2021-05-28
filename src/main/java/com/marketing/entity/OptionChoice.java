package com.marketing.entity;

import javax.persistence.*;

@Entity
@Table(name = "option_choice", schema = "gamified_marketing")
public class OptionChoice {
    private long id;
    private OptionGroup optionGroupId;
    private String optionChoiceName;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "option_choice_name", nullable = false)
    public String getOptionChoiceName() {
        return optionChoiceName;
    }

    public void setOptionChoiceName(String optionChoiceName) {
        this.optionChoiceName = optionChoiceName;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "option_group_id", nullable = false)
    public OptionGroup getOptionGroupId() {
        return optionGroupId;
    }

    public void setOptionGroupId(OptionGroup optionGroupId) {
        this.optionGroupId = optionGroupId;
    }
}
