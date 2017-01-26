package com.nodomain.savewords.activities;


import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.nodomain.savewords.Category;
import com.nodomain.savewords.R;
import com.nodomain.savewords.WordsStorage;
import com.nodomain.savewords.list.CategoriesAdapter;
import com.nodomain.savewords.list.OnItemClickListener;

import java.util.List;


public class CategoriesActivity extends AppCompatActivity
        implements View.OnClickListener, OnItemClickListener, PopupMenu.OnMenuItemClickListener {

    private RecyclerView recyclerViewCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        List<Category> categories = WordsStorage.getInstance(this).getCategories();

        recyclerViewCategories = (RecyclerView) findViewById(R.id.recyclerView_categories);
        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(categories, this);
        recyclerViewCategories.setAdapter(categoriesAdapter);

        findViewById(R.id.imageView_menu).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.show();
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, CategoryActivity.class);
        intent.putExtra("category_id", position);
        startActivity(intent);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_word:
                startActivity(new Intent(this, AddWordActivity.class));
                return true;
            case R.id.tests:
                startActivity(new Intent(this, TestsActivity.class));
                return true;
            default:
                return false;
        }
    }
}
