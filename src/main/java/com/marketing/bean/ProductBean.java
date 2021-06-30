package com.marketing.bean;

import com.marketing.entity.Product;
import com.marketing.utils.DateUtils;

import javax.ejb.Stateless;
import java.util.Date;
import java.util.List;

@Stateless
public class ProductBean extends AbstractFacade<Product> {
    public ProductBean() {
        super(Product.class);
    }

    public Product getProductOfTheDay() {
        long startOfToday = DateUtils.atStartOfDayUnix(new Date());
        return getProductByDate(startOfToday);
    }

    public Product getProduct(long id) {
        return find(id);
    }

    public Product getProductByDate(long date) {
        List<Product> products = getEntityManager()
                .createNamedQuery("Product.selectProductByDate", Product.class)
                .setParameter("date", date)
                .getResultList();

        if (!products.isEmpty())
            return products.get(0);
        else
            return null;
    }

    public Product addProduct(String name, long date, String description) {
        Product newProduct = new Product();
        newProduct.setName(name);
        newProduct.setDate(date);
        newProduct.setDescription(description);
        //TODO placeholder image url should not be hardcoded
        newProduct.setImage("https://images-eu.ssl-images-amazon.com/images/G/29/X-Site/2021/PD21/shop-all-deals-cat-card-low-res._SY304_CB666436135_.jpg");
        create(newProduct);
        getEntityManager().flush();
        return newProduct;
    }
}
