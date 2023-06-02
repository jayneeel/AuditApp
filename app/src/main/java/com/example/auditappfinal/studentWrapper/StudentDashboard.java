package com.example.auditappfinal.studentWrapper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.auditappfinal.Navigation;
import com.example.auditappfinal.R;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class StudentDashboard extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView bottomNavigationView;
    private static TimelineFragment timelineFragment;
    private AssignmentFragment assignmentFragment;
    private QuizFragment quizFragment;
    private ProfileFragment profileFragment;
    private Bundle bundle;
    private FirebaseFirestore db;
    private String email;
    private Student student;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        bottomNavigationView = findViewById(R.id.bNav);
        timelineFragment = new TimelineFragment();
        assignmentFragment = new AssignmentFragment();
        quizFragment = new QuizFragment();
        profileFragment = new ProfileFragment();
        bundle = new Bundle();
        db = FirebaseFirestore.getInstance();
        SharedPreferences settings = getSharedPreferences("AUDIT_APP_PREFS", MODE_PRIVATE);
        email = settings.getString("studentEmail","");

        db.collection("students").document(email).get()
        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                student = documentSnapshot.toObject(Student.class);
                bundle.putSerializable("loggedInStudent",student);
                timelineFragment.setArguments(bundle);
            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });


        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.home:
                timelineFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.frameL, timelineFragment).commit();
                return true;

            case R.id.assignment:
                assignmentFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.frameL, assignmentFragment).commit();
                return true;

            case R.id.quiz:
                quizFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.frameL, quizFragment).commit();
                return true;

            case R.id.profile:
                profileFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.frameL, profileFragment).commit();
                return true;
        }
        return false;
    }
}