package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.activities.VerDetalleDenuncias;
import com.example.myapplication.models.Denuncias;


import java.util.List;


public class DenunciasAdapter extends RecyclerView.Adapter<DenunciasAdapter.ViewHolder> {

    private List<Denuncias> denunciasList;
    private Context context;


    public DenunciasAdapter(List<Denuncias> denunciasList) {
        this.denunciasList = denunciasList;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_detalle_denuncia, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Denuncias denuncia = denunciasList.get(position);
        holder.textViewId.setText("ID: " + denuncia.getIdDenuncias());
        holder.textViewSitio.setText("Sitio: " + denuncia.getIdSitio());
        holder.textViewDescripcion.setText("Descripción: " + denuncia.getDescripcion());
        int id = denuncia.getIdDenuncias();


        // Configurar el botón "Ver detalle" si es necesario
        holder.buttonVerDetalle.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), VerDetalleDenuncias.class);
            intent.putExtra("DENUNCIA_ID", id);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return denunciasList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewId;
        public TextView textViewSitio;
        public TextView textViewDescripcion;
        public Button buttonVerDetalle;



        public ViewHolder(View itemView) {
            super(itemView);
            textViewId = itemView.findViewById(R.id.textViewId);
            textViewSitio = itemView.findViewById(R.id.textViewSitio);
            textViewDescripcion = itemView.findViewById(R.id.textViewDescripcion);
            buttonVerDetalle = itemView.findViewById(R.id.buttonVerDetalle);

        }
    }
}
