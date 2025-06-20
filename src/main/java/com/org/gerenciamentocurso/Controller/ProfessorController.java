package com.org.gerenciamentocurso.Controller;

import com.org.gerenciamentocurso.DAO.ProfessorDAO;
import com.org.gerenciamentocurso.Model.Professor;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProfessorController {
    @FXML
    private TextField nomeField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField formacaoField;
    @FXML
    private TableView<Professor> professorTable;
    @FXML
    private TableColumn<Professor, String> colNome;
    @FXML
    private TableColumn<Professor, String> colEmail;
    @FXML
    private TableColumn<Professor, String> colFormacao;

    @FXML
    private Button BtnsalvarProfessor;
    @FXML
    private Button BtneditarProfessor;
    @FXML
    private Button BtnexcluirProfessor;
    @FXML
    private Button BtnatualizaListaProfessor;

    //private final ProfessorDAO professorDAO = new ProfessorDAO();

    @FXML
    public void initialize() {
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colFormacao.setCellValueFactory(new PropertyValueFactory<>("formacao"));

        onBtnatualizarListaProfessor();
    }

    @FXML
    public void onBtnatualizarListaProfessor() {

        ///professorTable.getItems().setAll(professorDAO.buscarPorId(1L));
    }

    @FXML
    public void onBtnsalvarProfessor() {
        Professor p = new Professor();
        p.setNome(nomeField.getText());
        p.setEmail(emailField.getText());
        p.setFormacao(formacaoField.getText());
        //professorDAO.salvar(p);
        onBtnatualizarListaProfessor();
    }

    @FXML
    public void onBtneditarProfessor() {
        Professor selecionado = professorTable.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            selecionado.setNome(nomeField.getText());
            selecionado.setEmail(emailField.getText());
            selecionado.setFormacao(formacaoField.getText());
            //professorDAO.editar(selecionado);
            onBtnatualizarListaProfessor();
        }
    }

    @FXML
    public void onBtnexcluirProfessor() {
        Professor selecionado = professorTable.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            //professorDAO.excluir(selecionado.getId());
            onBtnatualizarListaProfessor();
        }
    }

}
