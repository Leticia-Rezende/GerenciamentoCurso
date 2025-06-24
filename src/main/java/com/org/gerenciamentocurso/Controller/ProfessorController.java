package com.org.gerenciamentocurso.Controller;

import com.org.gerenciamentocurso.DAO.ProfessorDAO;
import com.org.gerenciamentocurso.Model.Professor;
import com.org.gerenciamentocurso.Utils.Alerta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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

    private ObservableList<Professor> professorList = FXCollections.observableArrayList();
    private ProfessorDAO professorDAO = new ProfessorDAO(); // Instance of ProfessorDAO
    private Professor professorEditar = null;

    private void carregarProfessores() {
        professorList.clear();
        professorList.addAll(professorDAO.findAll()); // Load all professors from the database
    }

    @FXML
    public void initialize() {
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colFormacao.setCellValueFactory(new PropertyValueFactory<>("formacao"));
        professorTable.setItems(professorList);
        onBtnatualizarListaProfessor();
        carregarProfessores();
    }

    @FXML
    public void onBtnatualizarListaProfessor() {

        ///professorTable.getItems().setAll(professorDAO.buscarPorId(1L));
    }

    @FXML
    public void onBtnsalvarProfessor() {
        String nome = nomeField.getText();
        String email = emailField.getText();
        String formacao = formacaoField.getText();

        if (nome.isEmpty() || email.isEmpty() || formacao.isEmpty()) {
            Alerta.exibirAlerta("Erro", null, "Por favor, preencha todos os campos.", Alert.AlertType.WARNING);
            return;
        }
        if (professorEditar != null) {
            // Edita um novo professor
            professorEditar.setNome(nome);
            professorEditar.setEmail(email);
            professorEditar.setFormacao(formacao);
            professorDAO.editar(professorEditar);
            professorEditar = null;
        } else {
            // Salva um novo professor
            Professor novoProfessor = new Professor();
            novoProfessor.setNome(nome);
            novoProfessor.setEmail(email);
            novoProfessor.setFormacao(formacao);
            professorDAO.salvar(novoProfessor);
        }
        carregarProfessores();
        limparCampos();
    }

    @FXML
    public void onBtneditarProfessor() {
        Professor selectedProfessor = professorTable.getSelectionModel().getSelectedItem();
        if (selectedProfessor != null) {
            professorEditar = selectedProfessor;
            nomeField.setText(selectedProfessor.getNome());
            emailField.setText(selectedProfessor.getEmail());
            formacaoField.setText(selectedProfessor.getFormacao());
        } else {
            Alerta.exibirAlerta("Erro", null, "Selecione um professor para fazer a edição!.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    public void onBtnexcluirProfessor() {
        Professor selecionada = professorTable.getSelectionModel().getSelectedItem();
        if (selecionada != null) {
            professorDAO.excluir(selecionada); // Delete the selected professor from the database
            carregarProfessores(); // Refresh the list
        } else {
            Alerta.exibirAlerta("Erro", null, "Selecione um professor para remover.", Alert.AlertType.WARNING);
        }
    }

    private void limparCampos() {
        nomeField.clear();
        emailField.clear();
        formacaoField.clear();
    }
}
