package net.ordesk.ordesk_vivino;

/**
 * Created by alonpele on 005-05/11/16.
 */

public class item {
    private int itemId;
    private String title;
    private double price;
    private String imageName;
    private String category;

    item(int itemId,String title,double price,String imageName,String category)
    {
        this.itemId = itemId;
        this.title = title;
        this.price = price;
        this.imageName = imageName;
        this.category = category;
    }

    public double getItemId() {return itemId;}
    public String getTitle() {return title;}
    public double getPrice() {return price;}
    public String getImageName() {return imageName;}
    public String getCategory() {return category;}

}
