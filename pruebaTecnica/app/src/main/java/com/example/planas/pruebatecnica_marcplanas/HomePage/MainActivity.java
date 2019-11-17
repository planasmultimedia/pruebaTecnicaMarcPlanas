package com.example.planas.pruebatecnica_marcplanas.HomePage;

import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.planas.pruebatecnica_marcplanas.Adapters.MainListAdapter;
import com.example.planas.pruebatecnica_marcplanas.HttpUtils.HttpConst;
import com.example.planas.pruebatecnica_marcplanas.HttpUtils.JsonParser;
import com.example.planas.pruebatecnica_marcplanas.Models.MainListItem;
import com.example.planas.pruebatecnica_marcplanas.R;

import org.json.JSONException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    ListView main_listview;
    MainListAdapter main_list_adapter;
    EditText inputSearch;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidgets();
        HttpRequestGetList();
        disableKeyboardAppear();
    }

    private void initWidgets() {
        main_listview = (ListView) findViewById(R.id.main_list_view);

    }

    private void disableKeyboardAppear(){
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    private void feedList(ArrayList<MainListItem> items){
        Log.d(TAG,"feedList: starting...");
        this.main_list_adapter = new MainListAdapter(this, R.layout.main_list_item, items);
        main_listview.setAdapter(this.main_list_adapter);
        Log.d(TAG,"feedList: finished");

        main_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openDetailActivity(main_list_adapter.getItem(position));
            }
        });
    }




    private void HttpRequestGetList(){
        Log.d(TAG,"makeHttpRequest: starting...");
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, HttpConst.URL_GET_POKEMON_LIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "Response:" + response);
                        try {
                            feedList(JsonParser.parseList(response));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("MainActivity ", "Error on Response: " + error.toString());
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private void openDetailActivity(MainListItem item){
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(getString(R.string.extra_MainListItem), item);
        startActivity(intent);
    }


}
