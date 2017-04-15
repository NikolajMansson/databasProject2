package sample;

/**
 * 
 */
public abstract class Catcher {


    public Catcher() {
    }


    public abstract void addToDB(Object object);

    public abstract void getFromDB(Object object);

    public abstract void removeFromDB(Object object);

}