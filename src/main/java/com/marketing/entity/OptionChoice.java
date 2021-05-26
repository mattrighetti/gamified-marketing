package com.marketing.entity;

import javax.persistence.*;

@Entity
@Table(name = "option_choice", schema = "gamified_marketing")
public class OptionChoice {
    private long id;
    private Object optionGroupId;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "option_group_id", nullable = false)
    public Object getOptionGroupId() {
        return optionGroupId;
    }

    public void setOptionGroupId(Object optionGroupId) {
        this.optionGroupId = optionGroupId;
    }
}
