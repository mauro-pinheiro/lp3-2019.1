package lp3.college.gui.fx;


import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

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
        primaryStage.show();
    }

    public void run(String[] args){
        launch(args);
    }

    public void onCloseRequest(WindowEvent e) {
        var dialog = new ConfimationDialog("Sair", "Deseja mesmo sair?");
        dialog.showAndWait().ifPresent(b -> {
            if (b == ButtonType.YES) {
                window.close();
            } else {
                e.consume();
            }
        });
    }

    public static void main(String args[]){
        getInstance().run(args);
    }
}