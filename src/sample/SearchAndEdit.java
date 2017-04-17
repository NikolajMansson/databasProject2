package sample;

/**
 * 
 */
public abstract class SearchAndEdit {

    /**
     * Default constructor
     */
    public SearchAndEdit() {
    }



    /**
     * @param object
     */
    public abstract void addToDB(Object object);

    /**
     * @param object
     */
    public abstract void getFromDB(Object object);

    /**
     * @param object
     */
    public abstract void removeFromDB(Object object);

}