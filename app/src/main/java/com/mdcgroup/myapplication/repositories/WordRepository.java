package com.mdcgroup.myapplication.repositories;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.mdcgroup.myapplication.database.AppDatabase;
import com.mdcgroup.myapplication.database.Word;
import com.mdcgroup.myapplication.database.WordDao;

import java.util.List;

public class WordRepository {
    private WordDao wordDao;
    private LiveData<List<Word>> mAllWords;


    public WordRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        wordDao = db.wordDao();
        mAllWords = wordDao.getAllWords();
    }

    public void insert(Word word) {
        AppDatabase.databaseWriteExecutor.execute(() ->
                wordDao.insert(word));
    }

    public void deleteAll() {
        AppDatabase.databaseWriteExecutor.execute(() ->
                wordDao.deleteAll());
    }

    public LiveData<List<Word>> findAll() {
        return mAllWords;
    }


}
