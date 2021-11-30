package com.example.recyclerview.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;
import com.example.recyclerview.holder.MyViewHolder;
import com.example.recyclerview.model.Filme;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<MyViewHolder> {

    private List<Filme> listaFilme;

    public Adapter(List<Filme> lista){
        this.listaFilme = lista;
    };

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_lista, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Filme filme = listaFilme.get(position);
        holder.titulo.setText(filme.getTitulo());
        holder.genero.setText(filme.getGenero());
        holder.ano.setText(filme.getAno());

    }

    @Override
    public int getItemCount() {
        return listaFilme.size();
    }
}
