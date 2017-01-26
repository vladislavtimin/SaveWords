package com.nodomain.savewords;


import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.nodomain.savewords.database.SqlDatabase;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class WordsStorage implements Words {

    private static WordsStorage instance;

    private YandexService yandexService;
    private SqlDatabase sqlDatabase;
    private FileManager fileManager;

    public static WordsStorage getInstance(Context context) {
        if (instance == null) {
            instance = new WordsStorage(context);
        }

        return instance;
    }

    private WordsStorage(Context context) {
        yandexService = new YandexService();
        sqlDatabase = new SqlDatabase(context.getApplicationContext());
        fileManager = new FileManager();

        added.addAll(sqlDatabase.getAddedWords());
    }

    public List<Category> getCategories() {
        return categories;
    }

    public List<Word> getWordsFromCategory(int categoryId) {
        Log.e("MyTag", categories.size() + " " + categories.get(categoryId).getWords().size());
        return categories.get(categoryId).getWords();
    }

    public List<Word> getRandomWordsExcept(int count, Word exceptWord) {
        Collections.shuffle(allWords);

        List<Word> randomWords = new ArrayList<>();
        int i = 0;
        while (randomWords.size() < count) {
            Word randomWord = allWords.get(i);
            if (!randomWord.getEn().equals(exceptWord.getEn())) {
                randomWords.add(randomWord);
            }
            i++;
        }

        return randomWords;
    }

    public void addWord(String addedWord, byte[]image) throws IOException {
        String lang = yandexService.defineLanguage(addedWord);

        byte[] sound;
        String ru;
        String en;
        if (lang == YandexService.Languages.ENGLISH) {
            en = addedWord;
            ru = yandexService.translateWordFromEnglishToRussian(en);
            sound = yandexService.englishWordToSpeech(en);
        } else {
            ru = addedWord;
            en = yandexService.translateWordFromRussianToEnglish(ru);
            sound = yandexService.englishWordToSpeech(en);
        }

        File imageFile = fileManager.makePictureFile(en, ".jpg");
        File soundFile = fileManager.makeSoundFile(en);

        fileManager.saveDataToFile(image, imageFile);
        fileManager.saveDataToFile(sound, soundFile);

        Word word = new Word(ru, en, imageFile.getAbsolutePath(), soundFile.getAbsolutePath(), 0, 0);

        sqlDatabase.addWord(word);
        added.add(word);
    }
}
