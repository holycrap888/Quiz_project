package com.example.minutecrazy.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.minutecrazy.project.Common.Common;
import com.example.minutecrazy.project.Model.Category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Done extends AppCompatActivity {

    Button btnOk;
    TextView txt_r_score,getTxt_r_question;
    ProgressBar progressBar;
    private String id,s,c_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);

        txt_r_score = findViewById(R.id.txtTotalScore);
        getTxt_r_question = findViewById(R.id.txtTotalQuestion12);
        progressBar = findViewById(R.id.doneProgressBar);
        btnOk = findViewById(R.id.btn_ok);



        Bundle extra = getIntent().getExtras();

            String cateId = extra.getString("cateId");
            final String score = extra.getString("Score");
            String totalQuestion = extra.getString("Total");
            String correct = extra.getString("Correct");

            txt_r_score.setText("Score : "+score);
            getTxt_r_question.setText("Passed : "+correct+" / "+totalQuestion);
            id = Common.currentUser.getUserName();
            s = String.valueOf(score);
            c_id = cateId;
            int a = Integer.valueOf(totalQuestion);
            int b = Integer.valueOf(correct);

            progressBar.setMax(a);
            progressBar.setProgress(b);



        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendData();
                Intent intent = new Intent(Done.this,Home.class);
                startActivity(intent);
                finish();
                Common.Score = new ArrayList<>();
            }
        });

    }

    private void SendData() {
        String url = Category.BASE_URL+"send_score.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("username",id);
                params.put("categoryId", c_id);
                params.put("score",s);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
