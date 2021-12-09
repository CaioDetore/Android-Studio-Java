package com.example.organizze.activity.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.organizze.R;
import com.example.organizze.activity.config.ConfiguracaoFirebase;
import com.example.organizze.activity.helper.Base64Custom;
import com.example.organizze.activity.helper.DateCustom;
import com.example.organizze.activity.model.Movimentacao;
import com.example.organizze.activity.model.Usuario;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class ReceitasActivity extends AppCompatActivity {

    private EditText edtValor;
    private TextInputEditText edtData, edtCategoria, edtDescricao;
    private Movimentacao mov;
    private DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebaseDatabase();
    private FirebaseAuth auth = ConfiguracaoFirebase.getFirebaseAutenticacao();
    private Double receitaTotal;
    private Double receitaAtualizada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receitas);

        edtValor = findViewById(R.id.receitas_edtValor);
        edtData = findViewById(R.id.receitas_edtData);
        edtCategoria = findViewById(R.id.receitas_edtCategoria);
        edtDescricao = findViewById(R.id.receitas_edtDescricao);

        edtData.setText(DateCustom.dataAtual());
        recuperarReceitaTotal();
    }

    public void salvarReceita(View view){
        if ( validarCamposReceita()) {
            try {
                mov = new Movimentacao();
                String data = edtData.getText().toString();
                Double valorRecuperado = Double.parseDouble(edtValor.getText().toString());

                mov.setValor( valorRecuperado );
                mov.setCategoria( edtCategoria.getText().toString() );
                mov.setDescricao( edtDescricao.getText().toString() );
                mov.setData( data );
                mov.setTipo( "r" ); // "d" de despesa

                receitaAtualizada = receitaTotal + valorRecuperado;
                atualizarReceita( receitaAtualizada );

                mov.salvar( data );

                Toast.makeText(ReceitasActivity.this, "Registrado com sucesso!", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(ReceitasActivity.this, "Erro ao registrar receita..." + e.getMessage(), Toast.LENGTH_LONG).show();
            }

        finish();

    }

    }

    public Boolean validarCamposReceita(){

        String txtValor = edtValor.getText().toString();
        String txtData = edtData.getText().toString();
        String txtCategoria = edtCategoria.getText().toString();
        String txtDescricao = edtDescricao.getText().toString();

        if ( !txtValor.isEmpty() ){
            if ( !txtData.isEmpty() ){
                if ( !txtCategoria.isEmpty() ){
                    if ( !txtDescricao.isEmpty() ){

                        return true;

                    } else {
                        Toast.makeText(ReceitasActivity.this, "Insira uma descrição antes de prosseguir!", Toast.LENGTH_SHORT).show();
                        edtDescricao.requestFocus();
                        return false;
                    }
                } else {
                    Toast.makeText(ReceitasActivity.this, "Insira uma categoria antes de prosseguir!", Toast.LENGTH_SHORT).show();
                    edtCategoria.requestFocus();
                    return false;
                }
            } else {
                Toast.makeText(ReceitasActivity.this, "Insira uma data antes de prosseguir!", Toast.LENGTH_SHORT).show();
                edtData.requestFocus();
                return false;
            }
        } else {
            Toast.makeText(ReceitasActivity.this, "Insira um valor antes de prosseguir!", Toast.LENGTH_SHORT).show();
            edtValor.requestFocus();
            return false;
        }
    }

    public void recuperarReceitaTotal(){

        String emailUsuario = auth.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificarBase64( emailUsuario );
        DatabaseReference usuarioRef = firebaseRef.child("usuarios").child( idUsuario );

        usuarioRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Usuario usuario = snapshot.getValue( Usuario.class );
                receitaTotal = usuario.getReceitaTotal();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void atualizarReceita(Double receita){
        String emailUsuario = auth.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificarBase64( emailUsuario );
        DatabaseReference usuarioRef = firebaseRef.child("usuarios").child( idUsuario );

        usuarioRef.child("receitaTotal").setValue(receita);
    }

}