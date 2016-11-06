package net.ordesk.ordesk_vivino;

/**
 * Created by alonpele on 005-05/11/16.
 */

public class item {
    private String category;
    private String title;
    private double price;
    private String imagePath;

    item(String itemTitle,double itemPrice){
        title=itemTitle;
        price=itemPrice;
    }

    public String getTitle() {return title;}
    public double getPrice() {return price;}

}
