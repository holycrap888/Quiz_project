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
import com.example.minutecrazy.project.ViewHolder.QuestionsAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PlayActivity extends AppCompatActivity {

    final static long INTERVAL = 1000; //1sec
    final static long TIMEOUT = 10000; //10sec

    int score = 0 ,correct = 0,total = 0;

    private RecyclerView recyclerView;

    private String cateId;
    private List<Questions> questionsList ;
    private RecyclerView.LayoutManager layoutManager;
    private Button btn_go;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        questionsList = new ArrayList<>();

        Bundle bundle =getIntent().getExtras();
        if (bundle != null) {
            cateId = bundle.getString("CateId");
            //Toast.makeText(getApplicationContext(),cateId,Toast.LENGTH_LONG).show();
        }




        recyclerView = findViewById(R.id.questionRecycle);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        loadQuestions();


        btn_go = findViewById(R.id.btn_send);
        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),Common.Score.toString(),Toast.LENGTH_LONG).show();

                for(int i=0;i<Common.Score.size();i++){
                    if (Common.Score.get(i) == "yes"){
                        correct++;
                        score +=10;
                    }else {
                        score +=0;
                    }
                }

                Toast.makeText(getApplicationContext(),String.valueOf(score),Toast.LENGTH_LONG).show();
                total = Common.Score.size();

                Intent intent = new Intent(PlayActivity.this,Done.class);
                intent.putExtra("cateId",String.valueOf(cateId));
                intent.putExtra("Score",String.valueOf(score));
                intent.putExtra("Correct",String.valueOf(correct));
                intent.putExtra("Total",String.valueOf(total));
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

                        QuestionsAdapter questionsAdapter = new QuestionsAdapter(questionsList,getApplicationContext());
                        recyclerView.setAdapter(questionsAdapter);

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
