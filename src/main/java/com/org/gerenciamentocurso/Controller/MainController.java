package com.org.gerenciamentocurso.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainController {
    @FXML
    private AnchorPane conteudoArea;

    @FXML
    public void abrirCursoView() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/com/org/gerenciamentocurso/View/CursoView.fxml"));
        conteudoArea.getChildren().setAll(pane);
    }

    @FXML
    public void abrirProfessorView() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/com/org/gerenciamentocurso/View/ProfessorView.fxml"));
        conteudoArea.getChildren().setAll(pane);
    }

    @FXML
    public void abrirDisciplinaView() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/com/org/gerenciamentocurso/View/DisciplinaView.fxml"));
        conteudoArea.getChildren().setAll(pane);
    }

    @FXML
    public void abrirTurmaView() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/com/org/gerenciamentocurso/View/TurmaView.fxml"));
        conteudoArea.getChildren().setAll(pane);
    }
}