package lp3.college.util;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import lp3.college.entidades.EntidadeNomeada;

public class CustomCallback<T extends EntidadeNomeada> implements Callback<ListView<T>, ListCell<T>>{

    @Override
    public ListCell<T> call(ListView<T> param) {
        return new ListCell<T>(){
            @Override
            protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);
                if(item == null || empty){
                    setText("");
                } else {
                    setText(item.getNome());
                }
            }
        };
    }

}