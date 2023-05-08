package com.example.dineriumaplicacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dineriumaplicacion.Adaptador.AdaptadorTareas;
import com.example.dineriumaplicacion.Clases.Tareas;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class DetalleRegistroCategorias extends AppCompatActivity implements View.OnClickListener {
    Button btnAgregar;
    RecyclerView mRecycler;
    AdaptadorTareas mAdapter;
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
        Query query = mFirestore.collection("Tareas");
        FirestoreRecyclerOptions<Tareas> firestoreRecyclerOptions =
                new FirestoreRecyclerOptions.Builder<Tareas>().setQuery(query,Tareas.class).build();

        mAdapter = new AdaptadorTareas(firestoreRecyclerOptions);
        mAdapter.notifyDataSetChanged();
        mRecycler.setAdapter(mAdapter);
        btnAgregar = findViewById(R.id.btnAgregar);
        btnAgregar.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, DetalleRegistro.class);
        startActivity(intent);
        //nuevo intiti que esta llamando al login class
    }
}