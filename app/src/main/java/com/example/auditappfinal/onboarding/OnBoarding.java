package com.example.auditappfinal.onboarding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.auditappfinal.Navigation;
import com.example.auditappfinal.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class OnBoarding extends AppCompatActivity {
    private ViewPager viewPager;
    OnBoardAdapter onBoardAdapter;
    TabLayout tab_indicator;
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);
        tab_indicator = findViewById(R.id.tab_indicator);
        viewPager = findViewById(R.id.viewPager);
        List<OnBoardItems> resList = new ArrayList<>();
        resList.add(new OnBoardItems("Community","Post your doubts and get them cleared by your teachers.",R.drawable.one));
        resList.add(new OnBoardItems("Assessment Submission","Upload your assignments and practice quiz on your fingertips.",R.drawable.two));
        resList.add(new OnBoardItems("Analyze","Track your progress and analyze your grades in realtime",R.drawable.three));

        onBoardAdapter = new OnBoardAdapter(this,resList);
        viewPager.setAdapter(onBoardAdapter);
        tab_indicator.setupWithViewPager(viewPager);
    }

    public void nxtScreen(View view) {
        startActivity(new Intent(OnBoarding.this, Navigation.class));
    }
}