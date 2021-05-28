package com.marketing.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "option_group", schema = "gamified_marketing")
public class OptionGroup {
    private long id;
    private String optionGroupName;
    private Collection<OptionChoice> optionChoices;

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
    @Column(name = "option_group_name", length = 50)
    public String getOptionGroupName() {
        return optionGroupName;
    }

    public void setOptionGroupName(String optionGroupName) {
        this.optionGroupName = optionGroupName;
    }

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "option_group_id")
    public Collection<OptionChoice> getOptionChoice() {
        return optionChoices;
    }

    public void setOptionChoice(Collection<OptionChoice> optionChoices) {
        this.optionChoices = optionChoices;
    }
}
