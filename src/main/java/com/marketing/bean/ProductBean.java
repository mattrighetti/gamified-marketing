package com.marketing.bean;

import com.marketing.entity.Product;
import com.marketing.entity.Review;

import javax.ejb.Stateless;
import java.util.LinkedList;
import java.util.List;

@Stateless
public class ProductBean extends AbstractFacade<Product> {
    public ProductBean() {
        super(Product.class);
    }

    public Product getProductOfTheDay() {
        // TODO for simplicity and test purposes this is just getting the first product in the database
        return this.find(1);
    }

    public Product getProduct(int id) {
        return find(id);
    }
}
