package com.example.autonoma.abcdwebservices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.autonoma.abcdwebservices.adapter.MainAdapter;
import com.example.autonoma.abcdwebservices.api.UsuarioAPI;
import com.example.autonoma.abcdwebservices.model.Usuario;
import com.example.autonoma.abcdwebservices.model.Usuarios;
import com.example.autonoma.abcdwebservices.model.Usuarios2;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    //
    ListView lvListado;
    Button btnAgregar;
    //
    Retrofit retrofit;
    UsuarioAPI usuarioAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvListado = (ListView) findViewById(R.id.lvListado);
        btnAgregar = (Button) findViewById(R.id.btnAgregar);
        //
        retrofit = new MainAdapter().getAdapter();
        usuarioAPI = retrofit.create(UsuarioAPI.class);
        //
        lvListado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("onItemClick","click");

                Call<Usuarios2> usuarioCall =
                        usuarioAPI.getUsuario(2);

                usuarioCall.enqueue(new Callback<Usuarios2>() {
                    @Override
                    public void onResponse(Call<Usuarios2> call, Response<Usuarios2> response) {
                        Log.d("onItemClick2",response.body().getData().getFirst_name().toString());
                    }

                    @Override
                    public void onFailure(Call<Usuarios2> call, Throwable t) {
                        Log.d("onItemClick2",t.getMessage());
                    }
                });

            }
        });
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
        }); //fin enqueue
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });
    }
}
