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

    public CartFile() {

    }
    public void writerArticleNumberFile(int articleNumber)  {


        try {
            File f = new File ( "cartArticleNumber.bin" );
            if (f.exists () && f.isFile ()) {
                Path path = Paths.get ( "cartArticleNumber.bin" );
                byte[] bytes = {(byte) articleNumber};

                Files.write ( path, bytes, StandardOpenOption.APPEND );
            } else {
                Path path = Paths.get ( "cartArticleNumber.bin" );
                byte[] bytes = {(byte) articleNumber};

                Files.write ( path, bytes, StandardOpenOption.CREATE );
            }

        } catch (IOException e) {
            e.printStackTrace ();
        }
    }

    public void writerQuantityFile(int q)  {


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

    public void cleanArticleNo(){
        Path path = Paths.get ( "cartArticleNumber.bin" );


        try {
            Files.deleteIfExists (path );
        } catch (IOException e) {
            e.printStackTrace ();
        }

    }
    public void cleanQuantity(){
        Path path = Paths.get ( "cartQuantity.bin" );


        try {
            Files.deleteIfExists (path );
        } catch (IOException e) {
            e.printStackTrace ();
        }

    }
    public byte[] readerArticleNumberFile() {
        try {
            Path paths = Paths.get ( "cartArticleNumber.bin" );
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
