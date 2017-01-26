package com.nodomain.savewords.tasks;


import android.os.Handler;

import com.nodomain.savewords.YandexService;

import java.io.IOException;


public class TranslateWordTask extends Task {

    private YandexService yandexService = new YandexService();

    public TranslateWordTask(Handler handler) {
        super(handler);
    }

    public void translate(final TranslateWordTaskListener listener, final String word) {
        doInNewThread(new Runnable() {
            @Override
            public void run() {
                try {
                    String definedLanguage = yandexService.defineLanguage(word);
                    final String translatedWord;

                    if (definedLanguage.equals("en")) {
                        translatedWord = yandexService.translateWordFromEnglishToRussian(word);
                    } else {
                        translatedWord = yandexService.translateWordFromRussianToEnglish(word);
                    }

                    post(new Runnable() {
                        @Override
                        public void run() {
                            listener.wordTranslated(translatedWord);
                        }
                    });
                } catch (IOException e) {
                    post(new Runnable() {
                        @Override
                        public void run() {
                            listener.wordTranslateFail();
                        }
                    });
                }
            }
        });
    }
}
