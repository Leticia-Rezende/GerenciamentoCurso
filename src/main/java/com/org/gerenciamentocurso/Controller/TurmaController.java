package com.org.gerenciamentocurso.Controller;

import com.org.gerenciamentocurso.DAO.DisciplinaDAO;
import com.org.gerenciamentocurso.DAO.ProfessorDAO;
import com.org.gerenciamentocurso.DAO.TurmaDAO;
import com.org.gerenciamentocurso.Model.Disciplina;
import com.org.gerenciamentocurso.Model.Professor;
import com.org.gerenciamentocurso.Model.Turma;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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

    private final TurmaDAO turmaDAO = new TurmaDAO();
    private final DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
    private final ProfessorDAO professorDAO = new ProfessorDAO();

    @FXML
    public void initialize() {
        colSemestre.setCellValueFactory(new PropertyValueFactory<>("semestre"));
        colDisciplina.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getDisciplina().getNome()));
        colProfessor.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getProfessor().getNome()));

        disciplinaComboBox.getItems().setAll(disciplinaDAO.findAll());
        professorComboBox.getItems().setAll(professorDAO.findAll());

        atualizarLista();
    }

    @FXML
    public void atualizarLista() {
        turmaTable.getItems().setAll(turmaDAO.buscarPorId(1L));
    }

    @FXML
    public void salvarTurma() {
        Turma t = new Turma();
        t.setSemestre(semestreField.getText());
        t.setHorario(horarioField.getText());
        t.setDisciplina(disciplinaComboBox.getValue());
        t.setProfessor(professorComboBox.getValue());
        turmaDAO.salvar(t);
        atualizarLista();
    }

    @FXML
    public void editarTurma() {
        Turma selecionada = turmaTable.getSelectionModel().getSelectedItem();
        if (selecionada != null) {
            selecionada.setSemestre(semestreField.getText());
            selecionada.setHorario(horarioField.getText());
            selecionada.setDisciplina(disciplinaComboBox.getValue());
            selecionada.setProfessor(professorComboBox.getValue());
            turmaDAO.editar(selecionada);
            atualizarLista();
        }
    }

    @FXML
    public void excluirTurma() {
        Turma selecionada = turmaTable.getSelectionModel().getSelectedItem();
        if (selecionada != null) {
            turmaDAO.excluir(selecionada.getId());
            atualizarLista();
        }
    }
}
