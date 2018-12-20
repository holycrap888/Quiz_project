package com.example.minutecrazy.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.minutecrazy.project.Model.Questions;

import java.util.List;

public class Start extends AppCompatActivity {

    Button btnStart;
    String cateId;
    private int indexs = 0;
    List<Questions> questionsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


       // questionsList = new ArrayList<>();
//        Bundle bundle =getIntent().getExtras();
//        if (bundle != null) {
//            cateId = bundle.getString("CateId");
//            //Toast.makeText(getApplicationContext(),cateId,Toast.LENGTH_LONG).show();
//        }



        //loadQuestion(cateId);

        btnStart = findViewById(R.id.btn_start);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Start.this,PlayActivity.class);
                startActivity(intent);
//                intent.putExtra("CateId",cateId);
                finish();


            }
        });

    }
/*
    private void loadQuestion(String cateId) {

        if (Common.questionsList.size() > 0)
            Common.questionsList.clear();

        String url = "http://172.16.20.109/project/get_question.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(),"aaaaaaAAAAAAAAAAAAAA",Toast.LENGTH_LONG).show();
                try {
                    JSONArray array = new JSONArray(response);
                    for(int i = 0;i<array.length();i++){
                        JSONObject oak = array.getJSONObject(i);
                        questionsList.add(new Questions(
                                oak.getString("id"),
                                oak.getString("question"),
                                oak.getString("ansA"),
                                oak.getString("ansB"),
                                oak.getString("ansC"),
                                oak.getString("ansD"),
                                oak.getString("trueAns"),
                                oak.getString("categoryId")
                        ));



                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);




    }*/
}
