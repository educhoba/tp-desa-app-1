package com.example.myapplication;
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


}

