package com.example.componentesbasicos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    private EditText edtNome;
    private TextInputEditText edtEmail;
    private TextView txtResultado;

    private CheckBox cbVerde, cbBranco, cbVermelho;

    //RadioButton
    private RadioButton rbMasc, rbFem;
    private RadioGroup rgSexo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNome = findViewById(R.id.edtNome);
        edtEmail = findViewById(R.id.edtEmail);
        txtResultado = findViewById(R.id.txtResultado);

        //Checkbox
        cbBranco    = findViewById(R.id.cbBranco);
        cbVerde     = findViewById(R.id.cbVerde);
        cbVermelho  = findViewById(R.id.cbVermelho);

        //RadioButton
        rbMasc  = findViewById(R.id.rbMasc);
        rbFem   = findViewById(R.id.rbFem);
        rgSexo  = findViewById(R.id.rgSexo);

        radioButton();
    }

    public void radioButton(){
        rgSexo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if( checkedId == R.id.rbMasc){
                    txtResultado.setText("Masculino");
                } else if ( checkedId == R.id.rbFem ){
                    txtResultado.setText("Feminino");
                }
            }
        });
//        if (rbMasc.isChecked()){
//            txtResultado.setText("Masculino");
//        } else if (rbFem.isChecked()) {
//            txtResultado.setText("Feminino");
//        }
    }

    public void enviar(View view){
//        checkbox();
//        radioButton();
//        String nome = edtNome.getText().toString();
//        String email = edtEmail.getText().toString();
//        txtResultado.setText("o email de " + nome + " é: " + email  );
//        limpar();
    }

    public void checkbox(){
        String text = "";
        if ( cbVerde.isChecked() ){
            text = "Verde selecionado.";
        }
        if ( cbBranco.isChecked() ){
            text = text +" Branco selecionado.";
        }
        if ( cbVermelho.isChecked() ){
            text = text +" Vermelho selecionado.";
        }
        txtResultado.setText( text );
    }

    public void limpar(){
        edtNome.setText("");
        edtEmail.setText("");
    }

}