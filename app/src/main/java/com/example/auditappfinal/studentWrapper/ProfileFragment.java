package com.example.auditappfinal.studentWrapper;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.auditappfinal.Navigation;
import com.example.auditappfinal.R;
import com.google.android.material.button.MaterialButton;


public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Student student;
    TextView studentName,studentStdDiv;
    MaterialButton logoutBtn;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            student = (Student) arguments.getSerializable("loggedInStudent");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        studentName = view.findViewById(R.id.studentName);
        studentStdDiv = view.findViewById(R.id.studentStdDiv);
        logoutBtn = view.findViewById(R.id.logoutBtn);


        studentName .setText(student.getFull_name());
        studentStdDiv .setText(student.getStd() + " - "+student.getDivision());



        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences settings = view.getContext().getSharedPreferences("AUDIT_APP_PREFS", MODE_PRIVATE);
                SharedPreferences.Editor prefEditor = settings.edit();
                prefEditor.putBoolean("isLoggedIn",false);
                prefEditor.putString("studentEmail","");
                prefEditor.apply();
                Toast.makeText(view.getContext(), "Logout Successful", Toast.LENGTH_SHORT).show();
                getActivity().finishAffinity();
                startActivity(new Intent(view.getContext().getApplicationContext(), Navigation.class));
            }
        });

        return view;
    }
}