package com.example.autonoma.abcdwebservices;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.autonoma.abcdwebservices.adapter.MainAdapter;
import com.example.autonoma.abcdwebservices.api.UsuarioAPI;
import com.example.autonoma.abcdwebservices.model.Usuario;
import com.example.autonoma.abcdwebservices.model.Usuarios;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    //
    ListView lvListado;
    //
    Retrofit retrofit;
    UsuarioAPI usuarioAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvListado = (ListView) findViewById(R.id.lvListado);
        //
        retrofit = new MainAdapter().getAdapter();
        usuarioAPI = retrofit.create(UsuarioAPI.class);
        //obtengo los datos
        Call<Usuarios> usuariosCall = usuarioAPI.getAllUsuarios();
        //escucha
        usuariosCall.enqueue(new Callback<Usuarios>() {
            @Override
            public void onResponse(Call<Usuarios> call, Response<Usuarios> response) {
                Log.d("usuariosCall","onResponse");
                Log.d("usuariosCall-Total",response.body().getTotal());
                List<Usuario> usuario = response.body().getData();
                List<String> usuarioString = new ArrayList<>(usuario.size());
                //lleno el listado
                for(Usuario oUsuario:usuario){
                    usuarioString.add(oUsuario.getFirst_name());
                }
                //creo el adaptador para el listview
                ArrayAdapter<String> adaptador =
                        new ArrayAdapter<String>(
                        MainActivity.this,
                                android.R.layout.simple_list_item_1,
                                usuarioString);
                //asignar el adaptador al Listview
                lvListado.setAdapter(adaptador);
            }

            @Override
            public void onFailure(Call<Usuarios> call, Throwable t) {
                Log.d("usuariosCall","onFailure");
            }
        });

    }
}
