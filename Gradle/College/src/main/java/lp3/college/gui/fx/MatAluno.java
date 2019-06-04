package lp3.college.gui.fx;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lp3.college.dao.CursoDAO;
import lp3.college.dao.DAO;
import lp3.college.infra.Database;

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
    private ComboBox<String> comboBoxCurso;
    // @FXML
    // private TableView<String, String> tableViewMatAluno;

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

        CursoDAO dao = new CursoDAO(Database.getConexao());

        comboBoxCurso.getItems().addAll(dao.getAll()
                                        .stream()
                                        .map(curso -> curso.getNome())
                                        .collect(Collectors.toList()));
    }

    // public void acaoNovo() {
    //     textFieldCodigo.clear();
    //     comboBoxCurso.getItems().clear();
    //     tableViewMatAluno.getItems().clear();
    // }

    // public void acaoAbrir() {
    //     String codigo = textFieldCodigo.getText();
    //     String nome = textFieldNome.getText();
    //     Professor p;
    //     ProfessorDAO dao = new ProfessorDAO(Database.getConexao());

    //     if (!codigo.isBlank()) {
    //         p = dao.buscaPorCodigo(codigo);
    //     } else if (!nome.isBlank()) {
    //         p = dao.buscaPorNome(nome);
    //     } else {
    //         System.out.println("Tudo vazio");
    //         return;
    //     }

    //     textFieldCodigo.setText(p.getCodigo());
    //     textFieldNome.setText(p.getNome());
    // }

    // public void acaoSalvar() {
    //     String codigo = textFieldCodigo.getText();
    //     String nome = textFieldNome.getText();
    //     ProfessorDAO dao = new ProfessorDAO(Database.getConexao());
    //     Professor p = dao.buscaPorCodigo(codigo);
    //     System.out.println(codigo);
    //     if(p == null){
    //         p = new Professor(codigo, nome);
    //         dao.salva(p);
    //     } else {
    //         dao.atualiza(p);
    //     }
    //     p = dao.buscaPorCodigo(codigo);
    //     System.out.println(p);
    // }

    // public void acaoDeletar() {
    //     String codigo = textFieldCodigo.getText();
    //     String nome = textFieldNome.getText();
    //     Professor p = new Professor(codigo, nome);
    //     ProfessorDAO dao = new ProfessorDAO(Database.getConexao());
    //     dao.deleta(p);
    //     acaoNovo();
    // }
}