package com.mdcgroup.myapplication.database;

import androidx.lifecycle.LiveData;
import androidx.room.*;

import java.util.List;

@Dao
public interface WordDao {

    @Query("SELECT * FROM category_table where uid = :category")
    CategoryAndProduct find(int category);
    @Query("SELECT * FROM category_table where uid = :category")
    Category findCategory(int category);
    @Insert
    void insert(Product product);

    @Insert
    void insertCategory(Category category);

    @Delete
    @Transaction
    void delete(Product product);

    @Update
    @Transaction
    void update(Product product);


    @Query("DELETE FROM product_table")
    void deleteAll();

    @Query("SELECT * from product_table ORDER BY name ASC")
    LiveData<List<Product>> getAllWords();

}
