package net.ordesk.ordesk_vivino;

/**
 * Created by alonpele on 009-09/11/16.
 */

public class category {
    private int categoryId;
    private String categoryName;
    private item[] categoryItems;

    category(int categoryId,String categoryName) {
        this.categoryId=categoryId;
        this.categoryName=categoryName;
    }
}
