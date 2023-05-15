package com.example.dineriumaplicacion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DetalleRegistro extends AppCompatActivity implements View.OnClickListener {
    Button btnGuardar,btnVolver;
    EditText edtRazon, edtPrecio, edtDescripcion;
    private FirebaseFirestore mfirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Agregar Tarea");
        mfirestore = FirebaseFirestore.getInstance();


        setContentView(R.layout.activity_detalle_registro);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnVolver = findViewById(R.id.btnVolver);
        edtRazon  = findViewById(R.id.edtRazon);
        edtPrecio  = findViewById(R.id.edtPrecio);
        edtDescripcion = findViewById(R.id.edtDescripcion);
        btnGuardar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String registro_Razon = edtRazon.getText().toString().trim();
        float registro_Precio = Float.parseFloat(edtPrecio.getText().toString().trim());
        String registro_Descripcion = edtDescripcion.getText().toString().trim();

        if(registro_Razon.isEmpty() && registro_Descripcion.isEmpty()){
            Toast.makeText(getApplicationContext(), "Ingresar los datos", Toast.LENGTH_SHORT).show();
        }else{
            Registro_Tareas(registro_Razon,registro_Precio,registro_Descripcion);
        }
    }

    private void Registro_Tareas(String registro_razon, float registro_precio, String registro_descripcion) {
        Map<String, Object> map = new HashMap<>();
        map.put("Razon",registro_razon);
        map.put("Precio",registro_precio);
        map.put("Descripcion",registro_descripcion);
        mfirestore.collection("Tareas").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(), "Creado Exitosamente", Toast.LENGTH_SHORT).show();
                finish();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error al ingresar", Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void Volver_Panel_Registro(View view) {
        Intent intent = new Intent(this, DetalleRegistroCategorias.class);
        startActivity(intent);
        //nuevo intiti que esta llamando al login class
    }
}