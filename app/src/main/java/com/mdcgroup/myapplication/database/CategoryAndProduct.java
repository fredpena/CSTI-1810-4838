package com.mdcgroup.myapplication.database;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class CategoryAndProduct {

    @Embedded
    private Category category;

    @Relation(
            parentColumn = "uid",
            entityColumn = "category",
            entity = Product.class
    )
    private List<Product> products;

    public CategoryAndProduct(Category category, List<Product> products) {
        this.category = category;
        this.products = products;
    }

    public Category category() {
        return category;
    }

    public List<Product> products() {
        return products;
    }
}
