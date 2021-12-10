package com.caiodetore.entendendothreads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnIniciar;
    private int numero;
    private Handler handler = new Handler();
    private Boolean paraExecucao = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIniciar = findViewById(R.id.btnIniciar);

    }

    public void iniciarThread(View view){
        // para executar a thread
//        MyThread myThread = new MyThread();
//        myThread.start();

        paraExecucao = false;
        MyRunnable myRunnable = new MyRunnable();
        new Thread( myRunnable ).start();

    }

    public void pararThread(View view){
        paraExecucao = true;
    }

    class MyRunnable implements Runnable {
        @Override
        public void run() {
            for (int i=0; i <= 15; i++ ) {

                if (paraExecucao)
                    return;

                numero = i;
                Log.i("Thread: ", "Contador = " + i);

                //envia o comando a seguir para a UI Thread, assim é possível alterar a interface de usuario (UI) | UIThread = thread principal
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        btnIniciar.setText("Contador: " + numero);
                    }
                });
                // os dois metodos fazem a mesma coisa, isso é porque o ´runOnUiThread´ possui uma "implementação" do handler
                //envia uma mensagem, que executa um comando, para a Thread que instanciou a classe Handler, nesse caso a uithread
                //a desvantagem do handler é a necessidade de instanciar a classe e a vantagem são as funções ´.post...´
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        btnIniciar.setText("Contador: " + numero);
//                    }
//                });

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            int i = 0;
            for ( i=0; i <= 15; i++ ) {
                Log.i("Thread: ", "Contador = " + i);

//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        btnIniciar.setText("Contador: " + numero);
//                    }
//                }); aqui na thread esse comando não funciona

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}