package com.example.dineriumaplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class Login extends AppCompatActivity {

    Button btnGoogle, btnFb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnFb = findViewById(R.id.btnLoginFb);
        btnGoogle = findViewById(R.id.btnLoginGoogle);
    }
}