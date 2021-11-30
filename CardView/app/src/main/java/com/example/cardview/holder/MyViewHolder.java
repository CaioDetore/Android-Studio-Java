package com.example.cardview.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardview.R;

public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView nome;
    public TextView postagem;
    public ImageView imagem;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        nome = itemView.findViewById(R.id.txtNome);
        postagem = itemView.findViewById(R.id.txtPost);
        imagem = itemView.findViewById(R.id.imagePost);
    }
}
