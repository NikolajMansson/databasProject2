package sample;

import java.util.ArrayList;

/**
 * Created by Nikolaj on 2017-04-29.
 */
public class GuestOrder extends Order {

    public GuestOrder(EmployeeAccount account, ArrayList<Item> itemList) {
        super (  itemList );
    }



    public GuestOrder(ArrayList<Item> itemList) {
        super ( itemList);

    }
}
