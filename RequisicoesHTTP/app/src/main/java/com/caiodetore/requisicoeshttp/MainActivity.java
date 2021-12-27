package com.caiodetore.requisicoeshttp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.caiodetore.requisicoeshttp.api.CEPService;
import com.caiodetore.requisicoeshttp.api.DataService;
import com.caiodetore.requisicoeshttp.model.CEP;
import com.caiodetore.requisicoeshttp.model.Foto;
import com.caiodetore.requisicoeshttp.model.Postagem;

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
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Button btnRecuperar;
    private TextView txtResultado;
    private Retrofit retrofit;
    //private List<Foto> listaFotos = new ArrayList<>();
    private List<Postagem> listaPostagens = new ArrayList<>();
    private DataService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRecuperar = findViewById(R.id.btnRecuperar);
        txtResultado = findViewById(R.id.txtResultado);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                //.baseUrl("https://viacep.com.br/ws/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(DataService.class);

        btnRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //recuperarListaRetrofit();
                //recuperarCEPRetrofit();
                //salvarPostagem();
                //atualizarPostagem();
                removerPostagem();

                /*
                MyTask task = new MyTask();
                String moeda = "USD";
                String urlApi = "https://blockchain.info/tobtc?currency="+moeda+"&value=500";
                String urlApiNoFiltro = "https://blockchain.info/ticker";
                String cep = "01310100";
                String urlCep = "https://viacep.com.br/ws/"+cep+"/json/";
                task.execute(urlApiNoFiltro);*/
            }
        });

    }

    private void removerPostagem(){
        Call<Void> call = service.removerPostagem(2);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()){
                    txtResultado.setText("Status: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }


    private void atualizarPostagem(){
        //Configura objeto postagem
        //Postagem postagem = new Postagem("1234", "Título postagem!", "Corpo postagem");
        Postagem postagem = new Postagem();
        postagem.setBody("Cospo da postagem alterado");

        Call<Postagem> call = service.atualizarPostagem(2, postagem);

        call.enqueue(new Callback<Postagem>() {
            @Override
            public void onResponse(Call<Postagem> call, Response<Postagem> response) {
                if (response.isSuccessful()){
                    Postagem postagemResposta = response.body();
                    txtResultado.setText(
                            "Status: " + response.code() +
                            "\nid: " + postagemResposta.getId() +
                            "\nuserId: " + postagemResposta.getUserId() +
                            "\ntítulo: " + postagemResposta.getTitle() +
                            "\nbody: " + postagemResposta.getBody()
                    );
                }
            }

            @Override
            public void onFailure(Call<Postagem> call, Throwable t) {

            }
        });
    }

    private void salvarPostagem(){

        //Configura objeto postagem
        //Postagem postagem = new Postagem("1234", "Título postagem!", "Corpo postagem");

        //recupera o serviço e salva postagem
        DataService service = retrofit.create(DataService.class);
        Call<Postagem> call = service.salvarPostagem("1234", "Título postagem!", "Corpo postagem");

        call.enqueue(new Callback<Postagem>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<Postagem> call, Response<Postagem> response) {
                if (response.isSuccessful()){
                    Postagem postagemResposta = response.body();
                    txtResultado.setText(
                                    "Código: " + response.code() +
                                    "\nid: " + postagemResposta.getId() +
                                    "\ntítulo: " + postagemResposta.getTitle()
                    );
                }
            }

            @Override
            public void onFailure(Call<Postagem> call, Throwable t) {

            }
        });
    }

    private void recuperarListaRetrofit(){
        DataService service = retrofit.create(DataService.class);
        //Call<List<Foto>> call = service.recuperarFotos();
        Call<List<Postagem>> call = service.recuperarPostagem();

        call.enqueue(new Callback<List<Postagem>>() {
            @Override
            public void onResponse(Call<List<Postagem>> call, Response<List<Postagem>> response) {
                if (response.isSuccessful()) {
                    listaPostagens = response.body();

                    for(int i=0; i <listaPostagens.size(); i++){
                        Postagem postagem = listaPostagens.get( i );
                        Log.d("Resultado", "Resultado: " + postagem.getId() + " / " + postagem.getTitle());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Postagem>> call, Throwable t) {

            }
        });
    }

    private void recuperarCEPRetrofit(){

        CEPService cepService = retrofit.create( CEPService.class );
        Call<CEP> call = cepService.recuperarCEP("01310100");

        call.enqueue(new Callback<CEP>() {
            @Override
            public void onResponse(Call<CEP> call, Response<CEP> response) {
                if(response.isSuccessful()){
                    CEP cep = response.body();
                    txtResultado.setText( cep.getLogradouro() + " / " + cep.getBairro());
                }
            }

            @Override
            public void onFailure(Call<CEP> call, Throwable t) {

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

                // Objeto utilizado para leitura dos caracteres do InputStreamReader
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