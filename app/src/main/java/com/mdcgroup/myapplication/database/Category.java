package com.mdcgroup.myapplication.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "category_table")
public class Category implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "uid")
    private int uid;
    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    public Category(@NonNull String name) {
        this.name = name;
    }

    public int uid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @NonNull
    public String name() {
        return name;
    }

    public Category setName(@NonNull String name) {
        this.name = name;
        return this;
    }

    //private List<Product> products;
}
