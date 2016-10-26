package net.ordesk.ordesk_vivino;

import android.app.Application;

public class projectGlobals extends Application{

    private int menu_item_num = 100;
    private boolean[] order_flag = new boolean[menu_item_num];

    public void setOrder(int itemNum, boolean order) {

        order_flag[itemNum] = order;
    }

    public boolean getOrder(int itemNum) {

        return order_flag[itemNum];
    }

    public boolean[] getOrderArray() {

        return order_flag;
    }
}