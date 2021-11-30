package com.example.recyclerview.holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;

public class MyViewHolder extends RecyclerView.ViewHolder{
    public  TextView titulo;
    public  TextView ano;
    public  TextView genero;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        titulo = itemView.findViewById(R.id.txtTitulo);
        ano = itemView.findViewById(R.id.txtAno);
        genero = itemView.findViewById(R.id.txtGenero);

    }
}
