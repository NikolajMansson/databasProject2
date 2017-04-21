package sample;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * Created by Nikolaj on 2017-02-20.
 */
public class ReadActiveUserFile implements Serializable {
    private static ObjectInputStream input;


    public static void openFile() {
        try {
            input = new ObjectInputStream ( Files.newInputStream ( Paths.get ( "accounts.ser" ) ) );
        } catch (IOException ioException) {

            System.err.println ( "Error opening file" );
            System.exit ( 1 );
        }
    }

    public static Account readRecords() {

        try {
            while (true) {
                Account record = (Account) input.readObject ();

                return record;
            }
        } catch (EOFException endOffFileException) {
            System.out.printf ( "%nNo more records%n" );
        } catch (ClassNotFoundException classNotFoundException) {
            System.err.println ( "Invalid object type. Terminating. " );
        } catch (IOException ioException) {
            System.err.println ( "Error reading from file. Terminating. " );
        }
        return null;
    }

    public static void closeFile() {
        try {
            if (input != null) {
                input.close ();
            }
        } catch (IOException ioException) {
            System.err.println ( "Error closing file. Terminating." );
            System.exit ( 1 );
        }
    }
}
