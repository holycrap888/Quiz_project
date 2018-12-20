package com.example.minutecrazy.project;


import android.content.Intent;
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
import com.example.minutecrazy.project.ViewHolder.CategoryViewHolder;
import com.example.minutecrazy.project.ViewHolder.RecyclerTouchListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */

public class CategoryFragment extends Fragment {

    View myFragment;

    private List<Category> categoryLists;
    private RecyclerView listCategory;
    private RecyclerView.LayoutManager layoutManager;

    public static CategoryFragment newInstance(){
        CategoryFragment categoryFragment = new CategoryFragment();
        return categoryFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myFragment = inflater.inflate(R.layout.fragment_category,container,false);

        listCategory = myFragment.findViewById(R.id.listCategories);
        listCategory.setHasFixedSize(true);
        categoryLists = new ArrayList<>();
        layoutManager = new LinearLayoutManager(getActivity());
        listCategory.setLayoutManager(layoutManager);
        listCategory.setItemAnimator(new DefaultItemAnimator());
        listCategory.addOnItemTouchListener(new RecyclerTouchListener(getContext(), listCategory, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Category category = categoryLists.get(position);
                Toast.makeText(getContext(),category.getName(),Toast.LENGTH_LONG).show();
                Intent startQuiz = new Intent(getContext(),PlayActivity.class);
                Common.categoryId = category.getId();
                startQuiz.putExtra("CateId",category.getId());
                startActivity(startQuiz);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        loadCategory();
        


        return myFragment;
    }
    private void loadCategory() {
        //Toast.makeText(getContext(),"Fragment",Toast.LENGTH_LONG).show();

        String url = Category.BASE_URL+"get_post.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getContext(),"aaaaaaAAAAAAAAAAAAAA",Toast.LENGTH_LONG).show();
                try {
                    JSONArray array = new JSONArray(response);
                    for(int i = 0;i<array.length();i++){
                        JSONObject oak = array.getJSONObject(i);
                        categoryLists.add(new Category(
                                oak.getString("id"),
                                oak.getString("name"),
                                oak.getString("image")
                        ));
                        CategoryViewHolder categoryViewHolder = new CategoryViewHolder(categoryLists,getContext());
                        //CategoryViewHolder categoryViewHolder = new CategoryViewHolder(categoryLists,getContext());
                        listCategory.setAdapter(categoryViewHolder);
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
