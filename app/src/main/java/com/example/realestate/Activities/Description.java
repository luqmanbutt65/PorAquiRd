package com.example.realestate.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;

import com.example.realestate.Adapters.FeatureAdapter;
import com.example.realestate.Model.Features;
import com.example.realestate.R;
import com.example.realestate.customViewPager.customViewPager;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;

public class Description extends AppCompatActivity {
Context context;
Features features;
ViewPager viewPager;
DotsIndicator dotsIndicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);


        dotsIndicator = (DotsIndicator) findViewById(R.id.dots_indicator);




        int[]  imagge={R.drawable.house,R.drawable.house,R.drawable.house,R.drawable.house,R.drawable.house,R.drawable.house,R.drawable.house};

        viewPager = (ViewPager)findViewById(R.id.viewpager);

        customViewPager customViewPager= new customViewPager(Description.this, imagge);
        viewPager.setAdapter(customViewPager);
        dotsIndicator.setViewPager(viewPager);

        context=this;
        RecyclerView recyclerView = findViewById(R.id.featuresrecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));


        String[] description = {"aaaaaa", "sssss", "qqqqqq", "kkk", "kkkkkllll", "ppppppp", "uuuuuuu"};

        int[]  image={R.drawable.house,R.drawable.house,R.drawable.house,R.drawable.house,R.drawable.house,R.drawable.house,R.drawable.house};

        ArrayList<Features> features=new ArrayList<>();

        features.add(new Features(description[0],image[0]));
        features.add(new Features(description[1],image[1]));
        features.add(new Features(description[2],image[2]));

        features.add(new Features(description[3],image[3]));

        features.add(new Features(description[4],image[4]));

        features.add(new Features(description[5],image[5]));

        features.add(new Features(description[6],image[6]));


        recyclerView.setAdapter(new FeatureAdapter(Description.this,context,features));




    }
}