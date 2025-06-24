package com.org.gerenciamentocurso.Controller;

import com.org.gerenciamentocurso.DAO.CursoDAO;
import com.org.gerenciamentocurso.DAO.DisciplinaDAO;
import com.org.gerenciamentocurso.Model.Curso;
import com.org.gerenciamentocurso.Model.Disciplina;
import com.org.gerenciamentocurso.Utils.Alerta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.ReadOnlyStringWrapper;

import java.util.List;

public class DisciplinaController  {
    @FXML
    private TextField nomeField;
    @FXML private TextField descricaoField;
    @FXML private ComboBox<Curso> cursoComboBox;
    @FXML private TableView<Disciplina> disciplinaTable;
    @FXML private TableColumn<Disciplina, String> colNome;
    @FXML private TableColumn<Disciplina, String> colDescricao;
    @FXML private TableColumn<Disciplina, String> colCurso;
    @FXML
    private Button BtnsalvarDisciplina;
    @FXML
    private Button BtneditarDisciplina;
    @FXML
    private Button BtnexcluirDisciplina;
    @FXML
    private Button BtnatualizaListaDisciplina;

    private ObservableList<Disciplina> disciplinaList = FXCollections.observableArrayList();
    private ObservableList<Curso> cursoList = FXCollections.observableArrayList();
    private DisciplinaDAO disciplinaDAO = new DisciplinaDAO(); // Instance of DisciplinaDAO
    private Disciplina disciplinaEditar = null;
    private CursoDAO cursoDAO = new CursoDAO();
    private void carregarDisciplinas() {
        List<Curso> cursos = cursoDAO.findAll();
        cursoComboBox.setItems(FXCollections.observableArrayList(cursos));
        disciplinaList.clear();
        disciplinaList.addAll(disciplinaDAO.findAll()); // Load all disciplines from the database
    }

    @FXML
    public void initialize() {
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colCurso.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getCurso().getNome()));
        cursoComboBox.setItems(cursoList);
        disciplinaTable.setItems(disciplinaList);
        carregarDisciplinas();
    }

    @FXML
    public void onBtnatualizarListaDisciplina() {

       carregarDisciplinas();
    }

    @FXML
    public void onBtnsalvarDisciplina() {

        String nome = nomeField.getText();
        String descricao = descricaoField.getText();
        Curso curso = cursoComboBox.getValue();

        if (nome.isEmpty() || descricao.isEmpty() || curso == null) {
            Alerta.exibirAlerta("Erro", null, "Por favor, não deixe campos em branco.", Alert.AlertType.WARNING);
            return;
        }

        if (disciplinaEditar != null) {
            // Edita a disciplina exixtente
            disciplinaEditar.setNome(nome);
            disciplinaEditar.setDescricao(descricao);
            disciplinaEditar.setCurso(curso);
            disciplinaDAO.editar(disciplinaEditar);
            disciplinaEditar = null;
        } else {
            // Salva nova disciplina
            Disciplina novaDisciplina = new Disciplina();
            novaDisciplina.setNome(nome);
            novaDisciplina.setDescricao(descricao);
            novaDisciplina.setCurso(curso);
            disciplinaDAO.salvar(novaDisciplina);
        }
    }

    @FXML
    public void onBtneditarDisciplina() {
        Disciplina selectedDisciplina = disciplinaTable.getSelectionModel().getSelectedItem();
        if (selectedDisciplina != null) {
            disciplinaEditar = selectedDisciplina; // Mark selected disciplina as being edited
            nomeField.setText(selectedDisciplina.getNome());
            descricaoField.setText(selectedDisciplina.getDescricao());
            cursoComboBox.setValue(selectedDisciplina.getCurso());
        } else {
            Alerta.exibirAlerta("Erro", null, "Selecione a disciplina que deseja editar.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    public void onBtnxcluirDisciplina() {
        Disciplina selecionada = disciplinaTable.getSelectionModel().getSelectedItem();
        if (selecionada != null) {
            disciplinaDAO.excluir(selecionada);
            carregarDisciplinas(); // Refresh the list
        } else {
            Alerta.exibirAlerta("Erro", null, "Selecione uma disciplina para excluir do banco de dados.", Alert.AlertType.WARNING);
        }
    }

}
