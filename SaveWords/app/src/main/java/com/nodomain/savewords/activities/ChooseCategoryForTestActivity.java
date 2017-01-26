package com.nodomain.savewords.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.nodomain.savewords.Category;
import com.nodomain.savewords.R;
import com.nodomain.savewords.WordsStorage;
import com.nodomain.savewords.list.CategoriesAdapter;
import com.nodomain.savewords.list.OnItemClickListener;

import java.util.List;

public class ChooseCategoryForTestActivity extends AppCompatActivity implements OnItemClickListener {

    private RecyclerView recyclerViewCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_category_for_test);

        List<Category> categories = WordsStorage.getInstance(this).getCategories();

        recyclerViewCategories = (RecyclerView) findViewById(R.id.recyclerView_categories);
        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(categories, this);
        recyclerViewCategories.setAdapter(categoriesAdapter);

        findViewById(R.id.imageView_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = null;

        switch (getTestTypeFromIntent()) {
            case TestsActivity.TEST_TYPE_FIND:
                intent = new Intent(this, FindTestActivity.class);
                break;
            case TestsActivity.TEST_TYPE_LISTEN:
                intent = new Intent(this, ListenTestActivity.class);
                break;
            case TestsActivity.TEST_TYPE_COMPARE:
                intent = new Intent(this, CompareTestActivity.class);
                break;
        }

        intent.putExtra("category_id", position);
        startActivity(intent);
        finish();
    }

    private int getTestTypeFromIntent() {
        return getIntent().getIntExtra("test_type", 0);
    }
}
