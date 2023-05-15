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
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Inicio extends AppCompatActivity {

    FirebaseFirestore firebaseFirestore;
    ImageButton imgBtnIniConfiguracion, imgBtnIniInversiones;
    EditText edtIniAhorro, edtIniPresupuesto;
    Spinner spnIniTiempo;
    Button btnIniObjetivos, btnIniRegistrarObjetivo, btnIniRegistrarGastos, btnIniVerCategorias, btnIniReporte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        firebaseFirestore = FirebaseFirestore.getInstance();

        String id = getIntent().getStringExtra("idObjetivo");

        edtIniAhorro = (EditText) findViewById(R.id.edtIniAhorro);
        edtIniPresupuesto = (EditText) findViewById(R.id.edtIniPresupuesto);
        spnIniTiempo = (Spinner) findViewById(R.id.spnIniTiempo);
        imgBtnIniConfiguracion = (ImageButton) findViewById(R.id.imgBtnIniConfiguracion);
        imgBtnIniInversiones = (ImageButton) findViewById(R.id.imgBtnIniInversiones);
        btnIniObjetivos = (Button) findViewById(R.id.btnIniObjetivos);
        btnIniRegistrarObjetivo = (Button) findViewById(R.id.btnIniRegistrarObjetivo);
        btnIniRegistrarGastos = (Button) findViewById(R.id.btnIniRegistrarGastos);
        btnIniVerCategorias = (Button) findViewById(R.id.btnIniVerCategorias);
        btnIniReporte = (Button) findViewById(R.id.btnIniReporte);

        String [] OpcionesTiempo = {"Semanal", "Mensual", "Anual"};
        ArrayAdapter <String> adapterOpcionesTiempo = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, OpcionesTiempo);
        spnIniTiempo.setAdapter(adapterOpcionesTiempo);

        if(id == null || id == "")
        {
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
        }else{
            btnIniRegistrarObjetivo.setText("Editar");
            obtenerObjetivo(id);
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
                        EditarObjetivo(objMontoAhorro, objPresupuesto, objFechaPropuesta, id);
                    }
                }
            });
        }

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

        btnIniVerCategorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VerCategorias();
            }
        });

        btnIniReporte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReporteGeneral();
            }
        });
    }

    private void EditarObjetivo(String objMontoAhorro, String objPresupuesto, String objFechaPropuesta, String id) {
        Map<String, Object> map = new HashMap<>();
        map.put("objMontoAhorro", objMontoAhorro);
        map.put("objPresupuesto", objPresupuesto);
        map.put("objFechaPropuesta", objFechaPropuesta);

        firebaseFirestore.collection("tblObjetivos").document(id).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getApplicationContext(),
                        "Editaste el objetivo EXITOSAMENTE", Toast.LENGTH_SHORT).show();
                InicioProcesos();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),
                        "ERROR al Editar el objetivo", Toast.LENGTH_SHORT).show();
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
                    InicioProcesos();
                    Toast.makeText(getApplicationContext(),
                            "Guardaste el objetivo EXITOSAMENTE", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),
                                    "Error al ingresar a la base de datos", Toast.LENGTH_SHORT).show();
                        }
                    });
    }

    public void obtenerObjetivo(String id){
        firebaseFirestore.collection("tblObjetivos").document(id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String objMontoAhorro = documentSnapshot.getString("objMontoAhorro");
                String objPresupuesto = documentSnapshot.getString("objPresupuesto");
                edtIniAhorro.setText(objMontoAhorro);
                edtIniPresupuesto.setText(objPresupuesto);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),
                        "Error al obtener datos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void Configuracion() {
        Intent Configuracion = new Intent(this, com.example.dineriumaplicacion.Configuracion.class);
        startActivity(Configuracion);
    }

    public void MisObjetivos() {
        Intent MisObjetivos = new Intent(this, com.example.dineriumaplicacion.MisObjetivos.class);
        startActivity(MisObjetivos);
    }

    public void Inversiones() {
        Intent Inversiones = new Intent(this, com.example.dineriumaplicacion.Inversiones.class);
        startActivity(Inversiones);
    }

    public void RegistroCategorias() {
        Intent RegistroCategorias = new Intent(this, com.example.dineriumaplicacion.RegistroCategorias.class);
        startActivity(RegistroCategorias);
    }

    public void VerCategorias() {
        //Intent VerCategorias = new Intent(this, activityVerCategorias.class);
        //startActivity(VerCategorias);
    }

    public void ReporteGeneral() {
        Intent ReporteGeneral = new Intent(this, com.example.dineriumaplicacion.ReporteGeneral.class);
        startActivity(ReporteGeneral);
    }

    public void InicioProcesos() {
        Intent InicioProcesos = new Intent(this, InicioProceso.class);
        startActivity(InicioProcesos);
    }
}