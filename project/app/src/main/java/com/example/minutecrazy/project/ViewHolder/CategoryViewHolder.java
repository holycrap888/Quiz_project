package com.example.minutecrazy.project.ViewHolder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.minutecrazy.project.Model.Category;
import com.example.minutecrazy.project.R;

import java.util.List;

public class CategoryViewHolder extends RecyclerView.Adapter<CategoryViewHolder.MyViewHolder>{

    private List<Category> categoriesList;
    View itemView;
    private Context context;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView cate_name;
        public ImageView cate_img;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cate_img = itemView.findViewById(R.id.category_image);
            cate_name = itemView.findViewById(R.id.category_name);
        }
    }

    public CategoryViewHolder(List<Category> categories, Context context) {
        this.categoriesList = categories;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.category_layout,viewGroup,false);
        return new MyViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        Category category = categoriesList.get(i);
        holder.cate_name.setText(category.getName());
        Glide.with(itemView.getContext()).load(category.getImage()).into(holder.cate_img);
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }




}
