package com.example.autonoma.abcdwebservices.api;

import com.example.autonoma.abcdwebservices.model.Usuario;
import com.example.autonoma.abcdwebservices.model.Usuarios;
import com.example.autonoma.abcdwebservices.model.Usuarios2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UsuarioAPI {

    //get https://reqres.in/api/users/
    @GET("users")
    Call<Usuarios> getAllUsuarios();

    @GET("users/{id}")
    Call<Usuarios2> getUsuario(@Path("id") int id);

    //post https://reqres.in/api/users/api/users
    @FormUrlEncoded
    @POST("users")
    Call<Usuario> postUsuario(
            @Field("first_name") String first,
            @Field("last_name") String last);

}
