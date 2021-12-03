package com.example.listadetarefas.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.listadetarefas.helper.DbHelper;
import com.example.listadetarefas.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class TarefaDao {

    private Context context;
    private SQLiteDatabase writeIn;
    private SQLiteDatabase readOn; // by quilice

    public TarefaDao(Context context) {
        this.context = context;
        DbHelper dbHelper = new DbHelper(context);

        writeIn = dbHelper.getWritableDatabase();
        readOn = dbHelper.getReadableDatabase();

    }

    //C
    public boolean inserirTarefa(String tarefa){
        ContentValues cv = new ContentValues();
        cv.put("tarefa", tarefa);
        cv.put("concluido", "N");
        try {
            writeIn.insert(DbHelper.TABELA_TAREFA, null, cv);
            Log.i("INFO:", "Salvo com sucesso");
        } catch (Exception e){
//            Toast.makeText(context.getApplicationContext(), "Erro ao salvar", Toast.LENGTH_SHORT).show();
            Log.e("ERRO:", "Erro ao salvar" + e.getMessage());
            return false;
        }

        return true;
    }

    //R
    public List<Tarefa> listar(){
        List<Tarefa> tarefaList = new ArrayList<>();

        String sql = "SELECT * FROM " + DbHelper.TABELA_TAREFA + " ;";
        Cursor c = readOn.rawQuery(sql, null);

        while ( c.moveToNext() ){
            Tarefa tarefa = new Tarefa();

            @SuppressLint("Range") Long id = c.getLong( c.getColumnIndex("id") );
            @SuppressLint("Range") String nomeTarefa = c.getString( c.getColumnIndex("tarefa") );

            tarefa.setId(id);
            tarefa.setNomeTarefa(nomeTarefa);

            tarefaList.add(tarefa);
        }

        readOn.close();
        return tarefaList;
    }

    //U
    public boolean alterarTarefa(Tarefa tarefa){

        ContentValues cv = new ContentValues();
        cv.put("tarefa", tarefa.getNomeTarefa());

        try{
            String[] args = {tarefa.getId().toString()};
            writeIn.update(DbHelper.TABELA_TAREFA, cv, "id=?", args);
            Log.i("INFO:", "Alterado com sucesso");
        } catch (Exception e){
            Log.e("ERRO:", "Erro ao alterar" + e.getMessage());
            return false;
        }

        return true;
    }

    //D
    public boolean deletarTarefa(Tarefa tarefa){

        try{
            String[] args = {tarefa.getId().toString()};
            writeIn.delete(DbHelper.TABELA_TAREFA, "id=?", args);
            Log.i("INFO:", "Removido com sucesso");
        } catch (Exception e){
            Log.e("ERRO:", "Erro ao excluir" + e.getMessage());
            return false;
        }

        return true;
    }

}
