package com.example.dineriumaplicacion.Adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dineriumaplicacion.Clases.Tareas;
import com.example.dineriumaplicacion.Clases.Tareas;
import com.example.dineriumaplicacion.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class AdaptadorTareas extends FirestoreRecyclerAdapter<Tareas,  AdaptadorTareas.ViewHolder> {

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AdaptadorTareas(@NonNull FirestoreRecyclerOptions<Tareas> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Tareas tareas) {
        holder.txtTarea.setText(tareas.getRazon());
        holder.txtDescripcion.setText(tareas.getDescripcion());
        holder.txtPrecio.setText(String.valueOf(tareas.getPrecio()));

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_tareas, parent,false);
        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTarea,txtPrecio,txtDescripcion;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTarea = itemView.findViewById(R.id.txtTarea);
            txtPrecio = itemView.findViewById(R.id.txtPrecio);
            txtDescripcion = itemView.findViewById(R.id.txtDescripcion);
        }
    }
}
