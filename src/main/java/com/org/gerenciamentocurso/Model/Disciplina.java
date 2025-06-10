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
public class Disciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String descricao;

    // N : 1 Disciplina x Curso
    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    // N : N  Disciplina x Professor
    @ManyToMany
    @JoinTable(
            name = "disciplina_professor",
            joinColumns = @JoinColumn(name = "disciplina_id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id")
    )
    private List<Professor> professores;
}
