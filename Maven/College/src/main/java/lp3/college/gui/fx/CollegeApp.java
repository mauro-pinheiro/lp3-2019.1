package lp3.college.gui.fx;


import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class CollegeApp extends Application {
    private static CollegeApp instance = null;

    public static CollegeApp getInstance(){
        if(instance == null){
            return new CollegeApp();
        } else {
            return instance;
        }
    }

    boolean logged = true;
    Stage window;

    public CollegeApp(){
        super();
        instance = this;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        Parent mainForm = FXMLLoader.load(getClass().getResource("MainForm.fxml"));

        primaryStage.setTitle("College App");
        primaryStage.setMaximized(true);
        primaryStage.setScene(new Scene(mainForm));
        primaryStage.setOnCloseRequest(e -> onCloseRequest(e));
        primaryStage.requestFocus();
        primaryStage.show();

        loadLogin();
    }

    public void run(String[] args){
        launch(args);
    }

    public void onCloseRequest(WindowEvent e) {
        ConfimationDialog dialog = new ConfimationDialog("Sair", "Deseja mesmo sair?");
        dialog.showAndWait().ifPresent(b -> {
            if (b == ButtonType.YES) {
                window.close();
            } else {
                e.consume();
            }
        });
    }

    private void loadLogin() throws IOException {
        Parent loginForm = FXMLLoader.load(getClass().getResource("LoginForm.fxml"));
        Stage stage = new Stage(StageStyle.UTILITY);
        stage.setTitle("Login");
        stage.setScene(new Scene(loginForm));
        stage.sizeToScene();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOnCloseRequest(e -> LoginOnCloseRequest(e));
        stage.showAndWait();
    }

    public void LoginOnCloseRequest(WindowEvent e){
        Platform.exit();
    }
}