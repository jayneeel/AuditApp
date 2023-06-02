package com.example.auditappfinal.teacherWrapper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.auditappfinal.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TeacherDashboard extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;
    CreateAssignment createAssignment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dashboard);
        bottomNavigationView = findViewById(R.id.bNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
//        bottomNavigationView.setSelectedItemId(R.id.assignment);
        createAssignment = new CreateAssignment();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.assignment:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameTeacherDashboard, createAssignment).commit();
                return true;
        }
        return false;
    }
}