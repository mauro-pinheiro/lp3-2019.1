package lp3.college.gui.fx;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
    private Button buttonNovo;
    @FXML
    private Button buttonAbrir;
    @FXML
    private Button buttonSalvar;
    @FXML
    private Button buttonDeletar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    public void acaoNovo() {
        textFieldCodigo.clear();
        textFieldNome.clear();
        textAreaEmenta.clear();
        textFieldCh.clear();
    }

    public void acaoAbrir() {
        String codigo = textFieldCodigo.getText();
        String nome = textFieldNome.getText();
        String ch = textFieldCh.getText();
        String ementa = textAreaEmenta.getText();
        Disciplina c;
        DisciplinaDAO dao = new DisciplinaDAO(Database.getConexao());

        if (!codigo.isEmpty()) {
            c = dao.buscaPorCodigo(codigo);
        } else if (!nome.isEmpty()) {
            c = dao.buscaPorNome(nome);
        } else {
            System.out.println("Tudo vazio");
            return;
        }

        textFieldCodigo.setText(c.getCodigo());
        textFieldNome.setText(c.getNome());
        textAreaEmenta.setText(c.getEmenta());
        textFieldCh.setText(c.getCargaHoraria());
    }

    public void acaoSalvar() {
        DisciplinaDAO dao = new DisciplinaDAO(Database.getConexao());
        String codigo = textFieldCodigo.getText();
        String nome = textFieldNome.getText();
        String ch = textFieldCh.getText();
        String ementa = textAreaEmenta.getText();
        
        Disciplina d = new Disciplina(codigo, nome, ementa, ch);
        int id = dao.existe(d);
        
        if(id <= 0){
            dao.salva(d);
        } else {
            d.setId(id);
            dao.atualiza(d);
        }
    }

    public void acaoDeletar() {
        DisciplinaDAO dao = new DisciplinaDAO(Database.getConexao());
        String codigo = textFieldCodigo.getText();
        String nome = textFieldNome.getText();
        String ch = textFieldCh.getText();
        String ementa = textAreaEmenta.getText();
        
        Disciplina d = new Disciplina(codigo, nome, ementa, ch);
        dao.deleta(d);
        acaoNovo();
    }
}