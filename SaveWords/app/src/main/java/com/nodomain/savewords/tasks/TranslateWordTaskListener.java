package com.nodomain.savewords.tasks;


public interface TranslateWordTaskListener {

    void wordTranslated(String word);

    void wordTranslateFail();
}
