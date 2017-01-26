package com.nodomain.savewords.activities;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nodomain.savewords.AudioPlayer;
import com.nodomain.savewords.R;
import com.nodomain.savewords.Word;
import com.nodomain.savewords.WordsStorage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ListenTestActivity extends AppCompatActivity {

    private TextView textViewWordNumber;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;

    private WordsStorage wordsStorage;
    private List<Word> words;
    private AudioPlayer audioPlayer;
    private int currentWordIndex = 0;
    private int rightWordIndex;
    private Word rightWord;
    private boolean[] answers;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listen_test);

        initViews();
        setListeners();
        wordsStorage = WordsStorage.getInstance(this);
        words = new ArrayList<>(WordsStorage.getInstance(this).getWordsFromCategory(getCategoryIdFromIntent()));

        if (words.size() == 0) {
            Toast.makeText(this, "Нет добавленных слов", Toast.LENGTH_SHORT).show();
            finish();
        }

        Collections.shuffle(words);
        if (words.size() > 7) {
            words = words.subList(0, 6);
        }
        answers = new boolean[words.size()];

        audioPlayer = new AudioPlayer();

        showNextWord();
    }

    private void initViews() {
        textViewWordNumber = (TextView) findViewById(R.id.textView_word_number);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
    }

    private void setListeners() {
        findViewById(R.id.imageView_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        findViewById(R.id.imageButton_sound).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playSound();
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer(0);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer(1);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer(2);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer(3);
            }
        });
    }

    private int getCategoryIdFromIntent() {
        return getIntent().getIntExtra("category_id", 0);
    }

    private void playSound() {
        if (rightWord.getSoundFilePath() != null) {
            audioPlayer.playFile(rightWord.getSoundFilePath());
        } else {
            audioPlayer.playRes(this, rightWord.getSoundRes());
        }
    }

    private void answer(int answerIndex) {
        if (answerIndex == rightWordIndex) {
            answers[currentWordIndex] = true;
            Toast.makeText(this, "Правильно", Toast.LENGTH_SHORT).show();
        } else {
            answers[currentWordIndex] = false;
            Toast.makeText(this, "Неправильно", Toast.LENGTH_SHORT).show();
        }

        if (currentWordIndex == words.size() - 1) {
            showStatistics();
            return;
        }

        currentWordIndex++;
        showNextWord();
    }

    private void showNextWord() {
        textViewWordNumber.setText("Вопрос " + (currentWordIndex+1) + " из " + words.size());

        rightWord = words.get(currentWordIndex);
        rightWordIndex = new Random().nextInt(4);
        List<Word> randomWords = wordsStorage.getRandomWordsExcept(4, rightWord);

        button1.setText(randomWords.get(0).getEn());
        button2.setText(randomWords.get(1).getEn());
        button3.setText(randomWords.get(2).getEn());
        button4.setText(randomWords.get(3).getEn());

        switch (rightWordIndex) {
            case 0:
                button1.setText(rightWord.getEn());
                break;
            case 1:
                button2.setText(rightWord.getEn());
                break;
            case 2:
                button3.setText(rightWord.getEn());
                break;
            case 3:
                button4.setText(rightWord.getEn());
                break;
        }
    }

    private void showStatistics() {
        StringBuilder statistics = new StringBuilder();
        for (int i = 0; i < answers.length; i++) {
            statistics.append((i+1) + ") ");
            if (answers[i]) {
                statistics.append("правильно\n");
            } else {
                statistics.append("неправильно\n");
            }
        }

        Intent intent = new Intent(this, StatisticsActivity.class);
        intent.putExtra("statistics", statistics.toString());
        startActivity(intent);

        finish();
    }
}
