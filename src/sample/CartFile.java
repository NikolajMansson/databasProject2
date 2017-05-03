package sample;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created by L J on 4/21/2017.
 */
public class CartFile {

    //StringBuilder string = new StringBuilder ();

 //   ArrayList<Item> record = new ArrayList<Item> ();

   // private static CartFile datastorage;

    //  private ArrayList<Item> itemList;
    /*
    private ArrayList<Integer> articlenumber = null;
    private ArrayList<String> gametitles = null;
    private ArrayList<String> platforms = null;
    private ArrayList<Integer> quantities = null;
    private ArrayList<Double> priceperitem = null;
    private ArrayList<Double> totalpriceperitem = null;
    */


     /* private CartFile() {



        articlenumber = new ArrayList<> ();
        gametitles = new ArrayList<> ();
        platforms = new ArrayList<> ();
        quantities = new ArrayList<> ();
        priceperitem = new ArrayList<> ();
        totalpriceperitem = new ArrayList<> ();



    }*/

    public CartFile() {

    }
/*
    public ArrayList<Item> getInstance() {


        if (datastorage == null) {
            datastorage = new ArrayList<Item> ();
            startUpload ( datastorage );
            startDownload ();
        }
        else {
            datastorage = extractDatastorageFromFile ();
        }
        return datastorage;
    }
*/
    /*
        public void addToCartList(int artno, String gt, String pl, int qu, double ppi) {
            articlenumber.add ( artno );
            gametitles.add ( gt );
            platforms.add ( pl );
            quantities.add ( qu );
            priceperitem.add ( ppi );
            totalpriceperitem.add ( calculatepriceperitem ( qu, ppi ) );
        }*/


/*
    private double calculatepriceperitem(int q, double ppi) {
        return q * ppi;
    }
    */
    /*
        public ArrayList<Integer> getArticlenumber() {
            return articlenumber;
        }
    */

    /*

    public ArrayList<String> getGametitles() {
        return gametitles;
    }

    public ArrayList<String> getPlatforms() {
        return platforms;
    }

    public ArrayList<Integer> getQuantities() {
        return quantities;
    }

    public ArrayList<Double> getPriceperitem() {
        return priceperitem;
    }

    public ArrayList<Double> getTotalpriceperitem() {
        return totalpriceperitem;
    }
    */

//Här sparas artikelnumret på vad som ska köpas
    public void writerArticleNoFile(int articleNo) throws IOException {


        try {
            File f = new File ( "cartArticleNo.bin" );
            if (f.exists () && f.isFile ()) {
                Path path = Paths.get ( "cartArticleNo.bin" );
                byte[] bytes = {(byte) articleNo};

                Files.write ( path, bytes, StandardOpenOption.APPEND );
            } else {
                Path path = Paths.get ( "cartArticleNo.bin" );
                byte[] bytes = {(byte) articleNo};

                Files.write ( path, bytes, StandardOpenOption.CREATE );
            }

        } catch (IOException e) {
            e.printStackTrace ();
        }
    }

    public void writerQuantityFile(int q) throws IOException {


        try {
            File f = new File ( "cartQuantity.bin" );
            if (f.exists () && f.isFile ()) {
                Path path = Paths.get ( "cartQuantity.bin" );
                byte[] bytes = {(byte) q};

                Files.write ( path, bytes, StandardOpenOption.APPEND );
            } else {
                Path path = Paths.get ( "cartQuantity.bin" );
                byte[] bytes = {(byte) q};

                Files.write ( path, bytes, StandardOpenOption.CREATE );
            }

        } catch (IOException e) {
            e.printStackTrace ();
        }
    }

//Här hämtas filen över vilka artikelnummer som finns på föremål som ska köpas
    public byte[] readerArticleNoFile() {
        try {
            Path paths = Paths.get ( "cartArticleNo.bin" );
            byte[] bytesRead = Files.readAllBytes ( paths );
            return bytesRead;

        } catch (IOException ex) {
            ex.printStackTrace ();
        }

        return null;
    }

    public byte[] readerQuantityFile() {
        try {
            Path paths = Paths.get ( "cartQuantity.bin" );
            byte[] bytesRead = Files.readAllBytes ( paths );
            return bytesRead;

        } catch (IOException ex) {
            ex.printStackTrace ();
        }

        return null;
    }
}
