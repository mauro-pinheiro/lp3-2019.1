package lp3.college.gui.fx;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import lp3.college.dao.ProfessorDAO;
import lp3.college.entidades.Professor;
import lp3.college.infra.Database;

public class ProfessorFormController implements Initializable {
    @FXML
    private TextField textFieldCodigo;
    @FXML
    private TextField textFieldNome;
    @FXML
    private BorderPane borderPane;

    private BarraFerramentas toolBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        toolBar = new BarraFerramentas();
        toolBar.setNewAction(e -> acaoNovo());
        toolBar.setOpenAction(e -> acaoAbrir());
        toolBar.setSaveAction(e -> acaoSalvar());
        toolBar.setDeleteAction(e -> acaoDeletar());
        borderPane.setTop(toolBar);
    }

    public void acaoNovo() {
        String codigo = textFieldCodigo.getText();
        String nome = textFieldNome.getText();

        Professor p = new Professor(codigo, nome);
        ProfessorDAO dao = new ProfessorDAO(Database.getConexao());
        dao.salva(p);
    }

    public void acaoAbrir() {
        String codigo = textFieldCodigo.getText();
        String nome = textFieldNome.getText();
        Professor p;
        ProfessorDAO dao = new ProfessorDAO(Database.getConexao());

        if (!codigo.isBlank()) {
            p = dao.buscaPorCodigo(codigo);
        } else if (!nome.isBlank()) {
            p = dao.buscaPorNome(nome);
        } else {
            System.out.println("Tudo vazio");
            return;
        }

        textFieldCodigo.setText(p.getCodigo());
        textFieldNome.setText(p.getNome());
    }

    public void acaoSalvar() {
        String codigo = textFieldCodigo.getText();
        String nome = textFieldNome.getText();
        Professor p = new Professor(codigo, nome);
        ProfessorDAO dao = new ProfessorDAO(Database.getConexao());
        dao.atualizar(p);
    }

    public void acaoDeletar() {
        String codigo = textFieldCodigo.getText();
        String nome = textFieldNome.getText();
        Professor p = new Professor(codigo, nome);
        ProfessorDAO dao = new ProfessorDAO(Database.getConexao());
        dao.deleta(p);
        limpar();
    }

    public void limpar() {
        textFieldCodigo.clear();
        textFieldNome.clear();
    }
}