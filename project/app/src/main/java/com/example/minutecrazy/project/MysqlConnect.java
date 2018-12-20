package com.example.minutecrazy.project;

public class MysqlConnect {


    /*
    private final Activity catego;
    private String BASE_URL_Category = "http://192.168.64.2/project/";
    private ArrayList<String> mData;

    public String name;
    public String image;
    public String string;

    RecyclerView listCategory;

    public MysqlConnect(Activity category_a){
        catego = category_a ;
        mData = new ArrayList<String>();
    }

    public ArrayList<String> getData(){
        String url = BASE_URL_Category;
        StringRequest stringRequest = new StringRequest(url,showJSON(response));
        RequestQueue requestQueue = Volley.newRequestQueue(catego.getApplicationContext());
        requestQueue.add(stringRequest);

        return mData;
    }

    public void showJSON(String response){
        name="";
        image="";
        string = "";

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("result");

            for (int i = 0 ;i < result.length();i++){
                JSONObject collectData = result.getJSONObject(i);
                name = collectData.getString("name");
                image = collectData.getString("image");
                string = name+"\n"+image;
                mData.add(string);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void onResume() {
        super.onResume();
        feeddata();
    }
    private void feeddata() {
        new FeedAsyn().execute(BASE_URL_Category + "query.php");
    }

    private class FeedAsyn extends AsyncTask<String, Void, String> {

        @Override

        protected String doInBackground(String... strings) {
            try {
                OkHttpClient _OkHttpClient = new OkHttpClient();
                Request _request = new Request.Builder().url(strings[0]).get().build();
                Response _response = _OkHttpClient.newCall(_request).execute();
                String _result = _response.body().string();
                Gson _gson = new Gson();
                Type type = new TypeToken<List<Category>>() {}.getType();
                mData = _gson.fromJson(_result, type);
                return "successfully";
            } catch (IOException e) {
                e.printStackTrace(); }
            return null;
        }
        @Override

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if(result != null){
                listCategory.setAdapter(new CategoryViewHolder(mData));
            }
        } }
        */
}
