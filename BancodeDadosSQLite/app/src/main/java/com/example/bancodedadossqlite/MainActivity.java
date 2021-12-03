package com.example.bancodedadossqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            //Criar bancode dados
            SQLiteDatabase bancoDados = openOrCreateDatabase("app", MODE_PRIVATE, null );

            //Criar tabela
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas (" +
                    "nome VARCHAR," +
                    "idade INT(3) )");

            //Inserir dados
//            bancoDados.execSQL("INSERT INTO pessoas (nome, idade) VALUES ('Mariana', 20)");
//            bancoDados.execSQL("INSERT INTO pessoas (nome, idade) VALUES ('Pedro', 18)");

            //Recuperar pessoas
            String consulta = "SELECT nome, idade FROM pessoas WHERE nome = 'Caio' AND idade = 19 ";
            Cursor cursor = bancoDados.rawQuery(consulta, null);

            //Indices da tabela
            int indiceNome = cursor.getColumnIndex("nome");
            int indiceIdade = cursor.getColumnIndex("idade");

            cursor.moveToFirst();
            while (cursor != null){

                String nome = cursor.getString(indiceNome);
                String idade = cursor.getString(indiceIdade);

                Log.i("RESULTADO: ", "nome: " + nome + " | idade: " + idade);
                cursor.moveToNext();
            }

        } catch (Exception e){
            e.printStackTrace();
        }

    }
}