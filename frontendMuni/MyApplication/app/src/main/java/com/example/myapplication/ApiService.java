package com.example.myapplication;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("/vecinos/{documento}")
    Call<Boolean> buscarDNI(@Path("documento") String documento);

    @GET("/usuarios/{documento}")
    Call<Usuarios> buscarUsuario(@Path("documento") String documento);

}

