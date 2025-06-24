package com.org.gerenciamentocurso.Controller;

import com.org.gerenciamentocurso.Utils.PathFXML;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;
import java.io.FileInputStream;
import java.io.IOException;


public class MainController {
    @FXML
    private VBox vBoxMain;
    @FXML
    MenuBar menuBarMain;


    @FXML
    public void abrirCursoView() {
        loadView("\\CursoView.fxml");
    }

    @FXML
    public void abrirProfessorView() {

        loadView("\\ProfessorView.fxml");
    }

    @FXML
    public void abrirDisciplinaView() {

        loadView("\\DisciplinaView.fxml");
    }

    @FXML
    public void abrirTurmaView() {

        loadView("\\TurmaView.fxml");
    }

    private void loadView(String fxmlPath) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            VBox vBox = fxmlLoader.load(new FileInputStream(PathFXML.pathBase() + fxmlPath));

            vBoxMain.getChildren().clear();
            vBoxMain.getChildren().add(menuBarMain);
            vBoxMain.getChildren().addAll(vBox.getChildren());


        } catch (IOException e) {
            System.out.println("Erro ao carregar a view: " + fxmlPath);
            e.printStackTrace();
        }
    }
}