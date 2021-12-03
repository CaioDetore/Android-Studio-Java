package com.example.listadetarefas.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private Context context;

    private static final String BANCO = "ListaTarefaBD";
    private static final int VERSAO = 1;

    public static final String TABELA_TAREFA = "tarefa";

    public DbHelper(@Nullable Context context) {
        super(context, BANCO, null, VERSAO);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABELA_TAREFA + "(" +
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "tarefa VARCHAR(50) NOT NULL," +
                "concluido CHAR(1) NOT NULL " +
                " ); ";
        try{
            db.execSQL(sql);
            Log.i("INFO DB", "Sucesso ao criar a tabela");
        } catch (Exception e) {
            Toast.makeText(context, "Erro: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
