package com.example.listadetarefas.holder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listadetarefas.R;
import com.google.android.material.textfield.TextInputEditText;

public class MyViewHolder extends RecyclerView.ViewHolder {

    public TextInputEditText edtTarefa;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        edtTarefa = itemView.findViewById(R.id.edtAdicionarTarefa);

    }
}
