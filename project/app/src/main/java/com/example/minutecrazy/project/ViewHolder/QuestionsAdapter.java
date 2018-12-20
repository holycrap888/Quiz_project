package com.example.minutecrazy.project.ViewHolder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minutecrazy.project.Common.Common;
import com.example.minutecrazy.project.Model.Questions;
import com.example.minutecrazy.project.R;

import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.MyViewHolder>{

    private List<Questions> questionsList;
    private View itemView;
    private Context context;
    private RadioButton rtx;
    private String[] score = {"","","","","","","","",""};


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView question1;
        public RadioGroup r_g;
        public RadioButton r_a,r_b,r_c,r_d,rtx;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            question1 = itemView.findViewById(R.id.questionText1);
            r_g = itemView.findViewById(R.id.ans_question1);
            r_a = itemView.findViewById(R.id.btnAnsA1);
            r_b = itemView.findViewById(R.id.btnAnsB1);
            r_c = itemView.findViewById(R.id.btnAnsC1);
            r_d = itemView.findViewById(R.id.btnAnsD1);

        }
    }


    public QuestionsAdapter(List<Questions> questionsList, Context context) {
        this.questionsList = questionsList;
    }
    public int getItemCount() {
        return questionsList.size();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.quesyion_item,parent,false);
        return new QuestionsAdapter.MyViewHolder(itemView);
    }

    public void onBindViewHolder(@NonNull final QuestionsAdapter.MyViewHolder holder, final int i) {
        final Questions questions = questionsList.get(i);
        holder.question1.setText(questions.getQuestion());
        holder.r_a.setText(questions.getAnsA());
        holder.r_b.setText(questions.getAnsB());
        holder.r_c.setText(questions.getAnsC());
        holder.r_d.setText(questions.getAnsD());


        holder.r_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(itemView.getContext(),questions.getAnsA(),Toast.LENGTH_LONG).show();
                if(questions.getAnsA().equalsIgnoreCase(questions.getTrueAns())){
                    Common.Score.add(i,"yes");
                }else {
                    Common.Score.add(i,"no");
                }
            }
        });
        holder.r_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(itemView.getContext(),questions.getAnsB(),Toast.LENGTH_LONG).show();
                if(questions.getAnsB().equalsIgnoreCase(questions.getTrueAns())){
                    Common.Score.add(i,"yes");
                }else {
                    Common.Score.add(i,"no");
                }
            }
        });
        holder.r_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(itemView.getContext(),questions.getAnsC(),Toast.LENGTH_LONG).show();
                if(questions.getAnsC().equalsIgnoreCase(questions.getTrueAns())){
                    Common.Score.add(i,"yes");
                }else {
                    Common.Score.add(i,"no");
                }
            }
        });
        holder.r_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(itemView.getContext(),questions.getAnsD(),Toast.LENGTH_LONG).show();
                if(questions.getAnsD().equalsIgnoreCase(questions.getTrueAns())){
                    Common.Score.add(i,"yes");
                }else {
                    Common.Score.add(i,"no");
                }
            }
        });

    }
}

