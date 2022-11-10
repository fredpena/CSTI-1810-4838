package com.mdcgroup.myapplication.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey//(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "word")
    private String mWord;

    public Word(@NonNull String mWord) {
        this.mWord = mWord;
    }

    public String getWord() {
        return mWord;
    }
}
