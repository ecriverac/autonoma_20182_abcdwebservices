package com.example.autonoma.abcdwebservices;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.autonoma.abcdwebservices.adapter.MainAdapter;
import com.example.autonoma.abcdwebservices.api.UsuarioAPI;
import com.example.autonoma.abcdwebservices.model.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Main2Activity extends AppCompatActivity {
    EditText etNombre,etApellido;
    Button btnGrabar;
    UsuarioAPI usuarioAPI;
    Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //
        retrofit = new MainAdapter().getAdapter();
        usuarioAPI = retrofit.create(UsuarioAPI.class);
        //
        etNombre=findViewById(R.id.etNombre);
        etApellido=findViewById(R.id.etApellido);
        btnGrabar =findViewById(R.id.btGrabar);
        //
        btnGrabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre=etNombre.getText().toString();
                String apellido=etApellido.getText().toString();
                //
                //
                Call<Usuario> usuarioCall =
                        usuarioAPI.postUsuario(nombre,apellido);
                //
                usuarioCall.enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                        Log.d("PostUserCall","onResponse");
                        Toast.makeText(
                                Main2Activity.this,
                                "ID registrado:"+response.body().getId(),
                                Toast.LENGTH_LONG
                        ).show();
                    }

                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {

                    }
                });
            }
        });
    }
}
