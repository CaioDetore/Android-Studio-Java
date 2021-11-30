package com.example.cardview.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardview.R;
import com.example.cardview.holder.MyViewHolder;
import com.example.cardview.model.Postagem;

import java.util.List;

public class PostagemAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private List<Postagem> postagens;

    public PostagemAdapter(List<Postagem> p) {
        this.postagens = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.postagem_detalhe, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Postagem postagem = postagens.get( position );
        holder.nome.setText( postagem.getNome());
        holder.postagem.setText(postagem.getPostagem());
        holder.imagem.setImageResource(postagem.getImagem());
    }

    @Override
    public int getItemCount() {
        return postagens.size();
    }
}
