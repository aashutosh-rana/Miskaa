package com.bcebhagalpur.miskaa;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity {

   RecyclerView countryRecycler;
   RelativeLayout r1;
   ProgressBar progressBar;
   CountryAdapter countryAdapter;
//    CountryModel[] countryList = new CountryModel[0];
    ArrayList<CountryModel > countryList = new ArrayList<CountryModel >();
    ArrayList<String > borderList;
    ArrayList<String > langList;
    RecyclerView.LayoutManager layoutManager;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countryRecycler = findViewById(R.id.countryRecycler);
        r1 = findViewById(R.id.r1);
        progressBar = findViewById(R.id.progressBar);
        r1.setVisibility(View.VISIBLE);
        layoutManager= new LinearLayoutManager(this);
        getAllCountryList();



    }

//        cardView.setOnClickListener(v -> {
//            startActivity(new Intent(this,DetailActivity.class));
//        });


    private void getAllCountryList() {
        String url = "https://restcountries.eu/rest/v2/region/asia";
        RequestQueue queue= Volley.newRequestQueue(this);

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null, response -> {
            try {
                r1.setVisibility(View.GONE);
                for (int i=0;i<=response.length();i++) {
                    JSONObject data = response.getJSONObject(i);
                    JSONArray border = data.getJSONArray("borders");
                    JSONArray languages = data.getJSONArray("languages");


                    langList=new ArrayList<String>();
                    borderList = new ArrayList<>();
                        for (int i2 = 0; i2<languages.length(); i2++) {
                            JSONObject name1=languages.getJSONObject(i2);
                            String name= name1.getString("name");
                            langList.add(name);
                    }
//

                        for (int i1=0;i1<border.length();i1++){
                            borderList.add(border.getString(i1));
                        }

                        CountryModel countryObject = new CountryModel(
                                data.getString("name"),
                                data.getString("capital"), data.getString("region"),
                                data.getString("subregion"), data.getString("population"), data.getString("flag"),
                                borderList, langList
                        );
                        countryList.add(countryObject);
                        countryAdapter = new CountryAdapter(countryList, this);
                        countryRecycler.setAdapter(countryAdapter);
                        countryRecycler.setLayoutManager(layoutManager);

                }
            } catch (JSONException e) {
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }, error -> Toast.makeText(MainActivity.this, "Fail to get data..", Toast.LENGTH_SHORT).show());
        queue.add(jsonObjectRequest);

    }

}