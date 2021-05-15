package com.marketing.entity;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="product")
public class Product  implements Serializable {

    @Id
    private int id;

    private String thumbnail;
    private String Image;
    private String name;
    private Date date;
    private String description;

    @ManyToMany(mappedBy = "product", fetch= FetchType.EAGER)
    private List<Question> questions;




}
