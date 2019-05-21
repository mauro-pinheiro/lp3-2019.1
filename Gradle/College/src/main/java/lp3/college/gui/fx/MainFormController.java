package lp3.college.gui.fx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainFormController implements Initializable {
    @FXML
    private MenuItem menuItemSair;

    @FXML
    private MenuItem menuItemSobre;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private VBox vBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void menuItemSairOnAction(ActionEvent e) {
        CollegeApp.getInstance().window.close();
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
}