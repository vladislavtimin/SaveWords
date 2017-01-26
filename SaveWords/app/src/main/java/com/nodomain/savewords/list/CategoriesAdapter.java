package com.nodomain.savewords.list;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nodomain.savewords.Category;
import com.nodomain.savewords.R;

import java.util.List;


public class CategoriesAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    private List<Category> categories;
    private OnItemClickListener listener;

    public CategoriesAdapter(List<Category> categories, OnItemClickListener listener) {
        this.categories = categories;
        this.listener = listener;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        holder.imageViewPicture.setImageResource(categories.get(position).getImageRes());
        holder.textViewName.setText(categories.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
