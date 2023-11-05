package it.disi.unitn.surname;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Vittoria {
    Vittoria(String s) {
        Label lt = new Label(s+" ha vinto");
        BorderPane bp = new BorderPane();
        Stage st = new Stage();
        st.setTitle("Name Surname");
        bp.setCenter(lt);
        st.setScene(new Scene(bp, 200, 200));
        st.show();
    }
}
