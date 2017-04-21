package sample;

import java.util.ArrayList;

public class Cart {

    public Cart() {
    }

    private ArrayList<Game> itemId;

    private ArrayList<Game> itemAmount;

    private ArrayList<Double> pricePerItem;

    private ArrayList<Double> totalPricePerItem;

    private String itemName;

    private String platform;

    public void addItemToCart(Item item, Integer amountOfItems) {
        // TODO implement here
    }

    public void removeItemFromCart(Item item) {
        // TODO implement here
    }

    public void finishSale() {
        // TODO implement here
    }

    public Double calculateTotalPrice(ArrayList<Item> item) {
        // TODO implement here
        return null;
    }

    public double getPricePerItem() {
        // TODO implement here
        return Double.parseDouble ( null );
    }

    public Double getPricePerItem(Game itemID) {
        // TODO implement here
        return null;
    }

    public void sendOrderToDB(Order order) {
        //Lägg till metod i DB för att lägga in en order
    }

}