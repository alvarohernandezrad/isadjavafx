package ehu.isad;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Txanpona {
     int trade_id;
     float price;
     float size;
     String time;
     float bid;
     float ask;
     float volume;

    @Override
    public String toString() {
        return "Txanpona{" +
                "trade_id=" + trade_id +
                ", price=" + price +
                ", size=" + size +
                ", time='" + time + '\'' +
                ", bid=" + bid +
                ", ask=" + ask +
                ", volume=" + volume +
                '}';
    }

    public Txanpona readFromURL(String aukera){
        String inputLine  = "";
        String mota = "";
        URL api;
        Txanpona txapona = new Txanpona();

        if(aukera.equals("BTC")){
            mota="btc";
        }
        else if(aukera.equals("ETH")){
            mota="eth";
        }
        else{
            mota="ltc";
        }

        try {
            api = new URL("https://api.gdax.com/products/" + mota + "-eur/ticker");
            BufferedReader in = new BufferedReader(new InputStreamReader(api.openStream()));
            URLConnection konexioa = api.openConnection();
            inputLine = in.readLine();
            in.close();
        }
        catch(MalformedURLException malformedURLException){
            malformedURLException.printStackTrace();
        }
        catch (IOException ioException){
            ioException.printStackTrace();
        }
        Gson gson = new Gson();
        txapona = gson.fromJson(inputLine,Txanpona.class);
        return txapona;
    }
}
