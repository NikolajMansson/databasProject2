package sample;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.NoSuchElementException;

/**
 * Created by Nikolaj on 2017-02-20.
 */
public class CreateActiveUserFile implements Serializable {
    private static ObjectOutputStream output;

    public void startUpload(Account account) {

        openFile ();
        addRecords ( account );
        closeFile ();
    }

    public void openFile() {
        try {
            output = new ObjectOutputStream (
                    Files.newOutputStream ( Paths.get ( "accounts.ser" ) )
            );
        } catch (IOException ioException) {
            System.err.println ( "Error opening file. Terminating" );
            System.exit ( 1 );
        }
    }

    public void addRecords(Account account) {
        try {
            output.writeObject ( account );
        } catch (NoSuchElementException elementException) {
            System.err.println ( "Invalid input. Please try again" );
        } catch (IOException ioException) {
            System.err.println ( "Error writing to file. Terminating" );
        }
    }

    public void closeFile() {
        try {
            if (output != null) {
                output.close ();
            }
        } catch (IOException ioException) {
            System.err.println ( "Error closing file. Terminating." );
        }
    }
}
