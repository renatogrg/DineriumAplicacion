package com.example.dineriumaplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ConfiguracionHerramientas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion_herramientas);
    }

    public void llamarConfiguracionPerfilUsuario(View view) {
        Intent intencion = new Intent(this, ConfiguracionPerfilUsuario.class);
        startActivity(intencion);
    }
}