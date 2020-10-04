package ehu.isad;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class Argazki {
    String izena;
    String helbidea; //argazkiaren erruta 

    public Argazki (String pIzena, String pHelbidea){
        this.izena = pIzena;
        this.helbidea = pHelbidea;
    }

    public String getFitx(){
        return helbidea;
    }

    @Override
    public String toString() {
        return izena;
    }
}
