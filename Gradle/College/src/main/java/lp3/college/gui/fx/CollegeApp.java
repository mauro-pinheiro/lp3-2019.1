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
        Parent splash = FXMLLoader.load(getClass().getResource("Splash.fxml"));
        Parent mainForm = FXMLLoader.load(getClass().getResource("MainForm.fxml"));
        Stage stage = new Stage(StageStyle.TRANSPARENT);
        stage.setScene(new Scene(splash));
        stage.sizeToScene();
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), splash);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.setCycleCount(1);
        fadeIn.play();
        fadeIn.setOnFinished(e -> stage.close());
        stage.showAndWait();

        primaryStage.setTitle("College App");
        primaryStage.setMaximized(true);
        primaryStage.setScene(new Scene(mainForm));
        primaryStage.show();
    }

    public void run(String[] args){
        launch(args);
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
            window.close();
        }
    }
}