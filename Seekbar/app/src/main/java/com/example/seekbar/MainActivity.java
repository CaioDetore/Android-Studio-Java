package com.example.seekbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button btnResultado;
    private TextView txtResultado;
    private SeekBar sbEscala;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnResultado = findViewById(R.id.btnResultado);
        txtResultado = findViewById(R.id.txtResultado);
        sbEscala = findViewById(R.id.sbEscala);

        btnResultado.setOnClickListener(v -> recuperarProgresso());

        sbEscala.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txtResultado.setText("Progresso: " + progress + " / " + sbEscala.getMax());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void recuperarProgresso(){
        txtResultado.setText("Resultado: " + sbEscala.getProgress());
    }
}