package com.example.auditappfinal.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.auditappfinal.R;
import com.example.auditappfinal.studentWrapper.Student;
import com.example.auditappfinal.studentWrapper.StudentDashboard;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class StudentLogin extends AppCompatActivity {
    TextInputEditText emailET,passET;
    MaterialButton loginBtn;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore db;
    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        emailET = findViewById(R.id.email);
        passET = findViewById(R.id.pwd);
        loginBtn = findViewById(R.id.loginBtn);
        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email_txt = emailET.getText().toString();
                String pass_txt = passET.getText().toString();

                firebaseAuth.signInWithEmailAndPassword(email_txt,pass_txt)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {

                                db.collection("students").document(email_txt).get()
                                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                        @Override
                                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                                            student = documentSnapshot.toObject(Student.class);
                                            if (student.getPassword_hash().equals(PasswordHash.hashPassword(pass_txt))){
                                                Toast.makeText(StudentLogin.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(StudentLogin.this, StudentDashboard.class);
                                                SharedPreferences settings = getSharedPreferences("AUDIT_APP_PREFS", MODE_PRIVATE);
                                                SharedPreferences.Editor prefEditor = settings.edit();
                                                prefEditor.putBoolean("isLoggedIn",true);
                                                prefEditor.putString("studentEmail",student.getEmail());
                                                prefEditor.apply();
                                                finishAffinity();
                                                startActivity(intent);
                                            }
                                            else {
                                                Toast.makeText(StudentLogin.this, "Bad Credentials", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(StudentLogin.this, "Bad Credentials", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(StudentLogin.this, "Bad Credentials", Toast.LENGTH_SHORT).show();
                            }
                        });






            }
        });


    }



    public void nxtScreen(View view) {
        startActivity(new Intent(StudentLogin.this,StudentRegister.class));
    }

    public void forgetScreen(View view) {
        startActivity(new Intent(StudentLogin.this,ForgetPassword.class));
    }

    public void backScreen(View view) {
        onBackPressed();
    }
}