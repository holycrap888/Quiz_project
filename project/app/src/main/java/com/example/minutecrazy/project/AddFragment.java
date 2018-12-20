package com.example.minutecrazy.project;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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


/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends Fragment {

    TextView edt_addName,edt_addImage;
    Button btn_addSub;
    private String n,m;


    View myFragment;
    public static AddFragment newInstance(){
        AddFragment addFragment = new AddFragment();
        return addFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myFragment = inflater.inflate(R.layout.fragment_add,container,false);
        edt_addName = myFragment.findViewById(R.id.edtAddName);
        edt_addImage = myFragment.findViewById(R.id.edtAddImage);
        btn_addSub = myFragment.findViewById(R.id.addSubject);
        return myFragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn_addSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m = edt_addImage.getText().toString();
                n = edt_addName.getText().toString();
                sendCategory(m,n);
                Intent intent = new Intent(getContext(),Home_addActivity.class);
                startActivity(intent);
            }
        });

    }

    private void sendCategory(final String nana,final String mama ) {
        String url = Category.BASE_URL+"send_category.php";
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
                params.put("name", mama);
                params.put("image",nana);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

}
