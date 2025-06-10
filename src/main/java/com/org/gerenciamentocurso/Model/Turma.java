package com.org.gerenciamentocurso.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.smartcardio.ATR;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String semestre;
    //private String disciplina;
    //private String professor;
    private String horario;

    // N : 1 Turma x Disciplina
    @ManyToOne
    @JoinColumn(name = "disciplina_id")
    private Disciplina disciplina;

    // N : 1 Turma x Professor
    @ManyToOne
    @JoinColumn(name = "professora_id")
    private Professor professor;
}
