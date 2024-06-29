package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class ServiciosAdapter extends RecyclerView.Adapter<ServiciosAdapter.ServicioViewHolder> {

    private List<Servicios> serviciosList;

    public ServiciosAdapter(List<Servicios> serviciosList) {
        this.serviciosList = serviciosList;
    }

    @NonNull
    @Override
    public ServicioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.servicio, parent, false);
        return new ServicioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServicioViewHolder holder, int position) {
        Servicios servicio = serviciosList.get(position);

        holder.textNombre.setText(servicio.getNombre());
        holder.textDireccion.setText(servicio.getDireccion());
        holder.textHorario.setText(servicio.getHorario());
        holder.textTelefono.setText(servicio.getTelefono());
        holder.textRubro.setText(servicio.getRubro());
        holder.textDescripcion.setText(servicio.getDescripcion());

        ImagePagerAdapter adapter = new ImagePagerAdapter(servicio.getImagenes());
        holder.viewPager.setAdapter(adapter);
        holder.tabLayout.setupWithViewPager(holder.viewPager, true);
    }

    @Override
    public int getItemCount() {
        return serviciosList.size();
    }

    static class ServicioViewHolder extends RecyclerView.ViewHolder {
        TextView textNombre, textDireccion, textHorario, textTelefono, textRubro, textDescripcion;
        ViewPager viewPager;
        TabLayout tabLayout;

        public ServicioViewHolder(@NonNull View itemView) {
            super(itemView);
            textNombre = itemView.findViewById(R.id.textNombre);
            textDireccion = itemView.findViewById(R.id.textDireccion);
            textHorario = itemView.findViewById(R.id.textHorario);
            textTelefono = itemView.findViewById(R.id.textTelefono);
            textRubro = itemView.findViewById(R.id.textRubro);
            textDescripcion = itemView.findViewById(R.id.textDescripcion);
            viewPager = itemView.findViewById(R.id.viewPager);
            tabLayout = itemView.findViewById(R.id.tabLayout);
        }
    }
}
