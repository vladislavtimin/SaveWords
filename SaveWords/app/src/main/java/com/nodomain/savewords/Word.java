package com.nodomain.savewords;


public class Word {

    private String ru;
    private String en;
    private String imageFilePath;
    private String soundFilePath;
    private int soundRes;
    private int imageRes;

    public Word(String ru, String en, String imageFilePath, String soundFilePath, int soundRes, int imageRes) {
        this.ru = ru;
        this.en = en;
        this.imageFilePath = imageFilePath;
        this.soundFilePath = soundFilePath;
        this.soundRes = soundRes;
        this.imageRes = imageRes;
    }

    public String getRu() {
        return ru;
    }

    public String getEn() {
        return en;
    }

    public String getImageFilePath() {
        return imageFilePath;
    }

    public String getSoundFilePath() {
        return soundFilePath;
    }

    public int getSoundRes() {
        return soundRes;
    }

    public int getImageRes() {
        return imageRes;
    }
}
