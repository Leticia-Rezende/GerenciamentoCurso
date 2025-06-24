package com.org.gerenciamentocurso.Controller;

import com.org.gerenciamentocurso.DAO.DisciplinaDAO;
import com.org.gerenciamentocurso.DAO.ProfessorDAO;
import com.org.gerenciamentocurso.DAO.TurmaDAO;
import com.org.gerenciamentocurso.Model.Disciplina;
import com.org.gerenciamentocurso.Model.Professor;
import com.org.gerenciamentocurso.Model.Turma;
import com.org.gerenciamentocurso.Utils.Alerta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class TurmaController {
    @FXML
    private TextField semestreField;
    @FXML
    private TextField horarioField;
    @FXML
    private ComboBox<Disciplina> disciplinaComboBox;
    @FXML
    private ComboBox<Professor> professorComboBox;
    @FXML
    private TableView<Turma> turmaTable;
    @FXML
    private TableColumn<Turma, String> colSemestre;
    @FXML
    private TableColumn<Turma, String> colDisciplina;
    @FXML
    private TableColumn<Turma, String> colProfessor;
    @FXML
    private TableColumn<Turma, String> colHorario;

    @FXML
    private Button BtnsalvarTurma;
    @FXML
    private Button BtneditarTurma;
    @FXML
    private Button BtnexcluirTurma;
    @FXML
    private Button BtnatualizaListaTurma;

    private ObservableList<Turma> turmaList = FXCollections.observableArrayList();
    private ObservableList<Disciplina> disciplinaList = FXCollections.observableArrayList();
    private ObservableList<Professor> professorList = FXCollections.observableArrayList();
    private TurmaDAO turmaDAO = new TurmaDAO(); // Instance of TurmaDAO
    private DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
    private ProfessorDAO professorDAO = new ProfessorDAO();
    private Turma turmaEditar = null;

    @FXML
    public void initialize() {
        colSemestre.setCellValueFactory(new PropertyValueFactory<>("semestre"));
        colDisciplina.setCellValueFactory(new PropertyValueFactory<>("disciplina"));
        colProfessor.setCellValueFactory(new PropertyValueFactory<>("professor"));
        colHorario.setCellValueFactory(new PropertyValueFactory<>("horario"));

        disciplinaComboBox.setItems(disciplinaList);
        professorComboBox.setItems(professorList);
        turmaTable.setItems(turmaList);
        carregarTurmas();

    }

    @FXML
    public void onBtnatualizarListaTurma() {
        carregarTurmas();
    }

    @FXML
    public void onBtnsalvarTurma() {
        String semestre = semestreField.getText();
        Disciplina disciplina = disciplinaComboBox.getValue();
        Professor professor = professorComboBox.getValue();
        String horario = horarioField.getText();

        if (semestre.isEmpty() || disciplina == null || professor == null || horario.isEmpty()) {
            Alerta.exibirAlerta("Erro", null, "Por favor, preencha os campos em branco!.", Alert.AlertType.WARNING);
            return;
        }

        if (turmaEditar != null) {
            // Edita uma turma exixtente
            turmaEditar.setSemestre(semestre);
            turmaEditar.setDisciplina(disciplina);
            turmaEditar.setProfessor(professor);
            turmaEditar.setHorario(horario);
            turmaDAO.editar(turmaEditar);
            turmaEditar = null; // Clear edit mode
        } else {
            // Salva uma nova turma
            Turma novaTurma = new Turma();
            novaTurma.setSemestre(semestre);
            novaTurma.setDisciplina(disciplina);
            novaTurma.setProfessor(professor);
            novaTurma.setHorario(horario);
            turmaDAO.salvar(novaTurma);
        }

        carregarTurmas();
    }

    @FXML
    public void onBtneditarTurma() {

        Turma selectedTurma = turmaTable.getSelectionModel().getSelectedItem();
        if (selectedTurma != null) {
            turmaEditar = selectedTurma;
            semestreField.setText(String.valueOf(selectedTurma.getSemestre()));
            disciplinaComboBox.setValue(selectedTurma.getDisciplina());
            professorComboBox.setValue(selectedTurma.getProfessor());
            horarioField.setText(selectedTurma.getHorario());
        } else {
            Alerta.exibirAlerta("Erro", null, "Selecione uma turma para fazer a edição.", Alert.AlertType.WARNING);
        }
    }
    private void carregarTurmas() {
        List<Disciplina> disciplinas = disciplinaDAO.findAll();
        disciplinaComboBox.setItems(FXCollections.observableArrayList(disciplinas));

        List<Professor> professores = professorDAO.findAll();
        professorComboBox.setItems(FXCollections.observableArrayList(professores));

        turmaList.clear();
        turmaList.addAll(turmaDAO.findAll()); // Load all turmas from the database
    }

    @FXML
    public void onBtnexcluirTurma() {
        Turma turmaselecionada = turmaTable.getSelectionModel().getSelectedItem();
        if (turmaselecionada != null) {
            turmaDAO.excluir(turmaselecionada.getId());
            carregarTurmas();
        } else {
            Alerta.exibirAlerta("Erro", null, "Selecione uma turma para excluir do banco de dados.", Alert.AlertType.WARNING);
        }
    }

}
