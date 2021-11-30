package com.example.sorteioapp;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void alterarNum(View view) {
        TextView texto = findViewById(R.id.txtNum);
        int numero = new Random().nextInt(11);
        texto.setText("Número: " + numero);
    }
}