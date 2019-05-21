package lp3.college.gui.fx;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ToolBarController implements Initializable {
    @FXML private Button buttonNovo;
    @FXML private Button buttonAbrir;
    @FXML private Button buttonSalvar;
    @FXML private Button buttonDeletar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image novoIcon = new Image(getClass().getResourceAsStream("images/new doc.png"));
        Image openIcon = new Image(getClass().getResourceAsStream("images/open doc.png"));
        Image SaveIcon = new Image(getClass().getResourceAsStream("images/save doc.png"));
        Image DelIcon = new Image(getClass().getResourceAsStream("images/delete doc.png"));

        buttonNovo.setGraphic(new ImageView(novoIcon));
        buttonAbrir.setGraphic(new ImageView(openIcon));
        buttonSalvar.setGraphic(new ImageView(SaveIcon));
        buttonDeletar.setGraphic(new ImageView(DelIcon));
    }
}