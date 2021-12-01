package com.example.snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private Button btnAbrir;
    private Button btnFechar;
    private Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAbrir = findViewById(R.id.btnAbrir);
        btnFechar = findViewById(R.id.btnFechar);

        btnAbrir.setOnClickListener(v -> {
            Snackbar.make(
                    v,
                    "Botão pressionado",
                    Snackbar.LENGTH_INDEFINITE
            ).setAction("Confirmar", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btnAbrir.setText("Botão abrir alterado");

                }
            }).setActionTextColor( getResources().getColor( R.color.teal_200 ) )
            .show();

        });
        /*
        btnFechar.setOnClickListener(v -> {
            snackbar.dismiss();
        });
        */

    }

    public void abrirSnackbar(){



    }

}