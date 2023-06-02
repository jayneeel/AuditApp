package com.example.auditappfinal.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.example.auditappfinal.teacherWrapper.Teacher;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class TeacherRegister extends AppCompatActivity {
    private TextInputEditText full_name_t_ET, email_t_ET, password_t_ET;
    private AutoCompleteTextView gender_t_spinner;
    private ArrayAdapter<String> gender_t_adapter;
    private MaterialButton register_t_Btn;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_register);
        full_name_t_ET = findViewById(R.id.full_name_t);
        password_t_ET = findViewById(R.id.password_t);
        email_t_ET = findViewById(R.id.email_t);
        gender_t_spinner = findViewById(R.id.gender_t);
        register_t_Btn = findViewById(R.id.registerBtn_t);
        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();



        ArrayList<String> genderList = new ArrayList<>();
        genderList.add("Male");
        genderList.add("Female");


        gender_t_adapter = new ArrayAdapter<String>(TeacherRegister.this, R.layout.dropdown_item,genderList);
        gender_t_spinner.setAdapter(gender_t_adapter);

        register_t_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fullname_txt = full_name_t_ET.getText().toString();
                String gender_txt = gender_t_spinner.getText().toString();
                String email_txt = email_t_ET.getText().toString();
                String password_txt = password_t_ET.getText().toString();
                String hashPwd = PasswordHash.hashPassword(password_txt);


                firebaseAuth.createUserWithEmailAndPassword(email_txt,password_txt)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                String UID = firebaseAuth.getCurrentUser().getUid();
                                Teacher teacher = new Teacher(fullname_txt,gender_txt,email_txt,UID,hashPwd);
                                db.collection("teachers").document(email_txt).set(teacher)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Toast.makeText(TeacherRegister.this, "Account Created Successfully!", Toast.LENGTH_LONG).show();
                                                Intent intent = new Intent(TeacherRegister.this, TeacherLogin.class);
                                                startActivity(intent);
                                            }

                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(TeacherRegister.this, e.toString(), Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(TeacherRegister.this, e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });

    }
}