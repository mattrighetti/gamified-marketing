package com.marketing.bean;

import com.marketing.entity.Product;

import javax.ejb.Stateless;

@Stateless
public class ProductBean extends AbstractFacade<Product> {
    public ProductBean() {
        super(Product.class);
    }

    public Product getProductOfTheDay() {
        // TODO for simplicity and test purposes this is just getting the first product in the database
        return this.find(1);
    }
}
