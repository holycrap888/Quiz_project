package com.example.minutecrazy.project;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.minutecrazy.project.Common.Common;
import com.example.minutecrazy.project.Model.Category;
import com.example.minutecrazy.project.Model.Score;
import com.example.minutecrazy.project.ViewHolder.ScoreViewHolder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */

public class RankingFragment extends Fragment {

    View myFragment;

    private List<Score> scoreList;
    private RecyclerView ll;
    private RecyclerView.LayoutManager layoutManager;

    public static RankingFragment newInstance(){
        RankingFragment rankingFragment = new RankingFragment();
        return rankingFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myFragment = inflater.inflate(R.layout.fragment_ranking,container,false);

        ll = myFragment.findViewById(R.id.scoreRecycle);
        ll.setHasFixedSize(true);
        scoreList = new ArrayList<>();
        layoutManager = new LinearLayoutManager(getActivity());
        ll.setLayoutManager(layoutManager);
        ll.setItemAnimator(new DefaultItemAnimator());

        loadCategory();



        return myFragment;
    }
    private void loadCategory() {


        String url = Category.BASE_URL+"get_score_id.php?username="+Common.currentUser.getUserName();
        Toast.makeText(getContext(),url,Toast.LENGTH_LONG).show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getContext(),"aaaaaaAAAAAAAAAAAAAA",Toast.LENGTH_LONG).show();
                try {
                    JSONArray array = new JSONArray(response);
                    for(int i = 0;i<array.length();i++){
                        JSONObject oak = array.getJSONObject(i);
                        scoreList.add(new Score(
                                oak.getString("id"),
                                oak.getString("username"),
                                oak.getString("categoryId"),
                                oak.getString("score")
                        ));
                        ScoreViewHolder scoreViewHolder = new ScoreViewHolder(scoreList,getContext());
                        //CategoryViewHolder categoryViewHolder = new CategoryViewHolder(categoryLists,getContext());
                        ll.setAdapter(scoreViewHolder);
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
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

    }


}
