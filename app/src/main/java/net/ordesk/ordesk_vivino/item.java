package net.ordesk.ordesk_vivino;

/**
 * Created by alonpele on 005-05/11/16.
 */

public class item {
    private int itemId;
    private String category;
    private String title;
    private double price;
    private String imageName;
    private int categoryId;

    item(int itemId,String title,double price){//,String imageName,int categoryId
        this.itemId=itemId;
        this.title=title;
        this.price=price;
        //this.imageName=imageName;
        //this.categoryId=categoryId;
    }

    public String getTitle() {return title;}
    public double getPrice() {return price;}

}
