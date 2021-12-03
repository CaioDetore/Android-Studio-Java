package com.example.listadetarefas.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listadetarefas.R;
import com.example.listadetarefas.holder.MyViewHolder;
import com.example.listadetarefas.model.Tarefa;

import java.time.LocalDateTime;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<MyViewHolder> {

    private List<Tarefa> tarefaList;

    public Adapter(List<Tarefa> lista){
        this.tarefaList = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_tarefa, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Tarefa tarefa = tarefaList.get( position );
        holder.edtTarefa.setText(tarefa.getNomeTarefa());
    }

    @Override
    public int getItemCount() {

        return tarefaList.size();
    }
}


