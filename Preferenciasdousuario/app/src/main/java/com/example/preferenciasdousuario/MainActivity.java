package com.example.preferenciasdousuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SharedMemory;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private Button btnSalvar;
    private TextView txtResultado;
    private TextInputEditText edtNome;
    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSalvar = findViewById(R.id.btnSalvar);
        txtResultado = findViewById(R.id.txtResultado);
        edtNome = findViewById(R.id.edtNome);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
                SharedPreferences.Editor editor = preferences.edit();

                if(edtNome.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Digite um nome para salvar.", Toast.LENGTH_SHORT).show();
                } else {
                    String nome = edtNome.getText().toString();
                    editor.putString("nome", nome);
                    editor.commit();
                }
            }
        });

        //Recuperar dados
        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);

        //Valida se há o nome em preferencias
        if ( preferences.contains("nome") ){
            String nome = preferences.getString("nome", "usuário não definido");
            txtResultado.setText("Olá, " + nome);
        } else {
            txtResultado.setText("Olá, usuário não definido");
        }

    }

}