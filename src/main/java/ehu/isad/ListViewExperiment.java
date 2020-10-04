package ehu.isad;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListViewExperiment extends Application{


    public static void main (String [] args){
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("ListView Experiment");

        //ComboBox prestatu
        ComboBox comboBilduma = new ComboBox();

        List<String> bildumak = List.of("Abereak","Landareak","Frutuak");

        ObservableList<String> bildumaList = FXCollections.observableArrayList(bildumak);

        comboBilduma.setItems(bildumaList);
        comboBilduma.getSelectionModel().selectFirst(); //defektuz lehenengo aukera egongo da aukeratuta

        //ListView prestatu
        Map<String,List<Argazki>> bildumaMap = new HashMap<>();
        bildumaMap.put("Abereak",List.of(
                new Argazki("Elefantea","elefantea.jpeg"),
                new Argazki("Txakurra","txakurra.jpeg"),
                new Argazki("Untxia","untxia.png")
        ));

        bildumaMap.put("Landareak",List.of(
                new Argazki("Landare Berdea","landareberdea.jpeg"),
                new Argazki("Landare Horia","landarehoria.jpeg"),
                new Argazki("Cactus","cactus.png")
        ));

        bildumaMap.put("Frutuak",List.of(
                new Argazki("Marrubia","fresa.jpeg"),
                new Argazki("Sagarra","sagarra.jpeg"),
                new Argazki("Sandia","sandia.png")
        ));


        ObservableList<Argazki> argazkiList = FXCollections.observableArrayList();
        argazkiList.addAll(bildumaMap.get("Abereak"));

        ImageView imageView = new ImageView();

        ListView listViewOfArgazki = new ListView<>(argazkiList);
        listViewOfArgazki.getSelectionModel().selectedItemProperty().addListener( (observable,oldValue,newValue) -> {
           if(observable.getValue()==null) return;
            String fitx = ((Argazki)observable.getValue()).getFitx();
            imageView.setImage(lortuIrudia(fitx));
        });

        listViewOfArgazki.getSelectionModel().selectFirst();

        comboBilduma.setOnAction(e-> {
           String aukera = (String)comboBilduma.getValue();
            argazkiList.clear();
            argazkiList.addAll(bildumaMap.get(aukera));
            listViewOfArgazki.getSelectionModel().selectFirst();
        });

        VBox vbox = new VBox(comboBilduma,listViewOfArgazki,imageView);

        Scene scene = new Scene(vbox, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }




    private Image lortuIrudia(String fitx){
        InputStream is = getClass().getResourceAsStream("/"+fitx);
        BufferedImage reader = null;
        try{
            reader = ImageIO.read(is);
        } catch (IOException ioException){
            ioException.printStackTrace();
        }
        Image image = SwingFXUtils.toFXImage(reader,null);
        return image;
    }
}
