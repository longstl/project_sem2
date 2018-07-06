package com.example.hoang.project_demo_3.controller;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.hoang.project_demo_3.R;

public class MainForm extends AppCompatActivity {

    Button btnSignIn, btnSignUp;
    TextView textViewSlogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_form);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        textViewSlogan = (TextView) findViewById(R.id.txtSlogan);
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Nabila.ttf");
        textViewSlogan.setTypeface(face);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // code in here. Button Sign In Action
                Intent signIn = new Intent(MainForm.this, SignIn.class);
                startActivity(signIn);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // code in here. Button Sign Up Action
                Intent signUp = new Intent(MainForm.this, SignUp.class);
                startActivity(signUp);
            }
        });
    }
}
