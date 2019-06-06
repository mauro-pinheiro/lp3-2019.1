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
import lp3.college.dao.DisciplinaDAO;
import lp3.college.dao.MinistraDAO;
import lp3.college.dao.ProfessorDAO;
import lp3.college.entidades.Disciplina;
import lp3.college.entidades.Ministra;
import lp3.college.entidades.Professor;
import lp3.college.infra.Database;
import lp3.college.util.CustomCallback;

public class ProfDisc implements Initializable {
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
    private ComboBox<Disciplina> comboBoxDisciplina;
    @FXML
    private TableView<Ministra> tableView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image novoIcon = new Image(getClass().getResourceAsStream("images/new doc.png"));
        Image openIcon = new Image(getClass().getResourceAsStream("images/open doc.png"));
        Image SaveIcon = new Image(getClass().getResourceAsStream("images/save doc.png"));
        Image DelIcon = new Image(getClass().getResourceAsStream("images/delete doc.png"));

        buttonNovo.setGraphic(new ImageView(novoIcon));
        buttonAbrir.setGraphic(new ImageView(openIcon));
        buttonSalvar.setGraphic(new ImageView(SaveIcon));
        buttonDeletar.setGraphic(new ImageView(DelIcon));

        DisciplinaDAO discDao = new DisciplinaDAO(Database.getConexao());
        CustomCallback<Disciplina> discCallback = new CustomCallback<Disciplina>();
        comboBoxDisciplina.setButtonCell(discCallback.call(null));
        comboBoxDisciplina.setCellFactory(discCallback);
        comboBoxDisciplina.getItems().add(null);
        comboBoxDisciplina.getItems().addAll(discDao.getAll());

        tableView.setSelectionModel(null);
        tableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("professor"));
        tableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("disciplina"));
    }

    public void acaoNovo() {
        textFieldCodigo.clear();
        comboBoxDisciplina.getSelectionModel().clearSelection();
        tableView.getItems().clear();
    }

    public void acaoAbrir() {
        String codigo = textFieldCodigo.getText();
        Professor prof = new ProfessorDAO(Database.getConexao()).buscaPorCodigo(codigo);
        Disciplina disc = comboBoxDisciplina.getSelectionModel().getSelectedItem();
        MinistraDAO dao = new MinistraDAO(Database.getConexao());
        if(codigo.isEmpty()){
            tableView.setItems(FXCollections.observableArrayList(dao.buscaPorDisciplina(disc)));
        } else if(disc == null) {
            tableView.setItems(FXCollections.observableArrayList(dao.buscaPorProfessor(prof)));
        } else {
            tableView.setItems(FXCollections.observableArrayList(dao.getAll()));
        }
    }

    public void acaoSalvar() {
        String codigo = textFieldCodigo.getText();
        Professor professor = new ProfessorDAO(Database.getConexao()).buscaPorCodigo(codigo);
        Disciplina disc = comboBoxDisciplina.getSelectionModel().getSelectedItem();
        Ministra ministra = new Ministra(professor, disc);
        MinistraDAO dao = new MinistraDAO(Database.getConexao());
        dao.salva(ministra);
        acaoAbrir();
    }

    public void acaoDeletar() {
        String codigo = textFieldCodigo.getText();
        Professor professor = new ProfessorDAO(Database.getConexao()).buscaPorCodigo(codigo);
        Disciplina disc = comboBoxDisciplina.getSelectionModel().getSelectedItem();
        Ministra ministra = new Ministra(professor, disc);
        MinistraDAO dao = new MinistraDAO(Database.getConexao());
        dao.deleta(ministra);
        acaoNovo();
    }
}