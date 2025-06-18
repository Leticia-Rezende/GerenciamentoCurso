package com.org.gerenciamentocurso.Controller;

import com.org.gerenciamentocurso.DAO.CursoDAO;
import com.org.gerenciamentocurso.Model.Curso;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

    //private final CursoDAO cursoDAO = new CursoDAO();

    @FXML
    public void initialize() {
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colCargaHoraria.setCellValueFactory(new PropertyValueFactory<>("cargaHoraria"));
        onBtnatualizarListaCurso();
    }

    @FXML
    public void onBtnatualizarListaCurso() {

        //cursoTable.getItems().setAll(cursoDAO.buscarPorId(1L)); //Busca apenas pelo id 1
    }

    @FXML
    public void onBtnsalvarCurso() {
        Curso c = new Curso();
        c.setNome(nomeField.getText());
        c.setCargaHoraria(Integer.parseInt(cargaHorariaField.getText()));
        //cursoDAO.salvar(c);
        onBtnatualizarListaCurso();
    }

    @FXML
    public void onBtneditarCurso() {
        Curso selecionado = cursoTable.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            selecionado.setNome(nomeField.getText());
            selecionado.setCargaHoraria(Integer.parseInt(cargaHorariaField.getText()));
            //cursoDAO.editar(selecionado);
            onBtnatualizarListaCurso();
        }
    }

    @FXML
    public void onBtnexcluirCurso() {
        Curso selecionado = cursoTable.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            //cursoDAO.excluir(selecionado.getId());
            onBtnatualizarListaCurso();
        }
    }
}
