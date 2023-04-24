package com.example.dineriumaplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

public class activityInicio extends AppCompatActivity {

    ProgressBar prbIniProgreso;
    TextView txtIniProgreso;
    EditText edtIniAhorro, edtIniPresupuesto;
    Spinner spnIniTiempo;
    ImageButton imgBtnIniConfiguracion, imgBtnIniInversiones;
    Button btnIniObjetivos, btnIniRegistrarObjetivo, btnIniRegistrarGastos, btnIniReporte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        prbIniProgreso = (ProgressBar) findViewById(R.id.prbIniProgreso);
        txtIniProgreso = (TextView) findViewById(R.id.txtIniProgreso);
        edtIniAhorro = (EditText) findViewById(R.id.edtIniAhorro);
        edtIniPresupuesto = (EditText) findViewById(R.id.edtIniPresupuesto);
        spnIniTiempo = (Spinner) findViewById(R.id.spnIniTiempo);
        imgBtnIniConfiguracion = (ImageButton) findViewById(R.id.imgBtnIniConfiguracion);
        imgBtnIniInversiones = (ImageButton) findViewById(R.id.imgBtnIniInversiones);
        btnIniObjetivos = (Button) findViewById(R.id.btnIniObjetivos);
        btnIniRegistrarObjetivo = (Button) findViewById(R.id.btnIniRegistrarObjetivo);
        btnIniRegistrarGastos = (Button) findViewById(R.id.btnIniRegistrarGastos);
        btnIniReporte = (Button) findViewById(R.id.btnIniReporte);

        imgBtnIniConfiguracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Configuracion();
            }
        });
    }

    public void Configuracion() {
        Intent Configuracion = new Intent(this, activityConfiguracion.class);
        startActivity(Configuracion);
    }
}