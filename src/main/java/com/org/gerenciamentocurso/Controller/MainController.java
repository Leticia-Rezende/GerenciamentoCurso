package com.org.gerenciamentocurso.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MainController {
    @FXML
    private AnchorPane conteudoArea;


    @FXML
    public void abrirCursoView() {
        loadView("CursoView.fxml");
    }

    @FXML
    public void abrirProfessorView() {
        loadView("ProfessorView.fxml");
    }

    @FXML
    public void abrirDisciplinaView() {
        loadView("DisciplinaView.fxml");
    }

    @FXML
    public void abrirTurmaView() {
        loadView("TurmaView.fxml");
    }
    private void loadView(String fxmlFile) {
        try {
            URL fxmlLocation = getClass().getResource("src/main/java/com/org/gerenciamentocurso/View" + fxmlFile);
            System.out.println("Tentando carregar: " + fxmlLocation);

            if (fxmlLocation == null) {
                throw new IllegalStateException("Arquivo FXML não encontrado: " + fxmlFile);
            }

            AnchorPane pane = FXMLLoader.load(fxmlLocation);
            conteudoArea.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}