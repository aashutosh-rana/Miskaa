package com.bcebhagalpur.miskaa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class DetailActivity extends AppCompatActivity {

    Toolbar toolbar;
    ImageView expandedImage;
    TextView txtPopulation,txtRegion,txtSubRegion,txtBorders,txtLang,txtCapital;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        toolbar=findViewById(R.id.toolbar);
        expandedImage=findViewById(R.id.expandedImage);
        txtPopulation=findViewById(R.id.txtPopulation);
        txtRegion=findViewById(R.id.txtRegion);
        txtSubRegion=findViewById(R.id.txtSubRegion);
        txtBorders=findViewById(R.id.txtBorder);
        txtLang=findViewById(R.id.txtLang);
        txtCapital = findViewById(R.id.txtCapital);

       String name = getIntent().getStringExtra("name");
        String capital= getIntent().getStringExtra("capital");
        String region = getIntent().getStringExtra("region");
        String subRegion=getIntent().getStringExtra("subRegion");
       String flag = getIntent().getStringExtra("flag");
        String population= getIntent().getStringExtra("population");
        ArrayList<String> borders =(ArrayList<String>) getIntent().getSerializableExtra("borders");
        ArrayList<String> language= (ArrayList<String>)getIntent().getSerializableExtra("language");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setTitle(name);
            toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        }
        Utils.fetchSvg(this, flag, expandedImage);

        txtPopulation.setText(population);
        txtRegion.setText(region);
        txtSubRegion.setText(subRegion);
        txtCapital.setText(capital);

        txtBorders.setText(String.valueOf(borders));
        txtLang.setText(String.valueOf(language));
    }

}