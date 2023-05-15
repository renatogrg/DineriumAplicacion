package com.example.dineriumaplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ConfiguracionPerfilUsuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion_perfil_usuario);
    }




    public void llamarConfiguracionHerramientas(View view) {
        Intent intencion = new Intent(this, ConfiguracionHerramientas.class);
        startActivity(intencion);
    }
}