package com.example.auditappfinal.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.auditappfinal.R;
import com.example.auditappfinal.studentWrapper.Student;
import com.example.auditappfinal.studentWrapper.StudentDashboard;
import com.example.auditappfinal.teacherWrapper.Teacher;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class TeacherLogin extends AppCompatActivity {
    private TextInputEditText emailET,passwordET;
    private MaterialButton loginBtn;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore db;
    Teacher teacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);
        emailET = findViewById(R.id.emailT);
        passwordET = findViewById(R.id.pwdT);
        loginBtn = findViewById(R.id.loginBtnT);
        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailET.getText().toString();
                String pass = passwordET.getText().toString();

                firebaseAuth.signInWithEmailAndPassword(email,pass)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {

                                db.collection("teachers").document(email).get()
                                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                            @Override
                                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                teacher = documentSnapshot.toObject(Teacher.class);
                                                if (teacher.getPassword_hash().equals(PasswordHash.hashPassword(pass))){
                                                    Toast.makeText(TeacherLogin.this, "Login Successful!", Toast.LENGTH_SHORT).show();

                                                }
                                                else {
                                                    Toast.makeText(TeacherLogin.this, "Bad Credentials", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(TeacherLogin.this, "Bad Credentials", Toast.LENGTH_SHORT).show();
                                            }
                                        });


                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(TeacherLogin.this, "Bad Credentials", Toast.LENGTH_SHORT).show();

                            }
                        });

            }
        });
    }


    public void registerScreen(View view) {
        startActivity(new Intent(TeacherLogin.this, TeacherRegister.class));
    }
}