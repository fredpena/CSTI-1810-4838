package com.mdcgroup.myapplication.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "product_table")
public class Product implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "uid")
    private int uid;
    @NonNull
    @ColumnInfo(name = "name")
    private String name;
    @NonNull
    @ColumnInfo(name = "brand")
    private String brand;
    @NonNull
    @ColumnInfo(name = "price")
    private Double price;

    @ColumnInfo(name = "image")
    private String image;

    @NonNull
    @ColumnInfo(name = "category")
    private int category;

    //private Category category;

    public Product(@NonNull String name, @NonNull String brand, @NonNull Double price, @NonNull int category) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.category = category;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public Product setName(@NonNull String name) {
        this.name = name;
        return this;
    }

    @NonNull
    public String getBrand() {
        return brand;
    }

    public Product setBrand(@NonNull String brand) {
        this.brand = brand;
        return this;
    }

    @NonNull
    public Double getPrice() {
        return price;
    }

    public Product setPrice(@NonNull Double price) {
        this.price = price;
        return this;
    }

    public int category() {
        return category;
    }

    public Product setCategory(int category) {
        this.category = category;
        return this;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Product{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", category=" + category +
                '}';
    }
}
