package com.org.gerenciamentocurso.Controller;

import com.org.gerenciamentocurso.DAO.DisciplinaDAO;
import com.org.gerenciamentocurso.Model.Curso;
import com.org.gerenciamentocurso.Model.Disciplina;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.org.gerenciamentocurso.DAO.CursoDAO;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.ReadOnlyStringWrapper;

public class DisciplinaController {
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
    private final DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
    private final CursoDAO cursoDAO = new CursoDAO();

    @FXML
    public void initialize() {
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colCurso.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getCurso().getNome()));

        cursoComboBox.getItems().setAll((Curso) cursoDAO.findAll());

        onBtnatualizarListaDisciplina();
    }

    @FXML
    public void onBtnatualizarListaDisciplina() {

        disciplinaTable.getItems().setAll(disciplinaDAO.buscarPorId(1L));
    }

    @FXML
    public void onBtnsalvarDisciplina() {
        Disciplina d = new Disciplina();
        d.setNome(nomeField.getText());
        d.setDescricao(descricaoField.getText());
        d.setCurso(cursoComboBox.getValue());
        disciplinaDAO.salvar(d);
        onBtnatualizarListaDisciplina();
    }

    @FXML
    public void onBtneditarDisciplina() {
        Disciplina selecionada = disciplinaTable.getSelectionModel().getSelectedItem();
        if (selecionada != null) {
            selecionada.setNome(nomeField.getText());
            selecionada.setDescricao(descricaoField.getText());
            selecionada.setCurso(cursoComboBox.getValue());
            disciplinaDAO.editar(selecionada);
            onBtnatualizarListaDisciplina();
        }
    }

    @FXML
    public void onBtnxcluirDisciplina() {
        Disciplina selecionada = disciplinaTable.getSelectionModel().getSelectedItem();
        if (selecionada != null) {
            disciplinaDAO.excluir(selecionada.getId());
            onBtnatualizarListaDisciplina();
        }
    }
}
