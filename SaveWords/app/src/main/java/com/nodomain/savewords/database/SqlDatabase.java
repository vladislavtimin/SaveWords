package com.nodomain.savewords.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nodomain.savewords.Word;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlDatabase {

    private DatabaseHelper databaseHelper;

    public SqlDatabase(Context context) {
        databaseHelper = new DatabaseHelper(context.getApplicationContext());
    }

    public List<Word> getAddedWords() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query("ADDED_WORDS", null, null, null, null, null ,null);

        int ruColumnIndex = cursor.getColumnIndex("ru");
        int enColumnIndex = cursor.getColumnIndex("en");
        int imageFilePathColumnIndex = cursor.getColumnIndex("image_file_path");
        int soundFilePathColumnIndex = cursor.getColumnIndex("sound_file_path");

        List<Word> words = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String ru = cursor.getString(ruColumnIndex);
            String en = cursor.getString(enColumnIndex);
            String imageFilePath = cursor.getString(imageFilePathColumnIndex);
            String soundFilePath = cursor.getString(soundFilePathColumnIndex);

            words.add(new Word(ru, en, imageFilePath, soundFilePath, 0, 0));

            cursor.moveToNext();
        }

        cursor.close();
        databaseHelper.close();

        return words;
    }

    public void addWord(Word word) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("ru", word.getRu());
        cv.put("en", word.getEn());
        cv.put("image_file_path", word.getImageFilePath());
        cv.put("sound_file_path", word.getSoundFilePath());

        sqLiteDatabase.insert("ADDED_WORDS", null, cv);
    }
}
