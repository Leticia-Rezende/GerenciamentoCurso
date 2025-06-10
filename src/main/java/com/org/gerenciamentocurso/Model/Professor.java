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
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String email;
    private String formacao;

    // N : N Professor x Disciplina
    @ManyToMany(mappedBy = "professores")
    private List<Disciplina> disciplinas;
}
