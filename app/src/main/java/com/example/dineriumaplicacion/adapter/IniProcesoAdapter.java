package com.example.dineriumaplicacion.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dineriumaplicacion.Inicio;
import com.example.dineriumaplicacion.R;
import com.example.dineriumaplicacion.model.IniProceso;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class IniProcesoAdapter extends FirestoreRecyclerAdapter<IniProceso, IniProcesoAdapter.ViewHolder> {

    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    Activity activity;
    public IniProcesoAdapter(@NonNull FirestoreRecyclerOptions<IniProceso> options, Activity activity) {
        super(options);
        this.activity = activity;
    }

    @Override
    protected void onBindViewHolder(@NonNull IniProcesoAdapter.ViewHolder holder, int position, @NonNull IniProceso model) {
        DocumentSnapshot documentSnapshot = getSnapshots().getSnapshot(holder.getAdapterPosition());
        final String id = documentSnapshot.getId();

        holder.txtIniAhorro.setText(model.getObjMontoAhorro());
        holder.txtIniPresupuesto.setText(model.getObjPresupuesto());
        holder.txtIniTiempo.setText(model.getObjFechaPropuesta());

        holder.btnIniEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, Inicio.class);
                i.putExtra("idObjetivo", id);
                activity.startActivity(i);
            }
        });

        holder.btnIniCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cancelar(id);
            }
        });
    }

    private void Cancelar(String id) {
        firebaseFirestore.collection("tblObjetivos").document(id).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(activity, "Exito al eliminar", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(activity, Inicio.class);
                activity.startActivity(i);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(activity, "Error al eliminar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @NonNull
    @Override
    public IniProcesoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_ini_proceso_adapter,
                parent, false);
        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtIniAhorro, txtIniPresupuesto, txtIniTiempo;
        Button btnIniCancelar, btnIniEditar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtIniAhorro = itemView.findViewById(R.id.txtIniAhorro);
            txtIniPresupuesto = itemView.findViewById(R.id.txtIniPresupuesto);
            txtIniTiempo = itemView.findViewById(R.id.txtIniTiempo);

            btnIniCancelar = itemView.findViewById(R.id.btnIniCancelar);
            btnIniEditar = itemView.findViewById(R.id.btnIniEditar);
        }
    }
}
