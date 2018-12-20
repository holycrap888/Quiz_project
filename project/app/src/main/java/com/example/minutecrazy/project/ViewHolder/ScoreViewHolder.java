package com.example.minutecrazy.project.ViewHolder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.minutecrazy.project.Model.Score;
import com.example.minutecrazy.project.R;

import java.util.List;

public class ScoreViewHolder extends RecyclerView.Adapter<ScoreViewHolder.MyViewHolder>{

    private List<Score> scoreList;
    View itemView;
    private Context context;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView scoreName,scoreCategory,scoreScore;


        public MyViewHolder(@NonNull View view) {
            super(view);
            scoreName = view.findViewById(R.id.score_name);
            scoreCategory = view.findViewById(R.id.score_cate);
            scoreScore = view.findViewById(R.id.score_score);
        }
    }

    public ScoreViewHolder(List<Score> scores, Context context) {
        this.scoreList = scores;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.score_item,viewGroup,false);
        return new MyViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        Score scores = scoreList.get(i);
        holder.scoreName.setText("Username : "+scores.getUsername());
        holder.scoreCategory.setText("Score : "+scores.getCategoryId());
        holder.scoreScore.setText("Category : "+scores.getScore());


    }

    @Override
    public int getItemCount() {
        return scoreList.size();
    }




}
