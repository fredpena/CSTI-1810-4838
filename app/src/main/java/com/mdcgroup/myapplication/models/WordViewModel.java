package com.mdcgroup.myapplication.models;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.mdcgroup.myapplication.database.AppDatabase;
import com.mdcgroup.myapplication.database.Word;
import com.mdcgroup.myapplication.repositories.WordRepository;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    private WordRepository mRepository;

    private LiveData<List<Word>> mAllWords;

    public WordViewModel(@NonNull @NotNull Application application) {
        super(application);

        mRepository = new WordRepository(application);

        mAllWords = mRepository.findAll();
    }

    public void insert(Word word) {
        mRepository.insert(word);
    }

    public void deleteAll() {
        mRepository.deleteAll();
    }

    public LiveData<List<Word>> findAll() {
        return mAllWords;
    }


}
