package com.marketing.bean;

import com.marketing.entity.Forbidden;

import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ForbiddenWordBean extends AbstractFacade<Forbidden> {
    public ForbiddenWordBean() {
        super(Forbidden.class);
    }

    public List<String> getForbiddenWords() {
        return this.findAll().stream()
                .map(Forbidden::getWord)
                .map(String::toLowerCase)
                .collect(Collectors.toList());
    }
}
