package lp3.college.gui.fx;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class LoginForm extends Stage {
    private Label labelUsuario = new Label("Usuario");
    private Label labelSenha = new Label("Senha");
    private Label labelFeedback = new Label();

    private TextField textFieldUsuario = new TextField();
    private PasswordField passwordFieldSenha = new PasswordField();

    private Button buttonEntrar = new Button("Entrar");

    private GridPane root = new GridPane();

    private Scene scene = new Scene(root);

    private boolean logged = false;

    public LoginForm(){
        setOnCloseRequest(e -> onCloseRequest(e));
        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);

        var coluna1 = new ColumnConstraints(75);
        var coluna2 = new ColumnConstraints(150);

        root.getColumnConstraints().addAll(coluna1,coluna2);
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(10));
        GridPane.setHalignment(labelUsuario, HPos.LEFT);
        GridPane.setHalignment(labelSenha, HPos.LEFT);
        GridPane.setHalignment(buttonEntrar, HPos.RIGHT);
        GridPane.setHalignment(textFieldUsuario, HPos.RIGHT);
        GridPane.setHalignment(passwordFieldSenha, HPos.RIGHT);
        GridPane.setHalignment(labelFeedback, HPos.LEFT);
        root.addColumn(0, labelUsuario, labelSenha, buttonEntrar);
        root.addColumn(1, textFieldUsuario, passwordFieldSenha, labelFeedback);

        buttonEntrar.setOnAction(e -> buttonEntrarOnAction(e));

        setScene(scene);
    }

    public void buttonEntrarOnAction(ActionEvent e){
        if(textFieldUsuario.getText().equalsIgnoreCase("admin")
            && passwordFieldSenha.getText().equals("admin")){
                logged = true;
                close();
            }
        labelFeedback.setText("Usuário ou senha incorreto");
    }

    public void onCloseRequest(WindowEvent e){
        if(!logged){
            var dialog = new ConfimationDialog("Sair", "Deseja mesmo sair?");
            dialog.showAndWait().ifPresent(b -> {
                if(b == ButtonType.YES){
                    Platform.exit();
                } else {
                    e.consume();
                }
            });
        } else {
            close();
        }
    }
}