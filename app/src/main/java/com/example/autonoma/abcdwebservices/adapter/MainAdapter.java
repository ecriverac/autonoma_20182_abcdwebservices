package com.example.autonoma.abcdwebservices.adapter;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainAdapter {

    public Retrofit getAdapter(){
        //costruido el adaptador que apunta
        //a la url base
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //devuelvo el adaptador a quien lo necesite
        //ejecutar
        return retrofit;

    }

}
