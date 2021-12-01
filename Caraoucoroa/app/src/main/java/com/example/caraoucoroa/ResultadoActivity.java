package com.example.caraoucoroa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ResultadoActivity extends AppCompatActivity {
    private ImageView imgResultado;
    private ImageView ibVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        imgResultado = findViewById(R.id.imgResultado);
        ibVoltar = findViewById(R.id.ibVoltar);
                
        //Recuperar dados
        Bundle dados = getIntent().getExtras();
        int numero = dados.getInt("numero");

        if (numero == 0) {
            imgResultado.setImageResource( R.drawable.moeda_cara);
        } else {
            imgResultado.setImageResource( R.drawable.moeda_coroa);
        }

        ibVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}