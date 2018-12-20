package com.example.minutecrazy.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.minutecrazy.project.Model.Category;

import java.util.HashMap;
import java.util.Map;

public class AddQuestionActivity extends AppCompatActivity {

    private EditText question,ansA,ansB,ansC,ansD,ansTrue;
    private Button btn_cancle,btn_addQ;
    private String categoryID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        Bundle extra = getIntent().getExtras();
        categoryID = extra.getString("xxx");



        question= findViewById(R.id.addques);
        ansA= findViewById(R.id.addA);
        ansB= findViewById(R.id.addB);
        ansC= findViewById(R.id.addC);
        ansD= findViewById(R.id.addD);
        ansTrue= findViewById(R.id.addTrue);

        btn_addQ = findViewById(R.id.addQu);
        btn_cancle = findViewById(R.id.backQ);

        btn_addQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addQuestions(question.getText().toString(),ansA.getText().toString(),ansB.getText().toString()
                        ,ansC.getText().toString(),ansD.getText().toString(),ansTrue.getText().toString(),categoryID);

                Toast.makeText(getApplicationContext(),"ADD SUCCESS",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(AddQuestionActivity.this,Home_addActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddQuestionActivity.this,Home_addActivity.class);
                startActivity(intent);
                finish();
            }
        });




    }

    private void addQuestions(final String x1, final String x2, final String x3, final String x4,
                              final String x5, final String x6,final String x7) {
        Toast.makeText(getApplicationContext(),x7,Toast.LENGTH_LONG).show();
        String url = Category.BASE_URL+"add_question.php";
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
                params.put("question", x1);
                params.put("ansA", x2);
                params.put("ansB", x3);
                params.put("ansC", x4);
                params.put("ansD", x5);
                params.put("trueAns", x6);
                params.put("categoryId", x7);


                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

}

