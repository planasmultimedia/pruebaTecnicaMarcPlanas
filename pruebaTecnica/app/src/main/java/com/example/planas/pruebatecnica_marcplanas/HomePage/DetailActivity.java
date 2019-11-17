package com.example.planas.pruebatecnica_marcplanas.HomePage;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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
import com.example.planas.pruebatecnica_marcplanas.Models.PokemonDetails;
import com.example.planas.pruebatecnica_marcplanas.R;
import com.example.planas.pruebatecnica_marcplanas.Utils.DownloadImageTask;

import org.json.JSONException;

import java.util.Locale;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "DetailActivity";

    private PokemonDetails details;
    private MainListItem item;

    private TextView title_w, title_h, title_types, title_moves, tv_weigh, tv_height, tv_moves, tv_types;
    private ImageView image_front, image_back, back_arrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_detail_activity);

        getIntentExtras();
        initWidgets();
        httpRequestGetDetails();

    }

    private void initWidgets(){
        setTitle(item.getName());
        title_h = findViewById(R.id.tv_title_height);
        title_w = findViewById(R.id.tv_title_weight);
        title_moves = findViewById(R.id.tv_title_moves);
        title_types = findViewById(R.id.tv_title_types);

        tv_weigh = findViewById(R.id.tv_weight);
        tv_height = findViewById(R.id.tv_height);
        tv_moves = findViewById(R.id.tv_moves);
        tv_types = findViewById(R.id.tv_types);

        image_front = findViewById(R.id.image_front);
        image_back = findViewById(R.id.image_back);
        back_arrow = findViewById(R.id.backArrow);

        setFontType();

    }

    private void getIntentExtras(){
        Intent intent = getIntent();
        this.item = intent.getParcelableExtra(getString(R.string.extra_MainListItem));
        Log.d(TAG, "Item received: " + item.getName());

    }

    private void feedUI(PokemonDetails details){
        this.details = details;
        tv_types.setText(details.getTypes());
        tv_moves.setText(details.getMoves());

        tv_height.setText(Integer.toString(details.getHeight()));
        tv_weigh.setText(Integer.toString(details.getWeight()));

        new DownloadImageTask((ImageView) findViewById(R.id.image_front)).execute(details.getImages().get(0));
        new DownloadImageTask((ImageView) findViewById(R.id.image_back)).execute(details.getImages().get(1));

        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeActivityDetails();
            }
        });


    }

    private void setFontType(){
        AssetManager am = this.getApplicationContext().getAssets();

        Typeface typeface_bold = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Montserrat-Bold.ttf"));

        Typeface typeface_regular = Typeface.createFromAsset(am,
                String.format(Locale.US, "fonts/%s", "Montserrat-Regular.ttf"));

        title_types.setTypeface(typeface_bold);
        title_moves.setTypeface(typeface_bold);
        title_w.setTypeface(typeface_bold);
        title_h.setTypeface(typeface_bold);

        tv_weigh.setTypeface(typeface_regular);
        tv_height.setTypeface(typeface_regular);
        tv_moves.setTypeface(typeface_regular);
        tv_types.setTypeface(typeface_regular);


    }

    private void httpRequestGetDetails() {
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, item.getUrl(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "Response:" + response);
                        try {
                            feedUI(JsonParser.parseDetails(response));
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

    private void closeActivityDetails(){
        this.finish();
    }

}
