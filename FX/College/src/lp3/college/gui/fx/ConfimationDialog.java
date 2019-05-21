package lp3.college.gui.fx;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class ConfimationDialog extends Alert {
    
    public ConfimationDialog(String title, String message){
        super(AlertType.CONFIRMATION);
        setTitle(title);
        setContentText(message);
        getButtonTypes().clear();
        getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
    }
}