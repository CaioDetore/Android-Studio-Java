package com.example.listadetarefas.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Tarefa implements Serializable {
     private Long id;
     private String nomeTarefa;
}
