package com.nodomain.savewords.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.nodomain.savewords.R;
import com.nodomain.savewords.Word;
import com.nodomain.savewords.WordsStorage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompareTestActivity extends AppCompatActivity {

    private static final int COLOR_CHECKED = 0xff00ff00;
    private static final int COLOR_NORMAL = 0xffc7c7c7;

    private ListView listViewLeft;
    private ListView listViewRight;

    private SimpleAdapter leftWordsAdapter;
    private SimpleAdapter rightWordsAdapter;
    private List<Map<String, String>> leftData;
    private List<Map<String, String>> rightData;

    private List<Word> leftWords;
    private List<Word> rightWords;

    private int leftCheckedWordIndex = -1;
    private int rightCheckedWordIndex = -1;

    private int wrongAnswersCount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_test);

        leftWords = new ArrayList<>(WordsStorage.getInstance(this).getWordsFromCategory(getCategoryIdFromIntent()));

        if (leftWords.size() == 0) {
            Toast.makeText(this, "Нет добавленных слов", Toast.LENGTH_SHORT).show();
            finish();
        }


        Collections.shuffle(leftWords);
        if (leftWords.size() > 7) {
            leftWords = leftWords.subList(0, 6);
        }

        rightWords = new ArrayList<>(leftWords);
        Collections.shuffle(rightWords);

        initViews();
        setAdapters();
        setListeners();
    }

    private void initViews() {
        listViewLeft = (ListView) findViewById(R.id.listView_left);
        listViewRight = (ListView) findViewById(R.id.listView_right);
    }

    private void setAdapters() {
        leftWordsAdapter = createLeftAdapter();
        rightWordsAdapter = createRightAdapter();

        listViewLeft.setAdapter(leftWordsAdapter);
        listViewRight.setAdapter(rightWordsAdapter);
    }

    private void setListeners() {
        listViewLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                onLeftWordClick(view, i);
            }
        });

        listViewRight.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                onRightWordClick(view, i);
            }
        });

        findViewById(R.id.imageView_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private int getCategoryIdFromIntent() {
        return getIntent().getIntExtra("category_id", 0);
    }

    private void onLeftWordClick(View view, int position) {
        if (leftCheckedWordIndex == -1) {
            leftCheckedWordIndex = position;
            view.setBackgroundColor(COLOR_CHECKED);

            if (rightCheckedWordIndex != -1) {
                checkComparison();
            }
        } else {
            listViewLeft.getChildAt(leftCheckedWordIndex).setBackgroundColor(COLOR_NORMAL);

            if (position == leftCheckedWordIndex) {
                leftCheckedWordIndex = -1;
            } else {
                leftCheckedWordIndex = position;
                view.setBackgroundColor(COLOR_CHECKED);
            }
        }
    }

    private void onRightWordClick(View view, int position) {
        if (rightCheckedWordIndex == -1) {
            rightCheckedWordIndex = position;
            view.setBackgroundColor(COLOR_CHECKED);

            if (leftCheckedWordIndex != -1) {
                checkComparison();
            }
        } else {
            listViewRight.getChildAt(rightCheckedWordIndex).setBackgroundColor(COLOR_NORMAL);

            if (position == rightCheckedWordIndex) {
                rightCheckedWordIndex = -1;
            } else {
                rightCheckedWordIndex = position;
                view.setBackgroundColor(COLOR_CHECKED);
            }
        }
    }

    private void checkComparison() {
        Word leftWord = leftWords.get(leftCheckedWordIndex);
        Word rightWord = rightWords.get(rightCheckedWordIndex);

        View leftWordView = listViewLeft.getChildAt(leftCheckedWordIndex);
        View rightWordView = listViewRight.getChildAt(rightCheckedWordIndex);

        leftWordView.setBackgroundColor(COLOR_NORMAL);
        rightWordView.setBackgroundColor(COLOR_NORMAL);

        if (leftWord.equals(rightWord)) {
            leftData.remove(leftCheckedWordIndex);
            leftWords.remove(leftCheckedWordIndex);
            leftWordsAdapter.notifyDataSetChanged();

            rightData.remove(rightCheckedWordIndex);
            rightWords.remove(rightCheckedWordIndex);
            rightWordsAdapter.notifyDataSetChanged();

            if (leftWords.size() == 0) {
                showStatistics();
            }
        } else {
            Toast.makeText(this, "Неверно", Toast.LENGTH_SHORT).show();
            wrongAnswersCount++;
        }

        leftCheckedWordIndex = -1;
        rightCheckedWordIndex = -1;
    }

    private void showStatistics() {
        Intent intent = new Intent(this, StatisticsActivity.class);
        intent.putExtra("statistics", "Кол-во неверных попыток: " + wrongAnswersCount);
        startActivity(intent);
        finish();
    }

    private SimpleAdapter createLeftAdapter() {
        leftData = new ArrayList<>();

        HashMap<String, String> hm;
        for (int i = 0; i < leftWords.size(); i++) {
            hm = new HashMap<>();
            hm.put("word", leftWords.get(i).getEn());
            leftData.add(hm);
        }

        String[] from = new String[]{"word"};
        int[] to = new int[]{R.id.textView};

        return new SimpleAdapter(this, leftData, R.layout.item_compare_word, from, to);
    }

    private SimpleAdapter createRightAdapter() {
        rightData = new ArrayList<>();

        HashMap<String, String> hm;
        for (int i = 0; i < rightWords.size(); i++) {
            hm = new HashMap<>();
            hm.put("word", rightWords.get(i).getRu());
            rightData.add(hm);
        }

        String[] from = new String[]{"word"};
        int[] to = new int[]{R.id.textView};

        return new SimpleAdapter(this, rightData, R.layout.item_compare_word, from, to);
    }
}
