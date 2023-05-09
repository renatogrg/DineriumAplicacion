package com.example.dineriumaplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.SignInButton;
import com.google.firebase.auth.FirebaseAuth;

public class activityLogin extends AppCompatActivity {

    Button btnGoogle, btnFb;
    FirebaseAuth miAuth;
    GoogleSignInClient mGoogleSignInClient;
    CallbackManager mCallbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnFb = findViewById(R.id.btnLoginFb);
        btnGoogle = findViewById(R.id.btnLoginGoogle);
    }
}