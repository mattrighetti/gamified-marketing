package com.marketing.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "option_group", schema = "gamified_marketing")
public class OptionGroup {
    private long id;
    private String optionGroupName;
    private List<OptionChoice> optionChoices;

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
    @Column(name = "option_group_name", nullable = true, length = 50)
    public String getOptionGroupName() {
        return optionGroupName;
    }

    public void setOptionGroupName(String optionGroupName) {
        this.optionGroupName = optionGroupName;
    }

    @OneToMany
    public List<OptionChoice> getOptionChoice() {
        return optionChoices;
    }

    public void setOptionChoice(List<OptionChoice> optionChoices) {
        this.optionChoices = optionChoices;
    }
}
