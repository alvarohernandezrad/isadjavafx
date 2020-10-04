package ehu.isad;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class KriptoTxanponenPrezioa extends Application  {

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Txanponen Prezioa");

        ComboBox comboBox = new ComboBox();
        Label label = new Label();


        comboBox.getItems().add("BTC");
        comboBox.getItems().add("ETH");
        comboBox.getItems().add("LTC");

        comboBox.setEditable(true);

        comboBox.setOnAction(e -> {
            String aukera = (String)comboBox.getValue();
            Txanpona txanpona = new Txanpona();
            txanpona = txanpona.readFromURL(aukera);
            label.setText("1 "+aukera+"="+txanpona.price+" €");
        });

        VBox vbox = new VBox(label,comboBox);

        Scene scene = new Scene(vbox, 200, 120);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}