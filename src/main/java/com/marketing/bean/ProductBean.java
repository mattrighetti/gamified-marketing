package com.marketing.bean;

import com.marketing.entity.Product;
import com.marketing.entity.Review;

import javax.ejb.Stateless;
import java.util.Date;
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

    public Product getProductByDate(long date){
        List<Product> products = getEntityManager().createNamedQuery("Product.selectProductByDate", Product.class).setParameter("date",date).getResultList();
        if (products.size() > 0)
            return products.get(0);
        else
            return null;
    }

    public Product addProduct(String name, long date, String description){
        Product newProduct = new Product();
        newProduct.setName(name);
        newProduct.setDate(date);
        newProduct.setDescription(description);
        //placeholder image url
        newProduct.setImage("https://images-eu.ssl-images-amazon.com/images/G/29/X-Site/2021/PD21/shop-all-deals-cat-card-low-res._SY304_CB666436135_.jpg");
        newProduct.setThumbnail("url");
        create(newProduct);
        getEntityManager().flush();
        return newProduct;
    }
}
