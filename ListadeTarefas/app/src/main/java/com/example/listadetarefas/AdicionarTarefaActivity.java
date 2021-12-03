package com.example.listadetarefas;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.listadetarefas.dao.TarefaDao;
import com.example.listadetarefas.model.Tarefa;
import com.google.android.material.textfield.TextInputEditText;

public class AdicionarTarefaActivity extends AppCompatActivity {

    private TextInputEditText edtAdicionarTarefa;
    private Tarefa tarefaAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);

        edtAdicionarTarefa = findViewById(R.id.edtAdicionarTarefa);

        //Recuperar tarefa,caso seja edição
        tarefaAtual = (Tarefa) getIntent().getSerializableExtra("tarefaSelecionada");

        //Configurar tarefa na caixa de texto
        if ( tarefaAtual != null ){
            edtAdicionarTarefa.setText( tarefaAtual.getNomeTarefa() );
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch ( item.getItemId() ){
            case R.id.action_settings:
                Toast.makeText(AdicionarTarefaActivity.this, "Config", Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_salvar:
                TarefaDao tarefaDao = new TarefaDao( getApplicationContext() );

                if ( tarefaAtual != null ){ // alterar
                    String receberTarefa = edtAdicionarTarefa.getText().toString();
                    if ( !receberTarefa.isEmpty()){

                        Tarefa tarefa = new Tarefa();
                        tarefa.setNomeTarefa( receberTarefa );
                        tarefa.setId( tarefaAtual.getId() );

                        //atualizar no banco de dados
                        if ( tarefaDao.alterarTarefa( tarefa ) ){
                            finish();
                            Toast.makeText(getApplicationContext(), "Sucesso ao atualizar a tarefa!", Toast.LENGTH_SHORT).show();
                        } else {

                        }
                    }
                } else { // salvar
                    String receberTarefa = edtAdicionarTarefa.getText().toString();
                    if ( !receberTarefa.isEmpty()){
                        tarefaDao.inserirTarefa(receberTarefa);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Digite algo para salvar", Toast.LENGTH_SHORT).show();
                    }
                }

                break;

            case R.id.action_editar:
                Toast.makeText(AdicionarTarefaActivity.this, "Editar", Toast.LENGTH_SHORT).show();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

}