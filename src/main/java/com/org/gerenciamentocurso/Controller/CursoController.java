package com.org.gerenciamentocurso.Controller;

import com.org.gerenciamentocurso.DAO.CursoDAO;
import com.org.gerenciamentocurso.Model.Curso;
import com.org.gerenciamentocurso.Utils.Alerta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class CursoController {
    @FXML
    private TextField nomeField;
    @FXML
    private TextField cargaHorariaField;
    @FXML
    private TableView<Curso> cursoTable;
    @FXML
    private TableColumn<Curso, String> colNome;
    @FXML
    private TableColumn<Curso, Integer> colCargaHoraria;

    @FXML
    private Button BtnsalvarCurso;
    @FXML
    private Button BtneditarCurso;
    @FXML
    private Button BtnexcluirCurso;
    @FXML
    private Button BtnatualizaListaCurso;

    private CursoDAO cursoDAO = new CursoDAO();
    private ObservableList<Curso> cursoList = FXCollections.observableArrayList();
    private Curso cursoEditar = null;


    @FXML
    public void initialize() {
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colCargaHoraria.setCellValueFactory(new PropertyValueFactory<>("cargaHoraria"));
        onBtnatualizarListaCurso();
    }

    @FXML
    public void onBtnatualizarListaCurso() {
        cursoList.clear();
        cursoList.addAll(cursoDAO.findAll());
        //cursoTable.getItems().setAll(cursoDAO.buscarPorId(1L)); //Busca apenas pelo id 1
    }

    @FXML
    public void onBtnsalvarCurso() {
        String nome = nomeField.getText();
        String cargaHorariaText = cargaHorariaField.getText();

        if (nome.isEmpty() || cargaHorariaText.isEmpty()) {
            Alerta.exibirAlerta("Erro", null, "Por favor, preencha todos os campos.", Alert.AlertType.WARNING);
            return;
        }

        int cargaHoraria;
        try {
            cargaHoraria = Integer.parseInt(cargaHorariaText);
        } catch (NumberFormatException e) {
            Alerta.exibirAlerta("Erro", null, "Carga horária deve ser um número.", Alert.AlertType.WARNING);
            return;
        }

        if (cursoEditar != null) {
            // Atualiza um novo curso
            cursoEditar.setNome(nome);
            cursoEditar.setCargaHoraria(cargaHoraria);
            cursoDAO.editar(cursoEditar);
            cursoEditar = null;
        } else {
            // Salva um novo curso
            Curso novoCurso = new Curso();
            novoCurso.setNome(nome);
            novoCurso.setCargaHoraria(cargaHoraria);
            cursoDAO.salvar(novoCurso);
        }
    }

    @FXML
    public void onBtneditarCurso() {
        Curso selecionado = cursoTable.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            cursoEditar = selecionado;
            selecionado.setNome(nomeField.getText());
            selecionado.setCargaHoraria(Integer.parseInt(cargaHorariaField.getText()));
            cursoDAO.editar(selecionado);
            onBtnatualizarListaCurso();
        } else {
            Alerta.exibirAlerta("Erro", null, "Selecione um curso para fazer a edição!", Alert.AlertType.WARNING);
        }
    }

    @FXML
    public void onBtnexcluirCurso() {
        Curso selecionado = cursoTable.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            cursoDAO.excluir(selecionado);
            onBtnatualizarListaCurso();
        } else {
            Alerta.exibirAlerta("Erro", null, "Selecione um curso para remover do banco de dados!.", Alert.AlertType.WARNING);
        }
    }
}
