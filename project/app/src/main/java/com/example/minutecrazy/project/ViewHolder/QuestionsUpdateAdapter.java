package com.example.minutecrazy.project.ViewHolder;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.minutecrazy.project.Home_addActivity;
import com.example.minutecrazy.project.Model.Category;
import com.example.minutecrazy.project.Model.Questions;
import com.example.minutecrazy.project.R;

import java.util.List;

public class QuestionsUpdateAdapter extends RecyclerView.Adapter<QuestionsUpdateAdapter.MyViewHolder>{

    private List<Questions> questionsList;
    private View itemView;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public EditText question1;
        public EditText r_g;
        public EditText r_a,r_b,r_c,r_d;
        public Button bt_go,bt_del;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            question1 = itemView.findViewById(R.id.edit_text_name1);
            r_a = itemView.findViewById(R.id.edit_text_A);
            r_b = itemView.findViewById(R.id.edit_text_B);
            r_c = itemView.findViewById(R.id.edit_text_C);
            r_d = itemView.findViewById(R.id.edit_text_D);
            r_g = itemView.findViewById(R.id.edit_text_True);

            bt_go = itemView.findViewById(R.id.insert);
            bt_del = itemView.findViewById(R.id.delete_q);
        }
    }


    public QuestionsUpdateAdapter(List<Questions> questionsList, Context context) {
        this.questionsList = questionsList;
    }
    public int getItemCount() {
        return questionsList.size();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_dialog,parent,false);
        return new QuestionsUpdateAdapter.MyViewHolder(itemView);
    }

    public void onBindViewHolder(@NonNull final QuestionsUpdateAdapter.MyViewHolder holder, final int i) {
        final Questions questions = questionsList.get(i);
        holder.question1.setText(questions.getQuestion());
        holder.r_a.setText(questions.getAnsA());
        holder.r_b.setText(questions.getAnsB());
        holder.r_c.setText(questions.getAnsC());
        holder.r_d.setText(questions.getAnsD());
        holder.r_g.setText(questions.getTrueAns());

        holder.bt_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteQ(questions.getId());
            }
        });





        holder.bt_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateData(questions.getId(),holder.question1.getText().toString(),holder.r_a.getText().toString(),
                        holder.r_b.getText().toString(),holder.r_c.getText().toString(),holder.r_d.getText().toString(),
                        holder.r_g.getText().toString());
            }
        });



    }

    private void DeleteQ(final String asd) {
        String url = Category.BASE_URL+"delete_category_question.php?ids="+asd;
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getContext(),Common.categoryId,Toast.LENGTH_LONG).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String> params = new HashMap<>();
//                params.put("ids", Common.categoryId);
//                return params;
//            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(itemView.getContext());
        requestQueue.add(stringRequest);

        Intent intent = new Intent(itemView.getContext(),Home_addActivity.class);
        itemView.getContext().startActivity(intent);
    }

    private void UpdateData(final String x,final String f,final String a,final String b,final String c,final String aa,final String ss) {
        String url = Category.BASE_URL+"update_category.php?id="+x+"&question="+f+"&ansA="+a+"&ansB="+b+"&ansC="+c+"&ansD="+aa+"&trueAns="+ss;
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getContext(),Common.categoryId,Toast.LENGTH_LONG).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
           // @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String> params = new HashMap<>();
//                params.put("id", String.valueOf(x));
//                params.put("question", String.valueOf(f));
//                params.put("ansA", String.valueOf(a));
//                params.put("ansB", String.valueOf(b));
//                params.put("ansC", String.valueOf(c));
//                params.put("ansD", String.valueOf(d));
//                params.put("trueAns", String.valueOf(e));
//
//                return params;
//            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(itemView.getContext());
        requestQueue.add(stringRequest);

        Intent intent = new Intent(itemView.getContext(),Home_addActivity.class);
        itemView.getContext().startActivity(intent);

    }
}

