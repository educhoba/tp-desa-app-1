package com.example.myapplication;
import com.example.myapplication.activities.MainActivity;
import com.example.myapplication.activities.VerDetalleReclamos;
import com.example.myapplication.models.Reclamos;
import com.google.android.material.tabs.TabLayout;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import androidx.viewpager.widget.ViewPager;

import java.util.List;


public class ReclamosAdapter extends RecyclerView.Adapter<ReclamosAdapter.ViewHolder> {

    private List<Reclamos> reclamosList;
    private Context context;


    public ReclamosAdapter(List<Reclamos> reclamosList) {
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
        Reclamos reclamo = reclamosList.get(position);
        holder.textViewId.setText("Nro de reclamo: " + reclamo.getIdReclamo());
        holder.textViewDescripcion.setText("Descripción: " + reclamo.getDescripcion());
        int id = reclamo.getIdReclamo();


        // Configurar el botón "Ver detalle" si es necesario
        holder.buttonVerDetalle.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), VerDetalleReclamos.class);
            intent.putExtra("RECLAMO_ID", id);
            holder.itemView.getContext().startActivity(intent);
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
        ViewPager viewPager;
        TabLayout tabLayout;


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
