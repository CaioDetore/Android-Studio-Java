package com.example.progressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ProgressBar pbHorizontal;
    private ProgressBar pbCircular;
    private Button btnCarregar;
    private int progresso = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pbHorizontal = findViewById(R.id.pbHorizontal);
        pbCircular = findViewById(R.id.pbCircular);
        btnCarregar = findViewById(R.id.btnCarregar);

        btnCarregar.setOnClickListener(v -> carregarPb());
    }

    public void carregarPb(){
        this.progresso += 2;
        pbHorizontal.setProgress(this.progresso);
        pbCircular.setVisibility(View.VISIBLE);

        if (this.progresso == 10){
            pbCircular.setVisibility(View.GONE);
            btnCarregar.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Carregado com sucesso", Toast.LENGTH_SHORT).show();
        }

    }
}