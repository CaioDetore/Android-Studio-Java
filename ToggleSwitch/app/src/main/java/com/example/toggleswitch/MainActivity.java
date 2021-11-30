package com.example.toggleswitch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    private ToggleButton tbSenha;
    private Switch swtSenha;
    private CheckBox cbSenha;
    private TextView txtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tbSenha = findViewById(R.id.tbSenha);
        swtSenha = findViewById(R.id.swtSenha);
        cbSenha = findViewById(R.id.cbSenha);
        txtResultado = findViewById(R.id.txtResultado);

        adicionarListener();
    }

    public void adicionarListener(){
        swtSenha.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    txtResultado.setText("Switch ligado");
                } else {
                    txtResultado.setText("Switch desligado");
                }
            }
        });
    }

    public void enviar(View view){
        if(cbSenha.isChecked()){
            txtResultado.setText("Cb ligado");
        } else {
            txtResultado.setText("cb desligado");
        }
    }
}