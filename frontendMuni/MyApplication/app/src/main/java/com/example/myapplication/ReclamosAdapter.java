package com.example.myapplication;
import com.example.myapplication.models.Reclamo;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;



import java.util.List;

public class ReclamosAdapter extends RecyclerView.Adapter<ReclamosAdapter.ViewHolder> {

    private List<Reclamo> reclamosList;
    private Context context;


    public ReclamosAdapter(List<Reclamo> reclamosList) {
        this.reclamosList = reclamosList;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_detalle_reclamo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Reclamo reclamo = reclamosList.get(position);
        holder.textViewId.setText("ID: " + reclamo.getIdReclamo());
        holder.textViewSitio.setText("Sitio: " + reclamo.getIdSitio());
        holder.textViewDesperfecto.setText("Desperfecto: " + reclamo.getIdDesperfecto());
        holder.textViewDescripcion.setText("Descripción: " + reclamo.getDescripcion());

        // Configurar el botón "Ver detalle" si es necesario
        holder.buttonVerDetalle.setOnClickListener(v -> {
            //logica para boton
        });
    }

    @Override
    public int getItemCount() {
        return reclamosList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewId;
        public TextView textViewSitio;
        public TextView textViewDesperfecto;
        public TextView textViewDescripcion;
        public Button buttonVerDetalle;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewId = itemView.findViewById(R.id.textViewId);
            textViewSitio = itemView.findViewById(R.id.textViewSitio);
            textViewDesperfecto = itemView.findViewById(R.id.textViewDesperfecto);
            textViewDescripcion = itemView.findViewById(R.id.textViewDescripcion);
            buttonVerDetalle = itemView.findViewById(R.id.buttonVerDetalle);
        }
    }
}
