package com.example.passandodadosactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {
    private Button btnEnviar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEnviar = findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SegundaActivity.class);

                //Instanciar o objeto
                Usuario usuario = new Usuario("Caio", "caio@gmail.com");

                //Passar dados
                intent.putExtra("nome", "Caio");
                intent.putExtra("idade", 19);
                intent.putExtra("objeto", usuario);

                startActivity(intent);
            }
        });
    }
}