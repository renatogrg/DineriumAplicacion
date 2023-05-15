package com.example.dineriumaplicacion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.example.dineriumaplicacion.adapter.IniProcesoAdapter;
import com.example.dineriumaplicacion.model.IniProceso;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.Timer;
import java.util.TimerTask;

public class InicioProceso extends AppCompatActivity {

    RecyclerView recyclerView;
    IniProcesoAdapter iniProcesoAdapter;
    FirebaseFirestore firebaseFirestore;
    ProgressBar prbIniProgreso;
    ImageButton imgBtnIniConfiguracion, imgBtnIniInversiones;
    Button btnIniObjetivos, btnIniRegistrarGastos, btnIniVerCategorias, btnIniReporte;
    int counter=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_proceso);
        firebaseFirestore = FirebaseFirestore.getInstance();

        prbIniProgreso = (ProgressBar) findViewById(R.id.prbIniProgreso);

        imgBtnIniConfiguracion = (ImageButton) findViewById(R.id.imgBtnIniConfiguracion);
        imgBtnIniInversiones = (ImageButton) findViewById(R.id.imgBtnIniInversiones);
        btnIniObjetivos = (Button) findViewById(R.id.btnIniObjetivos);
        btnIniRegistrarGastos = (Button) findViewById(R.id.btnIniRegistrarGastos);
        btnIniVerCategorias = (Button) findViewById(R.id.btnIniVerCategorias );
        btnIniReporte = (Button) findViewById(R.id.btnIniReporte);

        recyclerView = (RecyclerView) findViewById(R.id.rvIniProceso);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Query query = firebaseFirestore.collection("tblObjetivos");
        FirestoreRecyclerOptions<IniProceso> firestoreRecyclerOptions =
                new FirestoreRecyclerOptions.Builder<IniProceso>().setQuery(query, IniProceso.class).build();
        iniProcesoAdapter = new IniProcesoAdapter(firestoreRecyclerOptions, this);
        iniProcesoAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(iniProcesoAdapter);

        Progreso();

        imgBtnIniConfiguracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Configuracion();
            }
        });

        imgBtnIniInversiones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Inversiones();
            }
        });

        btnIniObjetivos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MisObjetivos();
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

    private void Progreso() {
        Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                counter++;
                prbIniProgreso.setProgress(counter);
                if(counter == 100) t.cancel();
            }
        };
        t.schedule(tt, 0, 100);
    }

    public InicioProceso() {
        super();
    }

    @Override
    protected void onStart() {
        super.onStart();
        iniProcesoAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        iniProcesoAdapter.stopListening();
    }

    public void Configuracion() {
        Intent Configuracion = new Intent(this, com.example.dineriumaplicacion.Configuracion.class);
        startActivity(Configuracion);
    }

    public void Inversiones() {
        Intent Inversiones = new Intent(this, com.example.dineriumaplicacion.Inversiones.class);
        startActivity(Inversiones);
    }

    public void MisObjetivos() {
        Intent MisObjetivos = new Intent(this, com.example.dineriumaplicacion.MisObjetivos.class);
        startActivity(MisObjetivos);
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
}