package lp3.college.gui.fx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class DisciplinaFormController implements Initializable {
    @FXML
    private TextField textFieldCodigo;
    @FXML
    private TextField textFieldNome;
    @FXML
    private TextArea textAreaEmenta;
    @FXML
    private TextField textFieldCh;
    @FXML
    private BorderPane borderPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ToolBar toolBar = (ToolBar) FXMLLoader.load(getClass().getResource("ToolBar.fxml"));
            borderPane.setTop(toolBar);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}