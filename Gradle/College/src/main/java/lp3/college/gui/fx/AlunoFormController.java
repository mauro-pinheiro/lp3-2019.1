package lp3.college.gui.fx;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lp3.college.dao.AlunoDAO;
import lp3.college.entidades.Aluno;
import lp3.college.infra.Database;

public class AlunoFormController implements Initializable {
    @FXML
    private TextField textFieldCodigo;
    @FXML
    private TextField textFieldNome;
    @FXML
    private TextField textFieldEndereco;
    @FXML
    private TextField textFieldRg;
    @FXML
    private TextField textFieldCpf;
    @FXML
    private TextField textFieldTelefone;
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
        textFieldEndereco.clear();
        textFieldRg.clear();
        textFieldCpf.clear();
        textFieldTelefone.clear();
    }

    public void acaoAbrir() {
        String codigo = textFieldCodigo.getText();
        String nome = textFieldNome.getText();
        Aluno a;
        AlunoDAO dao = new AlunoDAO(Database.getConexao());

        if (!codigo.isEmpty()) {
            a = dao.buscaPorCodigo(codigo);
        } else if (!nome.isEmpty()) {
            a = dao.buscaPorNome(nome);
        } else {
            System.out.println("Tudo vazio");
            return;
        }

        textFieldCodigo.setText(a.getCodigo());
        textFieldNome.setText(a.getNome());
        textFieldCpf.setText(a.getCpf());
        textFieldEndereco.setText(a.getEndereco());
        textFieldRg.setText(a.getRg());
        textFieldTelefone.setText(a.getTelefone());
    }

    public void acaoSalvar() {
        AlunoDAO dao = new AlunoDAO(Database.getConexao());
        String codigo = textFieldCodigo.getText();
        String nome = textFieldNome.getText();
        String end = textFieldEndereco.getText();
        String rg = textFieldRg.getText();
        String cpf = textFieldCpf.getText();
        String telefone = textFieldTelefone.getText();
        Aluno a = new Aluno(codigo, nome, rg, end, cpf, telefone);
        int id = dao.existe(a);
        System.out.println(id);
        
        if(id <= 0){
            System.out.println("Salvar");
            dao.salva(a);
        } else {
            System.out.println("Atualizar");
            a.setId(id);
            dao.atualiza(a);
        }
    }

    public void acaoDeletar() {
        String codigo = textFieldCodigo.getText();
        String nome = textFieldNome.getText();
        String end = textFieldEndereco.getText();
        String rg = textFieldRg.getText();
        String cpf = textFieldCpf.getText();
        String telefone = textFieldTelefone.getText();
        
        Aluno a = new Aluno(codigo, nome, rg, end, cpf, telefone);
        AlunoDAO dao = new AlunoDAO(Database.getConexao());
        dao.deleta(a);
        acaoNovo();
    }
}