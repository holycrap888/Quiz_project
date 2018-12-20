package com.example.minutecrazy.project;


import android.app.AlertDialog;
import android.content.DialogInterface;
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

public class Category_adFragment extends Fragment {

    View myFragment;

    private List<Category> categoryLists;
    private RecyclerView listCategory;
    private RecyclerView.LayoutManager layoutManager;

    public static Category_adFragment newInstance(){
        Category_adFragment categoryFragment = new Category_adFragment();
        return categoryFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myFragment = inflater.inflate(R.layout.fragment_category_ad,container,false);

        listCategory = myFragment.findViewById(R.id.listCategories1);
        listCategory.setHasFixedSize(true);
        categoryLists = new ArrayList<>();
        layoutManager = new LinearLayoutManager(getActivity());
        listCategory.setLayoutManager(layoutManager);
        listCategory.setItemAnimator(new DefaultItemAnimator());
        listCategory.addOnItemTouchListener(new RecyclerTouchListener(getContext(), listCategory, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {
                Category category = categoryLists.get(position);
                Common.categoryId = category.getId();
                //Toast.makeText(getContext(),category.toString(),Toast.LENGTH_LONG).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setMessage("Edit or Delete");
                builder.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getContext(),UpdateActivity.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DeleteData();
                    }
                });
                builder.show();
//                showActionDialog(position);
            }
        }));
        loadCategory();



        return myFragment;
    }

    private void DeleteData() {
        String url = Category.BASE_URL+"delete_category.php?ids="+Common.categoryId;
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
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

        Intent intent = new Intent(getContext(),Home_addActivity.class);
        intent.putExtra("a","a");
        startActivity(intent);
    }

//    private void showActionDialog(final int position){
//        CharSequence colors[] = new CharSequence[]{"Edit","Delete"};
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        builder.setTitle("Choose Option");
//        builder.setItems(colors, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                if (which==0){
//                    showStudentDialog(true,categoryLists.get(position),position);
//                }else {
//                    deleteStudent(position);
//                }
//            }
//        });
//        builder.show();
//    }
//
//    private void deleteStudent(int position) {
//
//    }
//
//    private void showStudentDialog(final boolean b, final Category category, final int position) {
//        LayoutInflater layoutInflaterAndriod = LayoutInflater.from(getContext());
//        View view = layoutInflaterAndriod.inflate(R.layout.category_dialog,null);
//
//        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(getContext());
//        alertDialogBuilderUserInput.setView(view);
//
//        final EditText inputName = view.findViewById(R.id.edit_text_name1);
//        final EditText inputImg = view.findViewById(R.id.edit_text_img1);
//        TextView dialogTitle = view.findViewById(R.id.text_view_title);
//        dialogTitle.setText(!b ? "New Student" : "Edit student");
//
//        if (b && category != null){
//            inputName.setText(category.getName());
//            inputImg.setText(category.getImage());
//        }
//        alertDialogBuilderUserInput
//                .setCancelable(false)
//                .setPositiveButton(b ? "update" : "save", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int id) {
//                    }
//                })
//                .setNegativeButton("cancle",
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.cancel();
//                            }
//                        });
//
//        final AlertDialog alertDialog = alertDialogBuilderUserInput.create();
//        alertDialog.show();
//
//        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (TextUtils.isEmpty(inputName.getText().toString())){
//                    Toast.makeText(getContext(),"Enter Name Category!",Toast.LENGTH_SHORT).show();
//                    return;
//                }else {
//                    alertDialog.dismiss();
//                }
//
//                if (b && category != null){
//                    updateStudent(inputName.getText().toString(),
//                            inputImg.getText().toString(),
//                            position);
//                }
//            }
//        });
//    }
//
//

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
