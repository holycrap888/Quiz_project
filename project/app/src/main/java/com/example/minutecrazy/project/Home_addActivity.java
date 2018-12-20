package com.example.minutecrazy.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class Home_addActivity extends AppCompatActivity {

    private String a ="";


    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_add);

        Bundle bundle =getIntent().getExtras();
        if (bundle != null) {
            a = bundle.getString("a");
            //Toast.makeText(getApplicationContext(),cateId,Toast.LENGTH_LONG).show();
        }




        bottomNavigationView = findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                Fragment selectedFragment = null;
                switch (menuItem.getItemId()){
                    case R.id.action_category:
                        selectedFragment = Category_adFragment.newInstance();
                        break;
                    case R.id.action_ranking:
                        selectedFragment = Ranking_adFragment.newInstance();
                        break;
                    case R.id.action_add:
                        selectedFragment = AddFragment.newInstance();
                        break;
                    case R.id.logout:
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                }
                if (selectedFragment != null){
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout,selectedFragment);
                transaction.commit();}
                return true;
            }
        });
        //loadCategory();
        setDefaultFragment();
    }

    private void setDefaultFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout,Category_adFragment.newInstance());
        transaction.commit();
    }
//    private void loadCategory() {
//        String url = "http://192.168.1.13/project/get_post.php";
//        StringRequest stringRequest = new StringRequest(Request.Method.GET,
//                url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                //Toast.makeText(getApplicationContext(),"aaaaaaAAAAAAAAAAAAAA",Toast.LENGTH_LONG).show();
////                try {
////                    JSONArray array = new JSONArray(response);
////                    for(int i = 0;i<array.length();i++){
////                        JSONObject oak = array.getJSONObject(i);
////                        categoryList.add(new Category(
////                           oak.getString("name"),
////                                oak.getString("image")
////                        ));
////                        CategoryViewHolder categoryViewHolder = new CategoryViewHolder(categoryList,getContext());
////                        listCategory.setAdapter(categoryViewHolder);
////                    }
////                } catch (JSONException e) {
////                    e.printStackTrace();
////                }
//
//            }
//
//        },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                });
//        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//        requestQueue.add(stringRequest);
//
//    }
//

    /*
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.logout:{
                Intent homeActivity = new Intent(Home_addActivity.this,MainActivity.class);
                startActivity(homeActivity);
                finish();
                return true;
            }
            default:{
                return super.onOptionsItemSelected(item);
            }
        }
    }*/

}
