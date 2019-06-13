package lp3.college.gui.fx;

import java.io.NotActiveException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lp3.college.dao.CursoDAO;
import lp3.college.dao.DisciplinaDAO;
import lp3.college.dao.GradeDAO;
import lp3.college.entidades.Curso;
import lp3.college.entidades.Disciplina;
import lp3.college.entidades.Grade;
import lp3.college.infra.Database;
import lp3.college.util.CustomCallback;

public class GradeFormController implements Initializable {
    @FXML
    private Button buttonNovo;
    @FXML
    private Button buttonAbrir;
    @FXML
    private Button buttonSalvar;
    @FXML
    private Button buttonDeletar;
    @FXML
    private TextField textFieldAno;
    @FXML
    private ComboBox<Curso> comboBoxCurso;
    @FXML
    private ComboBox<Disciplina> comboBoxDisciplina;
    @FXML
    private TableView<Disciplina> tableView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CursoDAO cursoDao = new CursoDAO(Database.getConexao());
        CustomCallback<Curso> cursoCallback = new CustomCallback<>();
        comboBoxCurso.setButtonCell(cursoCallback.call(null));
        comboBoxCurso.setCellFactory(cursoCallback);
        comboBoxCurso.getItems().add(null);
        comboBoxCurso.getItems().addAll(cursoDao.getAll());
                                        

        DisciplinaDAO discDao = new DisciplinaDAO(Database.getConexao());
        CustomCallback<Disciplina> discCallback = new CustomCallback<Disciplina>();
        comboBoxDisciplina.setButtonCell(discCallback.call(null));
        comboBoxDisciplina.setCellFactory(discCallback);
        comboBoxDisciplina.getItems().add(null);
        comboBoxDisciplina.getItems().addAll(discDao.getAll());

        tableView.setSelectionModel(null);
        tableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("cargaHoraria"));
    }

    public void acaoNovo() {
        textFieldAno.clear();
        comboBoxCurso.getSelectionModel().select(0);
    }

    public void acaoAbrir() {
        Short ano = Short.parseShort(textFieldAno.getText());
        Curso curso = comboBoxCurso.getSelectionModel().getSelectedItem();
        GradeDAO dao = new GradeDAO(Database.getConexao());

        List<Disciplina> disciplinas = dao.buscaPorId(ano, curso)
                                     .stream().map(grade -> grade.getDisciplina())
                                     .collect(Collectors.toList());
        tableView.setItems(FXCollections.observableArrayList(disciplinas));
    }

    public void acaoSalvar() {
        Short ano = Short.parseShort(textFieldAno.getText());
        Curso curso = comboBoxCurso.getSelectionModel().getSelectedItem();
        Disciplina disciplina = comboBoxDisciplina.getSelectionModel().getSelectedItem();
        Grade grade = new Grade(ano, curso, disciplina);
        GradeDAO dao = new GradeDAO(Database.getConexao());
        dao.salva(grade);
        acaoAbrir();
    }

    public void acaoDeletar() {
        Short ano = Short.parseShort(textFieldAno.getText());
        Curso curso = comboBoxCurso.getSelectionModel().getSelectedItem();
        Disciplina disc = tableView.getSelectionModel().getSelectedItem();
        GradeDAO dao = new GradeDAO(Database.getConexao());
        dao.buscaPorId(ano, curso)
           .stream()
           .filter(grade -> grade.getDisciplina().equals(disc))
           .forEach(grade -> dao.deleta(grade));
        acaoNovo();
    }
}