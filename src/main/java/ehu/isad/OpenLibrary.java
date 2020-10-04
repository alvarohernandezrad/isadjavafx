package ehu.isad;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class OpenLibrary extends Application{

    public static void main (String[] args){
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("OpenLibrary");
        Label label = new Label();
        label.setWrapText(true); //Si wrap = TRUE, el texto se ajusta si excede el ancho de la celda; de lo contrario, no.
        ComboBox comboLiburuak = new ComboBox();
        List<String> liburuak = List.of("Blockchain: Blueprint for a New Economy","R for Data Science","Fluent Python","Natural Language Processing with PyTorch","Data Algorithms"); //comboBoxean sartuko ditugun aukerak lista batean jarri
        ObservableList<String> liburuakList = FXCollections.observableArrayList(liburuak); //liburuen lista Observable bihurtu
        comboLiburuak.setItems(liburuakList); //lista comboBoxean sartu
        comboLiburuak.setEditable(true); //comboBoxeko labela editatu daiteke

        comboLiburuak.setOnAction(e -> {
            String aukera = (String)comboLiburuak.getValue(); //ikusi nahi dugun liburua gordeko dugu
            String ISBN = this.getISBN(aukera); //metodo honi esker,ikusi nahi dugun liburuaren ISBN-a eskuratuko dugu.
            Liburua liburua = new Liburua();
            liburua = liburua.readFromURL(ISBN); //metodo honi liburuaren ISBN-a pasatuko diogu api-an ISBN-arekin bilaketa egiteko.
            label.setText(liburua.getTitle()+" "+liburua.getSubtitle()+"\n"+liburua.getNumber_of_pages()+"\n"+liburua.getPublishers()[0]);
            for(int i=1;i< liburua.getPublishers().length;i++){ //argitaletxe bat baino gehiago egongo baleude atzean gehituko genituzke
                label.setText(label.getText()+","+liburua.getPublishers()[i]);
            }
        });
        VBox vbox = new VBox(comboLiburuak,label); //Vbox-ean bi gauza izango ditugu, comboBox-a eta label-a
        Scene scene = new Scene(vbox, 350, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private String getISBN(String aukera){
        String isbn = "";
        switch(aukera){
            case "Blockchain: Blueprint for a New Economy":
                isbn = "9781491920497";
                break;
            case "R for Data Science":
                isbn = "1491910399";
                break;
            case "Fluent Python":
                isbn = "1491946008";
                break;
            case "Natural Language Processing with PyTorch":
                isbn = "1491978236";
                break;
            case "Data Algorithms":
                isbn = "9781491906187";
                break;
        }
        return isbn;
    }
}
