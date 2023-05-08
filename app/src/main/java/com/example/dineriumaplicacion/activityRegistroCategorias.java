package com.example.dineriumaplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class activityRegistroCategorias extends AppCompatActivity {
    Button btnVolver;
    TextView txtPresupuesto;
    String presupuesto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_categorias);

        btnVolver = findViewById(R.id.btnVolver);
        txtPresupuesto = findViewById(R.id.txtPresupuesto);

        Intent intent = getIntent();
        int presupuesto = intent.getIntExtra("presupuesto", 0);
        txtPresupuesto.setText(String.valueOf(presupuesto));


        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activityRegistroCategorias.this, activityInicio.class);
                startActivity(intent);
            }
        });

    }
}