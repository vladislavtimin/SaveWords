package com.nodomain.savewords.activities;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nodomain.savewords.AudioPlayer;
import com.nodomain.savewords.R;
import com.nodomain.savewords.Word;
import com.nodomain.savewords.WordsStorage;

import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    private ImageView imageViewPicture;
    private ImageButton imageButtonPrev;
    private ImageButton imageButtonNext;
    private TextView textViewTitle;
    private TextView textViewRu;
    private TextView textViewEn;

    private List<Word> words;
    private AudioPlayer audioPlayer;

    private int currentWordIndex = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        initViews();
        setListeners();
        int categoryId = getCategoryId();
        textViewTitle.setText(WordsStorage.getInstance(this).getCategories().get(categoryId).getName());
        words = WordsStorage.getInstance(this).getWordsFromCategory(categoryId);
        Log.e("MyTag", words.size() + "");
        audioPlayer = new AudioPlayer();

        if (words.size() > 0) {
            showWord(words.get(currentWordIndex));

            if (words.size() == 1) {
                imageButtonNext.setEnabled(false);
                imageButtonNext.setAlpha(0.4f);
            }
        } else {
            Toast.makeText(this, "Нет добавленных слов", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void initViews() {
        imageViewPicture = (ImageView) findViewById(R.id.imageView_picture);
        imageButtonPrev = (ImageButton) findViewById(R.id.imageButton_prev);
        imageButtonNext = (ImageButton) findViewById(R.id.imageButton_next);
        textViewTitle = (TextView) findViewById(R.id.textView_title);
        textViewRu = (TextView) findViewById(R.id.textView_ru);
        textViewEn = (TextView) findViewById(R.id.textView_en);

        imageButtonPrev.setEnabled(false);
        imageButtonPrev.setAlpha(0.4f);
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

        imageButtonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPrevWord();
            }
        });

        imageButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNextWord();
            }
        });
    }

    private void playSound() {
        if (words.get(currentWordIndex).getSoundFilePath() != null) {
            audioPlayer.playFile(words.get(currentWordIndex).getSoundFilePath());
        } else {
            audioPlayer.playRes(this, words.get(currentWordIndex).getSoundRes());
        }
    }

    private void showPrevWord() {
        currentWordIndex--;
        showWord(words.get(currentWordIndex));
        if (currentWordIndex == 0) {
            imageButtonPrev.setEnabled(false);
            imageButtonPrev.setAlpha(0.4f);
        }
        if (currentWordIndex == words.size()-2) {
            imageButtonNext.setEnabled(true);
            imageButtonNext.setAlpha(1f);

        }
    }

    private void showNextWord() {
        currentWordIndex++;
        showWord(words.get(currentWordIndex));
        if (currentWordIndex == words.size()-1) {
            imageButtonNext.setEnabled(false);
            imageButtonNext.setAlpha(0.4f);
        }
        if (currentWordIndex == 1) {
            imageButtonPrev.setEnabled(true);
            imageButtonPrev.setAlpha(1f);
        }
    }

    private void showWord(Word word) {
        if (word.getImageFilePath() != null) {
            imageViewPicture.setImageURI(Uri.parse(word.getImageFilePath()));
        } else {
            imageViewPicture.setImageResource(word.getImageRes());
        }

        textViewRu.setText(word.getRu());
        textViewEn.setText(word.getEn());
    }

    private int getCategoryId() {
        return getIntent().getIntExtra("category_id", -1);
    }
}
