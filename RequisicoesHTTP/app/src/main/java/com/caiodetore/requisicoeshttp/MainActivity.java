package com.caiodetore.requisicoeshttp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private Button btnRecuperar;
    private TextView txtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRecuperar = findViewById(R.id.btnRecuperar);
        txtResultado = findViewById(R.id.txtResultado);

        btnRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyTask task = new MyTask();

                String moeda = "USD";
                String urlApi = "https://blockchain.info/tobtc?currency="+moeda+"&value=500";

                String urlApiNoFiltro = "https://blockchain.info/ticker";

                String cep = "01310100";
                String urlCep = "https://viacep.com.br/ws/"+cep+"/json/";

                task.execute(urlApiNoFiltro);
            }
        });

    }

    class MyTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {

            URL url = null;
            String stringUrl = strings[0];
            InputStream inputStream = null;
            InputStreamReader inputStreamReader = null;
            StringBuffer buffer = null;

            try {

                url = new URL(stringUrl);
                HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

                // Recuperar os dados em Bytes
                inputStream = conexao.getInputStream();

                // Lê os dados em bytes e decodifica para caracteres
                inputStreamReader = new InputStreamReader( inputStream );

                // Objeto utilizado para leitura dos caracteres do InputStremaReader
                BufferedReader reader = new BufferedReader( inputStreamReader );
                buffer = new StringBuffer();
                String linha = "";

                while ((linha = reader.readLine()) != null) {
                    buffer.append( linha );
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return buffer.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            String logradouro = null;
            String cep = null;
            String complemento = null;
            String bairro = null;
            String localidade = null;
            String url = null;

            String objetoValor = null;
            String valorMoeda = null;
            String simbolo = null;

            try {
                /*JSONObject jsonObject = new JSONObject(s);
                logradouro = jsonObject.getString("logradouro");
                cep = jsonObject.getString("cep");
                complemento = jsonObject.getString("complemento");
                bairro = jsonObject.getString("bairro");
                localidade = jsonObject.getString("localidade");
                url = jsonObject.getString("url"); */

                JSONObject jsonObject = new JSONObject(s);
                objetoValor = jsonObject.getString("BRL");

                JSONObject jsonObjectReal = new JSONObject(objetoValor);
                valorMoeda = jsonObjectReal.getString("last");
                simbolo = jsonObjectReal.getString("symbol");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            txtResultado.setText(simbolo+" "+valorMoeda);
            Log.i("Valor:", ""+objetoValor);
//            txtResultado.setText(logradouro+" \n "+cep+" \n "+complemento+" \n "+bairro+" \n "+localidade+" \n "+url);

        }
    }
}