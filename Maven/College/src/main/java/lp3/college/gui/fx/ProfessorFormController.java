package lp3.college.gui.fx;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lp3.college.dao.ProfessorDAO;
import lp3.college.entidades.Professor;
import lp3.college.infra.Database;

public class ProfessorFormController implements Initializable {
    @FXML
    private TextField textFieldCodigo;
    @FXML
    private TextField textFieldNome;
    @FXML
    private Button buttonNovo;
    @FXML
    private Button buttonAbrir;
    @FXML
    private Button buttonSalvar;
    @FXML
    private Button buttonDeletar;

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
    }

    public void acaoNovo() {
        textFieldCodigo.clear();
        textFieldNome.clear();
    }

    public void acaoAbrir() {
        String codigo = textFieldCodigo.getText();
        String nome = textFieldNome.getText();
        Professor p;
        ProfessorDAO dao = new ProfessorDAO(Database.getConexao());

        if (!codigo.isEmpty()) {
            p = dao.buscaPorCodigo(codigo);
        } else if (!nome.isEmpty()) {
            p = dao.buscaPorNome(nome);
        } else {
            System.out.println("Tudo vazio");
            return;
        }

        textFieldCodigo.setText(p.getCodigo());
        textFieldNome.setText(p.getNome());
    }

    public void acaoSalvar() {
        ProfessorDAO dao = new ProfessorDAO(Database.getConexao());
        String codigo = textFieldCodigo.getText();
        String nome = textFieldNome.getText();
        Professor p = new Professor(codigo, nome);
        int id = dao.existe(p);
        
        if(id <= 0){
            dao.salva(p);
        } else {
            p.setId(id);
            dao.atualiza(p);
        }
    }

    public void acaoDeletar() {
        ProfessorDAO dao = new ProfessorDAO(Database.getConexao());
        String codigo = textFieldCodigo.getText();
        String nome = textFieldNome.getText();
        
        Professor p = new Professor(codigo, nome);
        dao.deleta(p);
        acaoNovo();
    }
}