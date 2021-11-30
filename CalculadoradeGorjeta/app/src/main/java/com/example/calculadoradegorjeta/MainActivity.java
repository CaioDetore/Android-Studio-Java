package com.example.calculadoradegorjeta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText edtValor;
    private TextView txtPorcentagem;
    private TextView txtGorjeta;
    private TextView txtTotal;
    private SeekBar sbPorcentagem;

    private double porcentagem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtValor = findViewById(R.id.edtValor);
        txtPorcentagem = findViewById(R.id.txtPorcentagem);
        txtGorjeta = findViewById(R.id.txtGorjeta);
        txtTotal = findViewById(R.id.txtTotal);
        sbPorcentagem = findViewById(R.id.sbPorcentagem);

        sbPorcentagem.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                porcentagem = progress;
                txtPorcentagem.setText(porcentagem + "%");
                calcular();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void calcular(){
        String valorRecuperado = edtValor.getText().toString();
        if (valorRecuperado == null || valorRecuperado.equals("")){
            Toast.makeText(getApplicationContext(), "Insira um valor primeiro", Toast.LENGTH_SHORT).show();
            edtValor.requestFocus();
        } else {
            double valorDigitado = Double.parseDouble(valorRecuperado);
            double gorjeta = valorDigitado * porcentagem / 100.00;
            double total = valorDigitado - gorjeta;
            txtGorjeta.setText("R$ " + gorjeta);
            txtTotal.setText("R$ " + Math.round(total) );
        }
    }
}