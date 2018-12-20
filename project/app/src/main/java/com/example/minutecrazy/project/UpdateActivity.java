package com.example.minutecrazy.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.minutecrazy.project.Common.Common;
import com.example.minutecrazy.project.Model.Category;
import com.example.minutecrazy.project.Model.Questions;
import com.example.minutecrazy.project.ViewHolder.QuestionsUpdateAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UpdateActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button btn_back,btn_addq;
    private List<Questions> questionsList ;
    private RecyclerView.LayoutManager layoutManager;
    private String cateId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        questionsList = new ArrayList<>();

        btn_addq= findViewById(R.id.addQ);
        btn_back = findViewById(R.id.back);
        recyclerView = findViewById(R.id.updateRecycle);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        cateId = Common.categoryId;



        loadQuestions();
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateActivity.this,Home_addActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_addq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateActivity.this,AddQuestionActivity.class);
                intent.putExtra("xxx",String.valueOf(cateId));
                startActivity(intent);
                finish();
            }
        });

    }


    private void loadQuestions() {

        String url = Category.BASE_URL+"get_question_category.php?cateId="+cateId;
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getContext(),"aaaaaaAAAAAAAAAAAAAA",Toast.LENGTH_LONG).show();
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
                        QuestionsUpdateAdapter questionsUpdateAdapter = new QuestionsUpdateAdapter(questionsList,getApplicationContext());
                        recyclerView.setAdapter(questionsUpdateAdapter);

                    }
                }  catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
                    }
                });/*{
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("cateId",cateId);
                return super.getParams();
            }

        };*/

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
//        Toast.makeText(getApplicationContext(),"oak",Toast.LENGTH_LONG).show();
        //Toast.makeText(getApplicationContext(),url,Toast.LENGTH_LONG).show();

    }
}
