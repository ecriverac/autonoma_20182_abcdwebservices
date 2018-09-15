package com.example.autonoma.abcdwebservices.api;

import com.example.autonoma.abcdwebservices.model.Usuarios;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UsuarioAPI {

    //get https://reqres.in/api/users/
    @GET("users")
    Call<Usuarios> getAllUsuarios();
}
