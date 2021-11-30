package com.example.alcoolougasolina;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText edtGasolina, edtAlcool;
    private TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtGasolina = findViewById(R.id.edtGasolina);
        edtAlcool   = findViewById(R.id.edtAlcool);
        txtResult   = findViewById(R.id.txtResult);
    }

    public void calcularPreco(View view){
        //recupera valores digitados
        String gasolina = edtGasolina.getText().toString();
        String alcool = edtAlcool.getText().toString();

        //validar os campos digitados
        Boolean resultado = validarCampos(alcool, gasolina);
        if( resultado ){
            //Convertendo string para números
            Double valorAlcool = Double.parseDouble(alcool);
            Double valorGasolina = Double.parseDouble(gasolina);

            if ((valorAlcool / valorGasolina) >= 0.7) {
                txtResult.setText("Melhor usar gasolina");
            } else {
                txtResult.setText("Melhor utilizar álcool");
            }
        } else {
            txtResult.setText("Preencha os preços primeiro!");
        }
    }

    public Boolean validarCampos(String pAlcool, String pGasolina){
        Boolean camposValidados = true;
        if(pAlcool == null || pAlcool.equals("")){
            camposValidados = false;
        } else if (pGasolina == null || pGasolina.equals("")){
            camposValidados = false;
        }
        return camposValidados;
    }
}