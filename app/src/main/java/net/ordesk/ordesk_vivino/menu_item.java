package net.ordesk.ordesk_vivino;

/**
 * Created by Omri Elmaleh on 03/11/2016.
 */

public class menu_item {

    String menu_item_name = "";
    String[] menu_item_addons = {};

    public void setItemName(String name)
    {
        menu_item_name = name;
    }

    public String getItemName()
    {
        return menu_item_name;
    }

    public void setItemAddons(String[] addons)
    {
        menu_item_addons = addons;
    }

    public String[] getItemAddons()
    {
        return menu_item_addons;
    }

}
