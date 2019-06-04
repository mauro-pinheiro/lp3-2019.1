package lp3.college.gui.fx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;

public class GradeForm implements Initializable {
    @FXML
    private BorderPane borderPane;

    private ToolBar toolBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        toolBar = new BarraFerramentas();
        borderPane.setTop(toolBar);
    }
}