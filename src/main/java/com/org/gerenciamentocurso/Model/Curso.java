package com.org.gerenciamentocurso.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private int cargaHoraria;

    //1 : N Curso x Disciplina
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    private List<Disciplina> disciplinas;
}
