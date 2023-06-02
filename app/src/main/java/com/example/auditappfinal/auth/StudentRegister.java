package com.example.auditappfinal.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.auditappfinal.R;
import com.example.auditappfinal.studentWrapper.Student;
import com.example.auditappfinal.studentWrapper.StudentDashboard;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class StudentRegister extends AppCompatActivity {
private AutoCompleteTextView stdSpinner,divSpinner, genderSpinner;
private ArrayAdapter<String> stdAdapter,divAdapter, genderAdapter;
private TextInputEditText fullnameET,emailET,passET;
private FirebaseAuth firebaseAuth;
private FirebaseFirestore db;
private DatabaseReference databaseReference;

    @SuppressLint({"ResourceType", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);
        fullnameET = findViewById(R.id.full_name);
        emailET = findViewById(R.id.email);
        passET = findViewById(R.id.password);
        stdSpinner = findViewById(R.id.std);
        divSpinner = findViewById(R.id.div);
        genderSpinner = findViewById(R.id.gender);
        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();


        ArrayList<String> genderList = new ArrayList<>();
        genderList.add("Male");
        genderList.add("Female");



        ArrayList<String> divList = new ArrayList<>();
        divList.add("A");
        divList.add("B");
        divList.add("C");
        divList.add("D");
        divList.add("E");


        ArrayList<String> stdList = new ArrayList<>();
        stdList.add("I");
        stdList.add("II");
        stdList.add("III");
        stdList.add("IV");
        stdList.add("V");
        stdList.add("VI");
        stdList.add("VII");
        stdList.add("VIII");
        stdList.add("IX");
        stdList.add("X");


        divAdapter = new ArrayAdapter<String>(StudentRegister.this, R.layout.dropdown_item,divList);
        stdAdapter = new ArrayAdapter<String>(StudentRegister.this, R.layout.dropdown_item,stdList);
        genderAdapter = new ArrayAdapter<String>(StudentRegister.this, R.layout.dropdown_item,genderList);


        divSpinner.setAdapter(divAdapter);
        stdSpinner.setAdapter(stdAdapter);
        genderSpinner.setAdapter(genderAdapter);
    }

    public void nxtScreen(View view) {
        String fullname_txt = fullnameET.getText().toString();
        String div_txt = divSpinner.getText().toString();
        String std_txt = stdSpinner.getText().toString();
        String gender_txt = genderSpinner.getText().toString();
        String email_txt = emailET.getText().toString();
        String password_txt = passET.getText().toString();
        String hashPwd = PasswordHash.hashPassword(password_txt);


        firebaseAuth.createUserWithEmailAndPassword(email_txt,password_txt)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        String UID = firebaseAuth.getCurrentUser().getUid();
                        Student student = new Student(fullname_txt,gender_txt,std_txt,div_txt,email_txt,UID,hashPwd);
                        db.collection("students").document(email_txt).set(student)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(StudentRegister.this, "Account Created Successfully!", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(StudentRegister.this, StudentDashboard.class);
                                        intent.putExtra("loggedInStudent",student);
                                        SharedPreferences settings = getSharedPreferences("AUDIT_APP_PREFS", MODE_PRIVATE);
                                        SharedPreferences.Editor prefEditor = settings.edit();
                                        prefEditor.putBoolean("isLoggedIn",true);
                                        prefEditor.putString("studentEmail",student.getEmail());
                                        prefEditor.apply();
                                        finishAffinity();
                                        startActivity(intent);
                                    }

                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(StudentRegister.this, e.toString(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(StudentRegister.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    public void backScreen(View view) {
        onBackPressed();
    }
}