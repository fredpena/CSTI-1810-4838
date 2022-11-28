package com.mdcgroup.myapplication.repositories;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.mdcgroup.myapplication.database.*;

import java.util.List;
import java.util.function.Consumer;

public class WordRepository {
    private WordDao wordDao;
    private LiveData<List<Product>> mAllWords;


    public WordRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        wordDao = db.wordDao();
        mAllWords = wordDao.getAllWords();
    }

    public void insert(Product product) {
        AppDatabase.databaseWriteExecutor.execute(() ->
                wordDao.insert(product));
    }

    public void insertCategory(Category category) {
        AppDatabase.databaseWriteExecutor.execute(() ->
                wordDao.insertCategory(category));
    }

    public void delete(Product product) {
        AppDatabase.databaseWriteExecutor.execute(() ->
                wordDao.delete(product));
    }

    public void deleteAll(Consumer<Void> consumer) {
        AppDatabase.databaseWriteExecutor.execute(() ->
                wordDao.deleteAll());
        consumer.accept(null);
    }

    public void find(int category, Consumer<CategoryAndProduct> consumer) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            consumer.accept(wordDao.find(category));
        });
    }

    public LiveData<List<Product>> findAll() {
        return mAllWords;
    }


    public void update(Product product) {
        AppDatabase.databaseWriteExecutor.execute(() ->
                wordDao.update(product));
    }
}
