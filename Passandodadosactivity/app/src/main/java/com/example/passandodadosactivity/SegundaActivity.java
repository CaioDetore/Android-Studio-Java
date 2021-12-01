package com.example.passandodadosactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SegundaActivity extends AppCompatActivity {
    private TextView txtNome;
    private TextView txtIdade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);

        txtNome = findViewById(R.id.txtNome);
        txtIdade = findViewById(R.id.txtIdade);

        //Recuperar os dados enviados
        Bundle dados = getIntent().getExtras();
        String nome = dados.getString("nome");
        int idade = dados.getInt("idade");

        Usuario usuario = (Usuario) dados.getSerializable("objeto");

        //Configurar valores recuperados
        txtNome.setText( usuario.getEmail() );
        txtIdade.setText( String.valueOf( idade ));
    }
}