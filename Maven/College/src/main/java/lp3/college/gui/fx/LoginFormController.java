package lp3.college.gui.fx;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginFormController implements Initializable {
    @FXML
    private TextField textFieldUsuario;
    @FXML
    private PasswordField passwordFieldSenha;
    @FXML
    private Button buttonEntrar;
    @FXML
    private Label labelFeedback;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void buttonEntrarOnAction(ActionEvent e){
        Button source = (Button) e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        String usuario = textFieldUsuario.getText();
        String senha = passwordFieldSenha.getText();
        if(usuario.equals("admin") && senha.equals("admin")){
            stage.close();
        } else {
            labelFeedback.setText("Usuário ou Senha incorretos");
        }
    }
}