package net.ordesk.ordesk_vivino;
import android.app.Application;
import android.widget.FrameLayout;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.concurrent.ExecutionException;
public class projectGlobals extends Application{

    private int index = 0;
    private int menu_item_num = 100;
    private boolean[] order_flag = new boolean[menu_item_num];
    private FrameLayout[] order_btn_fl = new FrameLayout[menu_item_num];
    private String[][] dish = {{"סלט","בירה","פיצה","כדורי ריזוטו","שיפוד","קרפאצ'ו","קינוח חלווה"},{"10","15","35","20","50","20","25"},{"salad3","goldstar","pizza","rizotoball","shipud","carpacho","kinuahhalva"}};

    public void importMenuItems()
    {
        String json_string="";
        JSONObject jsonObject;
        JSONArray jsonArray;

        BackgroundWorker backgroundWorker=new BackgroundWorker(this);
        try {
            json_string=backgroundWorker.execute().get();
            //prodTitle.setText(json_string);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        try {
            jsonObject=new JSONObject(json_string);
            jsonArray=jsonObject.getJSONArray("server_response");
            JSONObject JO=jsonArray.getJSONObject(0);
            //int count=0;
            //while(count<jsonObject.length()){
            //    JSONObject JO=jsonArray.getJSONObject(count);
            //    name=JO.getString("Menu_Name");
            //}
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String[][] getDishArr()
    {
        return dish;
    }

    public String getDish(int i, int j)
    {
        return dish[i][j];
    }

    public void OrderBtnFlInit()
    {
        int i=0;
        while(i < menu_item_num)
        {
            order_btn_fl[i] = new FrameLayout(this);
            i++;
        }
    }

    public FrameLayout getOrderBtnFl(int i)
    {
        return order_btn_fl[i];
    }

    public void setOrderBtnFl(FrameLayout fl, int i)
    {
        order_btn_fl[i]=fl;
    }

    public int getMyIndex()
    {
        return index;
    }

    public void setMyIndex(int i)
    {
        index=i;
    }

    public int getItemNum() {

        return menu_item_num;
    }

    public void setOrder(int itemNum, boolean order)
    {
        order_flag[itemNum] = order;
    }

    public boolean getOrder(int itemNum)
    {
        return order_flag[itemNum];
    }

    public boolean[] getOrderArray()
    {
        return order_flag;
    }
}