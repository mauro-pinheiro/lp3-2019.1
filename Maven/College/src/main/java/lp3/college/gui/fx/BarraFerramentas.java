package lp3.college.gui.fx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BarraFerramentas extends ToolBar{
    private Button buttonNovo = new Button();
    private Button buttonAbrir = new Button();
    private Button buttonSalvar = new Button();
    private Button buttonDeletar = new Button();

    public BarraFerramentas() {
        Image novoIcon = new Image(getClass().getResourceAsStream("images/new doc.png"));
        Image openIcon = new Image(getClass().getResourceAsStream("images/open doc.png"));
        Image SaveIcon = new Image(getClass().getResourceAsStream("images/save doc.png"));
        Image DelIcon = new Image(getClass().getResourceAsStream("images/delete doc.png"));

        buttonNovo.setGraphic(new ImageView(novoIcon));
        buttonAbrir.setGraphic(new ImageView(openIcon));
        buttonSalvar.setGraphic(new ImageView(SaveIcon));
        buttonDeletar.setGraphic(new ImageView(DelIcon));

        this.getItems().addAll(buttonNovo, buttonAbrir, buttonSalvar, buttonDeletar);
    }

    public void setNewAction(EventHandler<ActionEvent> eventHandler){
        buttonNovo.setOnAction(eventHandler);
    }

    public void setOpenAction(EventHandler<ActionEvent> eventHandler){
        buttonAbrir.setOnAction(eventHandler);
    }

    public void setSaveAction(EventHandler<ActionEvent> eventHandler){
        buttonSalvar.setOnAction(eventHandler);
    }

    public void setDeleteAction(EventHandler<ActionEvent> eventHandler){
        buttonDeletar.setOnAction(eventHandler);
    }
}