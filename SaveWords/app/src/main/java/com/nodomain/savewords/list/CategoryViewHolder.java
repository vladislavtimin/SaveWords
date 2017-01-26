package com.nodomain.savewords.list;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nodomain.savewords.R;

public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView imageViewPicture;
    TextView textViewName;

    private OnItemClickListener listener;

    public CategoryViewHolder(View itemView, OnItemClickListener listener) {
        super(itemView);

        imageViewPicture = (ImageView) itemView.findViewById(R.id.imageView_picture);
        textViewName = (TextView) itemView.findViewById(R.id.textView_name);

        this.listener = listener;

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        listener.onItemClick(getAdapterPosition());
    }
}
