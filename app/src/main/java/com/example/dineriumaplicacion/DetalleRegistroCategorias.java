package com.example.dineriumaplicacion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dineriumaplicacion.Adaptador.AdaptadorTareas;
import com.example.dineriumaplicacion.Clases.Tareas;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class DetalleRegistroCategorias extends AppCompatActivity implements View.OnClickListener {
    Button btnAgregar;
    RecyclerView mRecycler;
    AdaptadorTareas mAdapter;
    TextView txtPrecioTotal;



    FirebaseFirestore mFirestore;


    @Override
    protected void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_registro_categorias);
        mFirestore = FirebaseFirestore.getInstance();
        mRecycler = findViewById(R.id.recycler_tareas);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        Query query = mFirestore.collection("tblTareas");
        FirestoreRecyclerOptions<Tareas> firestoreRecyclerOptions =
                new FirestoreRecyclerOptions.Builder<Tareas>().setQuery(query,Tareas.class).build();

        mAdapter = new AdaptadorTareas(firestoreRecyclerOptions);
        mAdapter.notifyDataSetChanged();
        mRecycler.setAdapter(mAdapter);
        btnAgregar = findViewById(R.id.btnAgregar);
        btnAgregar.setOnClickListener(this);
        txtPrecioTotal = findViewById(R.id.txtPrecioTotal); // Reemplaza "txtPrecioTotal" con el ID correcto de tu TextView

        getTotalPrecios(); // Llama a esta función para obtener y mostrar el precio total al iniciar la actividad


    }

    private void getTotalPrecios() {
        mFirestore.collection("tblTotal").document("Precios").get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            double totalPrecios = documentSnapshot.getDouble("TotalPrecios");
                            txtPrecioTotal.setText(String.valueOf(totalPrecios));
                        } else {
                            txtPrecioTotal.setText("0");
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Error al obtener el precio total", Toast.LENGTH_SHORT).show();
                    }
                });

    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, DetalleRegistro.class);
        startActivity(intent);

    }
}