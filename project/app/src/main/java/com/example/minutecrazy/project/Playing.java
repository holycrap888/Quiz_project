package com.example.minutecrazy.project;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Playing extends AppCompatActivity implements View.OnClickListener {

    final static long INTERVAL = 1000; //1sec
    final static long TIMEOUT = 10000; //10sec
    int progressValue = 0;

    CountDownTimer mCountDown;

    int indexs = 0,score = 0, thisQuestion = 0, totalQuestion,correctAns;

    ProgressBar progressBar;
    private TextView textScore,textQuestionNum,question_text;
    private Button btnA,btnB,btnC,btnD;
    private String cateId;
    private List<Questions> questionsList ;
    private String[] arr;
    private List<String> list;
    //ArrayList<Questions> questionsArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);
        questionsList = new ArrayList<>();
        Bundle bundle =getIntent().getExtras();
        if (bundle != null) {
            cateId = bundle.getString("CateId");
            //Toast.makeText(getApplicationContext(),cateId,Toast.LENGTH_LONG).show();
        }


        loadQuestions();

        textScore = findViewById(R.id.textScore);
        textQuestionNum = findViewById(R.id.textTotalQuestion);
        question_text = findViewById(R.id.questionText);

        progressBar = findViewById(R.id.progressBar);

        btnA = findViewById(R.id.btnAnsA);
        btnB = findViewById(R.id.btnAnsB);
        btnC = findViewById(R.id.btnAnsC);
        btnD = findViewById(R.id.btnAnsD);

        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnD.setOnClickListener(this);



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
                        Common.questionsList = questionsList;

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
        Toast.makeText(getApplicationContext(),Common.questionsList.get(1).toString(),Toast.LENGTH_LONG).show();
        //Toast.makeText(getApplicationContext(),url,Toast.LENGTH_LONG).show();

    }


    @Override
    public void onClick(View v) {
        mCountDown.cancel();
        if (indexs < totalQuestion){
            Button clickButton = (Button) v;
            if (clickButton.getText().equals(questionsList.get(indexs).getTrueAns())){
                score+=1;
                correctAns++;
                showQuestion(++indexs);

            }else {
                score+=0;
                correctAns+=0;
                showQuestion(++indexs);
            }
        } else {
                Intent intent = new Intent(this,Done.class);
                Bundle dataSend = new Bundle();
                dataSend.putInt("Score",score);
                dataSend.putInt("Total",totalQuestion);
                dataSend.putInt("Correct",correctAns);
                intent.putExtras(dataSend);
                startActivity(intent);
                finish();
            }

            textScore.setText(String.format("%d",score));
        }



    private void showQuestion(int i) {
        //Toast.makeText(getApplicationContext(),questionsArrayList.toString(),Toast.LENGTH_LONG);
//        if (indexs<totalQuestion){
//            thisQuestion++;
//            textQuestionNum.setText(String.format("%d / %d",thisQuestion,totalQuestion));
//            progressBar.setProgress(0);
//            progressValue=0;
//
//            question_text.setText(questionsList.get(indexs).getQuestion());
//            btnA.setText(questionsList.get(indexs).getAnsA());
//            btnB.setText(questionsList.get(indexs).getAnsB());
//            btnC.setText(questionsList.get(indexs).getAnsC());
//            btnD.setText(questionsList.get(indexs).getAnsD());
//
//            mCountDown.start();
//
//        }else {
//            Intent intent = new Intent(this,Done.class);
//            Bundle dataSend = new Bundle();
//            dataSend.putInt("Score",score);
//            dataSend.putInt("Total",totalQuestion);
//            dataSend.putInt("Correct",correctAns);
//            intent.putExtras(dataSend);
//            startActivity(intent);
//            finish();
//        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        totalQuestion = questionsList.size();
        mCountDown = new CountDownTimer(TIMEOUT,INTERVAL) {
            @Override
            public void onTick(long minisec) {
                progressBar.setProgress(progressValue);
                progressValue++;
            }

            @Override
            public void onFinish() {
                mCountDown.cancel();
                showQuestion(++indexs);

            }
        };
        showQuestion(indexs);
    }
}
