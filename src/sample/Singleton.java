package sample;

import java.util.ArrayList;

/**
 * Created by L J on 4/21/2017.
 */
public class Singleton {

    private static Singleton datastorage;
    private ArrayList<Integer> articlenumber = null;
    private ArrayList<String> gametitles = null;
    private ArrayList<String> platforms = null;
    private ArrayList<Integer> quantities = null;
    private ArrayList<Double> priceperitem=null;
    private ArrayList<Double> totalpriceperitem=null;

    private Singleton()
    {
        articlenumber = new ArrayList<>();
        gametitles = new ArrayList<>();
        platforms = new ArrayList<>();
        quantities = new ArrayList<>();
        priceperitem = new ArrayList<>();
        totalpriceperitem = new ArrayList<>();

    }

    public static Singleton getInstance()
    {
        if (datastorage == null)
        {
            datastorage = new Singleton();
        }
        return datastorage;
    }

    public void addToCartList(int artno, String gt, String pl, int qu, double ppi)
    {
        articlenumber.add(artno);
        gametitles.add(gt);
        platforms.add(pl);
        quantities.add(qu);
        priceperitem.add(ppi);
        totalpriceperitem.add(calculatepriceperitem(qu,ppi));
    }

    private double calculatepriceperitem(int q, double ppi)
    {
        return q*ppi;
    }

    public ArrayList<Integer> getArticlenumber() {
        return articlenumber;
    }

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
}
