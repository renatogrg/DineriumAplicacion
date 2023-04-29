package com.example.dineriumaplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.ktx.Firebase;

public class Splash extends AppCompatActivity implements View.OnClickListener {

    FirebaseFirestore firebaseFirestore;


    ImageView icono;
    TextView txtMensaje;

    Handler handler;

    Animator animador, animadorX, animadorY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        icono = findViewById(R.id.imgLogo);
        txtMensaje= findViewById(R.id.txtMensaje);
        Animation animacion;
        animacion = android.view.animation.AnimationUtils.loadAnimation(this,R.anim.animacionsplash);
        icono.startAnimation(animacion);
        rotacion(icono, 2000);
        txtMensaje.startAnimation(animacion);
        rotacion(txtMensaje, 1000);
        icono.setOnClickListener(this);
        esperaryCerrar(3000);
    }
    private void rotacion(View view, int dur){
        animador = ObjectAnimator.ofFloat(view,"rotation",0,360);
        animador.setDuration(dur);
        //QUE LA ANIMACION SE EJECUTE AL MISMO TIEMPO QUE OTROS
        AnimatorSet animatorSet= new AnimatorSet();
        animatorSet.play(animador);
        animatorSet.start();
    }

    private void trasladar(View view, long dur, float haciax, float haciay){
        animadorX = ObjectAnimator.ofFloat(view,"X", haciax);
        animadorY=ObjectAnimator.ofFloat(view, "Y",haciay);
        animadorX.setDuration(dur);
        animadorY.setDuration(dur);
        AnimatorSet animatorSet= new AnimatorSet();
        animatorSet.playTogether(animadorX,animadorY);
        animatorSet.start();
    }
    private void esperaryCerrar(int tiempo){
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                llamar();
            }
        },tiempo);
    }
    public void llamar(){
        Intent intent= new Intent(this, activityLogin.class);
        startActivity(intent);
    }

    public void onClick(View view) {
        trasladar(txtMensaje,2000,200,800);
        rotacion(icono, 1000);
    }
}