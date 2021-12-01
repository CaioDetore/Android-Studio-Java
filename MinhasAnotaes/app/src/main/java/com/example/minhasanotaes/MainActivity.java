package com.example.minhasanotaes;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.minhasanotaes.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private AnotacaoPreferencias preferencias;
    private EditText edtAnotacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        preferencias = new AnotacaoPreferencias( getApplicationContext() );
        edtAnotacao = findViewById(R.id.edtAnotacao);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //validar se foi digitado algo
                String textoRecuperado = edtAnotacao.getText().toString();
                if( textoRecuperado.equals("")){
                    Snackbar.make(view, "Preencha a anotação!", Snackbar.LENGTH_LONG).show();
                } else {
                    preferencias.salvarAnotacao(textoRecuperado);
                    Snackbar.make(view, "Anotação salva com sucesso!", Snackbar.LENGTH_LONG).show();
                }
            }
        });

        //Recuperar anotacao
        String anotacao = preferencias.recuperarAnotacao();
        if (!anotacao.equals("")){
            edtAnotacao.setText( anotacao );
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}