package lp3.college.gui.fx;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lp3.college.dao.AlunoDAO;
import lp3.college.dao.CursoDAO;
import lp3.college.entidades.Aluno;
import lp3.college.entidades.Curso;
import lp3.college.infra.Database;
import lp3.college.util.CustomCallback;

public class MatAluno implements Initializable {
    @FXML
    private Button buttonNovo;
    @FXML
    private Button buttonAbrir;
    @FXML
    private Button buttonSalvar;
    @FXML
    private Button buttonDeletar;
    @FXML
    private TextField textFieldCodigo;
    @FXML
    private ComboBox<Curso> comboBoxCurso;
    @FXML
    private TableView<Aluno> tableViewMatAluno;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CursoDAO dao = new CursoDAO(Database.getConexao());
        CustomCallback<Curso> cursoCallback = new CustomCallback<>();
        comboBoxCurso.setButtonCell(cursoCallback.call(null));
        comboBoxCurso.setCellFactory(cursoCallback);
        comboBoxCurso.getItems().addAll(dao.getAll());

        tableViewMatAluno.setSelectionModel(null);
        tableViewMatAluno.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableViewMatAluno.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("curso"));
    }

    public void acaoNovo() {
        textFieldCodigo.setText("");
        comboBoxCurso.getSelectionModel().select(0);
        tableViewMatAluno.getItems().clear();
    }

    public void acaoAbrir() {
        Curso curso = comboBoxCurso.getSelectionModel().getSelectedItem();
        AlunoDAO dao = new AlunoDAO(Database.getConexao());
        tableViewMatAluno.setItems(FXCollections.observableArrayList(dao.buscaPorCurso(curso)));
    }

    public void acaoSalvar() {
        String codigo = textFieldCodigo.getText();
        Curso curso = comboBoxCurso.getSelectionModel().getSelectedItem();
        AlunoDAO dao = new AlunoDAO(Database.getConexao());
        Aluno aluno = dao.buscaPorCodigo(codigo);
        dao.atualiza(aluno, curso);
        System.out.println(aluno);
        acaoAbrir();
    }

    public void acaoDeletar() {
        String codigo = textFieldCodigo.getText();
        AlunoDAO dao = new AlunoDAO(Database.getConexao());
        Aluno aluno = dao.buscaPorCodigo(codigo);
        dao.atualiza(aluno, null);
        acaoNovo();
    }
}