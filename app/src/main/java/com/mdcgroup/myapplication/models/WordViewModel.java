package com.mdcgroup.myapplication.models;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.mdcgroup.myapplication.database.Category;
import com.mdcgroup.myapplication.database.CategoryAndProduct;
import com.mdcgroup.myapplication.database.Product;
import com.mdcgroup.myapplication.repositories.WordRepository;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

public class WordViewModel extends AndroidViewModel {

    private WordRepository mRepository;

    private LiveData<List<Product>> mAllWords;

    public WordViewModel(@NonNull @NotNull Application application) {
        super(application);

        mRepository = new WordRepository(application);

        mAllWords = mRepository.findAll();
    }

    public void insert(Product product) {
        mRepository.insert(product);
    }

    public void find(int category, Consumer<CategoryAndProduct> consumer) {
        mRepository.find(category, consumer);
    }

    public void insertCategory(Category category) {
        mRepository.insertCategory(category);
    }

    public void delete(Product product) {
        mRepository.delete(product);
    }

    public void deleteAll(Consumer<Void> consumer) {
        mRepository.deleteAll(consumer);
    }

    public LiveData<List<Product>> findAll() {
        return mAllWords;
    }


    public void update(Product product) {
        mRepository.update(product);
    }
}
