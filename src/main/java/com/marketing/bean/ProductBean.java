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
                .setParameter("today", date)
                .setParameter("tomorrow",date + 86400)
                .getResultList();

        if (!products.isEmpty())
            return products.get(0);
        else
            return null;
    }

    public Product addProduct(String name, long date, String description, String image) {
        Product newProduct = new Product();
        newProduct.setName(name);
        newProduct.setDate(date);
        newProduct.setDescription(description);
        newProduct.setImage(image);
        create(newProduct);
        getEntityManager().flush();
        return newProduct;
    }
}
