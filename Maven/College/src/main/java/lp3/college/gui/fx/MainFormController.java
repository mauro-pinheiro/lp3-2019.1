package lp3.college.gui.fx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class MainFormController implements Initializable {
    @FXML
    private MenuItem menuItemSair;
    @FXML
    private MenuItem menuItemSobre;
    @FXML
    private MenuItem menuItemTabelasAluno;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private VBox vBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadSplash();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void menuItemSairOnAction(ActionEvent e) {
        Stage window = (Stage) CollegeApp.getInstance().window;
        window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    public void menuItemSobreOnAction(ActionEvent e) throws IOException {
        TextArea textAreaSobre = (TextArea) FXMLLoader.load(getClass().getResource("SobreForm.fxml"));
        Stage stage = new Stage(StageStyle.UTILITY);
        stage.setScene(new Scene(textAreaSobre));
        stage.setTitle("Sobre");
        stage.sizeToScene();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void menuItemTabelasAlunoOnAction() throws IOException {
        Parent alunoForm = FXMLLoader.load(getClass().getResource("AlunoForm.fxml"));
        anchorPane.getChildren().setAll(alunoForm);
    }

    public void menuItemTabelasDisciplinaOnAction() throws IOException {
        Parent disciplinaForm = FXMLLoader.load(getClass().getResource("DisciplinaForm.fxml"));
        anchorPane.getChildren().setAll(disciplinaForm);
    }

    public void menuItemTabelasProfessorOnAction() throws IOException {
        Parent professorForm = FXMLLoader.load(getClass().getResource("ProfessorForm.fxml"));
        anchorPane.getChildren().setAll(professorForm);
    }

    public void menuItemTabelasCursoOnAction() throws IOException {
        Parent cursoForm = FXMLLoader.load(getClass().getResource("CursoForm.fxml"));
        anchorPane.getChildren().setAll(cursoForm);
    }

    public void menuItemSpfGradeOnAction() throws IOException {
        Parent gradeForm = FXMLLoader.load(getClass().getResource("GradeForm.fxml"));
        anchorPane.getChildren().setAll(gradeForm);
    }
    
    public void menuItemSpfMatAlunoOnAction() throws IOException {
        Parent matAlunoForm = FXMLLoader.load(getClass().getResource("MatAluno.fxml"));
        anchorPane.getChildren().setAll(matAlunoForm);
    }

    public void menuItemSpfProfDiscOnAction() throws IOException {
        Parent profDiscForm = FXMLLoader.load(getClass().getResource("ProfDisc.fxml"));
        anchorPane.getChildren().setAll(profDiscForm);
    }


    private void loadSplash() throws IOException {
        Parent splash = FXMLLoader.load(getClass().getResource("Splash.fxml"));
        Stage stage = new Stage(StageStyle.TRANSPARENT);
        stage.setScene(new Scene(splash));
        stage.sizeToScene();
        stage.requestFocus();
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), splash);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.setCycleCount(1);
        fadeIn.play();
        fadeIn.setOnFinished(e -> stage.close());
        stage.showAndWait();
    }
}