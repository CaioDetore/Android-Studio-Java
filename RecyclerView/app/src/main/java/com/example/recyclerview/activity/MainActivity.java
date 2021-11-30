package com.example.recyclerview.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.recyclerview.R;
import com.example.recyclerview.RecyclerItemClickListener;
import com.example.recyclerview.adapter.Adapter;
import com.example.recyclerview.model.Filme;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Filme> listaFilme = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
//        Listagem de filme
        this.criarFilmes();

//        Configurar adapter
        Adapter adapter = new Adapter( listaFilme );

//        Configurar clique
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Filme filme = listaFilme.get(position);
                                Toast.makeText(
                                        getApplicationContext(),
                                        "Item pressionado: " + filme.getTitulo(),
                                        Toast.LENGTH_SHORT
                                ).show();
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                Filme filme = listaFilme.get(position);
                                Toast.makeText(
                                        getApplicationContext(),
                                        "Click Longo: " + filme.getTitulo(),
                                        Toast.LENGTH_SHORT
                                ).show();
                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );

//        Configurar recyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration( new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    public void criarFilmes(){
        Filme filme = new Filme("Homem Aranha - De volta ao lar", "Aventura", "2017");
        listaFilme.add(filme);

        filme = new Filme("Mulher Maravilha", "Fantasia", "2017");
        listaFilme.add(filme);

        filme = new Filme("Liga da Justiça", "Ficção", "2017");
        listaFilme.add(filme);

        filme = new Filme("Capitão América - Guerra Civíl", "Aventura/Ficção", "2016");
        listaFilme.add(filme);

        filme = new Filme("It: A Coisa", "Drama/Terror", "2017");
        listaFilme.add(filme);

        filme = new Filme("Pica-pau: O Filme", "Comédia/Animação", "2017");
        listaFilme.add(filme);

        filme = new Filme("A Múmia", "Terro", "2017");
        listaFilme.add(filme);

        filme = new Filme("A Bela e a Fera", "Romance", "2017");
        listaFilme.add(filme);

        filme = new Filme("Meu malvado favorito 3", "Comédia", "2017");
        listaFilme.add(filme);

        filme = new Filme("Carros 3", "Comédia", "2017");
        listaFilme.add(filme);



    }
}