package com.example.myapplication;
import com.example.myapplication.models.Desperfectos;
import com.example.myapplication.models.Inspector;
import com.example.myapplication.models.Reclamo;
import com.example.myapplication.models.Servicios;
import com.example.myapplication.models.Sitios;
import com.example.myapplication.models.Usuarios;
import com.example.myapplication.models.Vecinos;
import com.example.myapplication.models.Reclamo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface ApiService {
    @GET("/vecinos/{documento}")
    Call<Vecinos> buscarDNI(@Path("documento") String documento);

    @GET("/usuarios/{documento}")
    Call<Usuarios> buscarUsuario(@Path("documento") String documento);

    @GET("/personal/{documento}")
    Call<Inspector> buscarInspector(@Path("documento") String documento);

    @POST("/usuarios/registrarUsuario")
    Call <Void> registrarUsuario(@Body Usuarios usuario);

    @POST("/email/enviar/to/{to}/subject/{subject}/body/{body}")
    Call<Void> enviarCorreoVerificacion(
            @Path("to") String to,
            @Path("subject") String subject,
            @Path("body") String body
    );

    @POST("/usuarios/cambiarContrasenia")
    Call<Usuarios> cambiarContraseniaUser(@Body Usuarios usuario);

    @POST("/personal/cambiarContrasenia")
    Call<Inspector> cambiarContraseniaInspec(@Body Inspector inspector);

    @GET("/servicios/tipo={tipo}")
    Call<List<Servicios>> listarPorTipo(@Path("tipo") String tipo);

    @POST("/servicios/registrar")
    Call<Void> registrarServicio(@Body Servicios servicio);

    @GET("/reclamos")
    Call<List<Reclamo>> getReclamos();

    @GET("/reclamos/{id}")
    Call<Reclamo> getReclamoById(@Path("id") int id);

    @POST("/reclamos/registrar")
    Call<Void> registrarReclamo(@Body Reclamo reclamo);

    @GET("/sitios")
    Call<List<Sitios>> listarSitios();
    @GET("/desperfectos")
    Call<List<Desperfectos>> listarDesperfectos();
}

