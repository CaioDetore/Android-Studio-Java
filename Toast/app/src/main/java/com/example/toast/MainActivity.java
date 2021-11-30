package com.example.toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnToast;
    Button btnDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnToast = findViewById(R.id.btnAbrir);
        btnDialog = findViewById(R.id.button);

        btnToast.setOnClickListener(v -> abrirToast());

        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirDialog();
            }
        });
    }

    public void abrirDialog(){
//        Instanciar alertDialog
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
//        Configurar titulo e mensagem
        dialog.setTitle("Título da Dialog");
        dialog.setMessage("Mensagem da Dialog");
//        Definir cancelamento
        dialog.setCancelable(false);
//        Definir icone
        dialog.setIcon(android.R.drawable.ic_btn_speak_now);

//        Configurar ações sim e não

        dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(
                        getApplicationContext(),
                        "Executar ação ao clicar SIM",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        // ação feita com lambda
        dialog.setNegativeButton("Não", (dialog1, which) -> Toast.makeText(
                getApplicationContext(),
                "Executar ação ao clicar NÃO",
                Toast.LENGTH_SHORT
        ).show());

//        Criar e exibir AlertDialog
        dialog.create();
        dialog.show();
    }

    public void abrirToast(){
        ImageView imagem = new ImageView(getApplicationContext());
        imagem.setImageResource(android.R.drawable.star_big_off);

        TextView textView = new TextView(getApplicationContext());
        textView.setBackgroundResource(R.color.teal_200);
//        textView.setText("Olá Toast");

        Toast toast = new Toast( getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(imagem);
//        toast.setView(textView);
        toast.show();

        Toast.makeText(getApplicationContext(), "Ação realizada com sucesso", Toast.LENGTH_SHORT).show();
    }
}