package lp3.college.gui.fx;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lp3.college.dao.DisciplinaDAO;
import lp3.college.entidades.Disciplina;
import lp3.college.infra.Database;

public class DisciplinaFormController implements Initializable {
    @FXML
    private TextField textFieldCodigo;
    @FXML
    private TextField textFieldNome;
    @FXML
    private TextArea textAreaEmenta;
    @FXML
    private TextField textFieldCh;
    @FXML
    private TextField textFieldAno;
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
        Disciplina c;
        DisciplinaDAO dao = new DisciplinaDAO(Database.getConexao());

        if (!codigo.isBlank()) {
            c = dao.buscaPorCodigo(codigo);
        } else if (!nome.isBlank()) {
            c = dao.buscaPorNome(nome);
        } else {
            System.out.println("Tudo vazio");
            return;
        }

        textFieldCodigo.setText(c.getCodigo());
        textFieldNome.setText(c.getNome());
    }

    public void acaoSalvar() {
        String codigo = textFieldCodigo.getText();
        String nome = textFieldNome.getText();
        String ementa = textAreaEmenta.getText();
        String ano = textFieldAno.getText();
        String ch = textFieldCh.getText();

        DisciplinaDAO dao = new DisciplinaDAO(Database.getConexao());
        Disciplina d = dao.buscaPorCodigo(codigo);
        System.out.println(codigo);
        if(d == null){
            d = new Disciplina(codigo, nome, ano, ementa, ch);
            dao.salva(d);
        } else {
            dao.atualiza(d);
        }
        d = dao.buscaPorCodigo(codigo);
        System.out.println(d);
    }

    public void acaoDeletar() {
        String codigo = textFieldCodigo.getText();
        String nome = textFieldNome.getText();
        String ementa = textAreaEmenta.getText();
        String ano = textFieldAno.getText();
        String ch = textFieldCh.getText();
        
        Disciplina p = new Disciplina(codigo, nome, ano, ementa, ch);
        DisciplinaDAO dao = new DisciplinaDAO(Database.getConexao());
        dao.deleta(p);
        acaoNovo();
    }
}