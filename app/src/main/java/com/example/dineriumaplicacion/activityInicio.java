package com.example.dineriumaplicacion;

import androidx.annotation.NonNull;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class activityInicio extends AppCompatActivity {

    public FirebaseFirestore firebaseFirestore;

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

        firebaseFirestore = FirebaseFirestore.getInstance();

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

        String [] OpcionesTiempo = {"Semanal", "Mensual", "Anual"};
        ArrayAdapter <String> adapterOpcionesTiempo = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, OpcionesTiempo);
        spnIniTiempo.setAdapter(adapterOpcionesTiempo);

        btnIniRegistrarObjetivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String objMontoAhorro = edtIniAhorro.getText().toString();
                String objPresupuesto = edtIniPresupuesto.getText().toString();
                String objFechaPropuesta = spnIniTiempo.getSelectedItem().toString();

                if(objMontoAhorro.isEmpty() && objPresupuesto.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Ingresar TODOS los datos",
                            Toast.LENGTH_LONG).show();
                }else{
                    RegistrarObjetivo(objMontoAhorro, objPresupuesto, objFechaPropuesta);
                }
            }
        });

        imgBtnIniConfiguracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Configuracion();
            }
        });

        btnIniObjetivos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MisObjetivos();
            }
        });

        imgBtnIniInversiones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Inversiones();
            }
        });

        btnIniRegistrarGastos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistroCategorias();
            }
        });

        btnIniReporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReporteGeneral();
            }
        });
    }

    public void RegistrarObjetivo(String objMontoAhorro, String objPresupuesto, String objFechaPropuesta){
        Map<String, Object> map = new HashMap<>();
        map.put("objMontoAhorro", objMontoAhorro);
        map.put("objPresupuesto", objPresupuesto);
        map.put("objFechaPropuesta", objFechaPropuesta);

        firebaseFirestore.collection("tblObjetivos").add(map).
                addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Toast.makeText(getApplicationContext(),
                            "Guardaste el objetivo EXITOSAMENTE",
                            Toast.LENGTH_LONG).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),
                                    "Error al ingresar a la base de datos",
                                    Toast.LENGTH_LONG).show();
                        }
                    });
    }

    public void Configuracion() {
        Intent Configuracion = new Intent(this, activityConfiguracion.class);
        startActivity(Configuracion);
    }

    public void MisObjetivos() {
        Intent MisObjetivos = new Intent(this, activityMisObjetivos.class);
        startActivity(MisObjetivos);
    }

    public void Inversiones() {
        Intent Inversiones = new Intent(this, activityInversiones.class);
        startActivity(Inversiones);
    }

    public void RegistroCategorias() {
        Intent RegistroCategorias = new Intent(this, activityRegistroCategorias.class);
        startActivity(RegistroCategorias);
    }

    public void ReporteGeneral() {
        Intent ReporteGeneral = new Intent(this, activityReporteGeneral.class);
        startActivity(ReporteGeneral);
    }
}