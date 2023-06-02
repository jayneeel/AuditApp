package com.example.auditappfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.auditappfinal.auth.StudentLogin;
import com.example.auditappfinal.auth.TeacherLogin;

public class Navigation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
    }

    public void studentLogin(View view) {
        startActivity(new Intent(Navigation.this, StudentLogin.class));

    }

    public void teacherLogin(View view) {
        startActivity(new Intent(Navigation.this, TeacherLogin.class));
    }
}