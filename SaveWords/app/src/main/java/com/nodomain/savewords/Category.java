package com.nodomain.savewords;


import java.util.List;

public class Category {

    private int imageRes;
    private String name;
    private List<Word> words;

    public Category(int imageRes, String name, List<Word> words) {
        this.imageRes = imageRes;
        this.name = name;
        this.words = words;
    }

    public int getImageRes() {
        return imageRes;
    }

    public String getName() {
        return name;
    }

    public List<Word> getWords() {
        return words;
    }
}
