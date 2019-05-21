package lp3.college.gui.fx;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
        var source = (Button) e.getSource();
        var stage = (Stage) source.getScene().getWindow();
        var usuario = textFieldUsuario.getText();
        var senha = passwordFieldSenha.getText();
        if(usuario.equals("admin") && senha.equals("admin")){
            stage.close();
        } else {
            labelFeedback.setText("Usuário ou Senha incorretos");
        }
    }
}