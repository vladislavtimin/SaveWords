package com.nodomain.savewords.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.nodomain.savewords.R;


public class TestsActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int TEST_TYPE_FIND = 0;
    public static final int TEST_TYPE_LISTEN = 1;
    public static final int TEST_TYPE_COMPARE = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests);

        findViewById(R.id.button_find).setOnClickListener(this);
        findViewById(R.id.button_listen).setOnClickListener(this);
        findViewById(R.id.button_compare).setOnClickListener(this);

        findViewById(R.id.imageView_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, ChooseCategoryForTestActivity.class);

        switch (view.getId()) {
            case R.id.button_find:
                intent.putExtra("test_type", TEST_TYPE_FIND);
                break;
            case R.id.button_listen:
                intent.putExtra("test_type", TEST_TYPE_LISTEN);
                break;
            case R.id.button_compare:
                intent.putExtra("test_type", TEST_TYPE_COMPARE);
                break;
        }

        startActivity(intent);
    }
}
