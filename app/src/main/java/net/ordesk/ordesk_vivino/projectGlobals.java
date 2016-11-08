package net.ordesk.ordesk_vivino;
import android.app.Application;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class projectGlobals extends Application{

    private int index = 0;
    private int menu_item_num = 63;
    private boolean[] order_flag = new boolean[menu_item_num];
    private FrameLayout[] order_btn_fl = new FrameLayout[menu_item_num];
    final String[] menu_categories = {"פתיחה","ראשונות","פסטות","סלטים","עיקריות","קינוחים","שתיה קלה","אלכוהול"};
    private String[][] dish = {{"קרפאצ'ו","כדורי פירה","דג מטוגן","אגרול","פילה דג","גולש","המבורגר","צ'קן בורגר","חציל בטחינה","חזה עוף","חזה עוף בפטריות","חצי עוף","הום פרייז","קבב","כריך עוף","כריך פרגית","כריך סינטה","כבד עוף","כנפיים באפאלו","כנפיים טריאקי","קציצות ברוטב","קובה","לחם הבית","מוס שוקולד","נתח פילה","ניוקי בטטה","ניוקי קריספי","עוף בקארי","פרגית","פסטה אווז","פסטה בקר","פסטה בולונז","פסטה חזה עוף","פסטה פיטריות","פסטה רצועות עוף","פסטה סלמון","פאי תפוחים","פורטבלו עיזים","רביולי גבינות","סלט בטטה","סלט בולגרית","סלט בורגול","סלט בורגול ופלפלים","סלט דוריטוס","סלט חלומי","סלט חלומי אגוזים","סלט חזה עוף","סלט נבטים","סלט נודלס","סלט פלפל בולגרית","סלט סביח","שווארמה ומג'דרה","שיפוד חלומי","שיפוד סלמון","נתח סינטה","סטייק","פילה סלמון","סופלה","טרמיסו","טחינה","עוגת נטיפים","עוגת גבינה","עוגת ביסקוויטים"},
                               {"25","15","40","15","40","40","40","40","25","40","40","50","25","40","40","40","40","40","25","25","40","15","15","15","40","30","15","40","40","35","35","35","35","30","35","35","15","25","35","30","30","30","30","30","35","35","35","30","30","30","35","40","15","25","50","50","50","15","15","15","15","15","15"},
                               {"kg0carpatcho","kg0pire0balls","kg0dag0metugan","kg0egrol","kg0file0dag","kg0gulash","kg0hamburger","kg0hamburger0of","kg0hatzil0bethina","kg0haze0of","kg0haze0of0pitriyot","kg0hetzi0of","kg0homefries","kg0kabab","kg0karih0of","kg0karih0pargit","kg0karih0sinta","kg0kaved0pire","kg0knafaim0bafalo","kg0knafaim0teriaki","kg0ktzitzot0berotev","kg0kube","kg0lehem0bait","kg0moos0chokolade","kg0netah0file","kg0nioki0batata","kg0nioki0krispi","kg0of0bekari","kg0pargit","kg0pene0avaz","kg0pene0bakar","kg0pene0bolonez","kg0pene0haze0of","kg0pene0pitriyot","kg0pene0retzuot0of","kg0pene0salmon","kg0pi0tapuhim","kg0portabelo","kg0ravioli0gvinot","kg0salat0batata","kg0salat0bulgarit","kg0salat0burgul","kg0salat0burgul0pilpel","kg0salat0doritos","kg0salat0halumi","kg0salat0halumi0egozim","kg0salat0haze0of","kg0salat0nevatim","kg0salat0nudels","kg0salat0pilpel0bulgarit","kg0salat0sabih","kg0shawarma","kg0shipud0halumi","kg0shipud0salmon","kg0sinta","kg0steak","kg0file0salmon","kg0sufle","kg0teramisu","kg0thina","kg0uga0katzefet","kg0uga0pasiflora","kg0ugat0biskwitim"},
                               {"ראשונות","פתיחה","עיקריות","פתיחה","עיקריות","עיקריות","עיקריות","עיקריות","ראשונות","עיקריות","עיקריות","עיקריות","ראשונות","עיקריות","עיקריות","עיקריות","עיקריות","עיקריות","ראשונות","ראשונות","עיקריות","פתיחה","פתיחה","קינוחים","עיקריות","פסטות","פתיחה","עיקריות","עיקריות","פסטות","פסטות","פסטות","פסטות","פסטות","פסטות","פסטות","קינוחים","ראשונות","פסטות","סלטים","סלטים","סלטים","סלטים","סלטים","סלטים","סלטים","סלטים","סלטים","סלטים","סלטים","סלטים","עיקריות","פתיחה","ראשונות","עיקריות","עיקריות","עיקריות","קינוחים","קינוחים","פתיחה","קינוחים","קינוחים","קינוחים"}};

    private FrameLayout[] order_screen_fl = new FrameLayout[1];

    public FrameLayout getOrderScrnFL()
    {
        return order_screen_fl[0];
    }

    public void setOrderScrnFL(FrameLayout i)
    {
        order_screen_fl[0] = i;
    }

    private List<item> itemList = new ArrayList<item>();
    public void importMenuItems()
    {
        String json_string="";
        JSONObject jsonObject;
        JSONArray jsonArray;

        BackgroundWorker backgroundWorker=new BackgroundWorker(this);
        try {
            json_string=backgroundWorker.execute().get();
            jsonObject=new JSONObject(json_string);
            jsonArray=jsonObject.getJSONArray("server_response");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject rec = jsonArray.getJSONObject(i);
                String itemTitle=rec.getString("Item_Name");
                double itemPrice=rec.getDouble("Item_Price");
                itemList.add(new item(itemTitle,itemPrice));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public item[] getItems() {
        importMenuItems();
        item[] items=new item[itemList.size()];
        itemList.toArray(items);
        return items;}

    public String[] getCategories()
    {
        return menu_categories;
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

    public void lrg_img(String img_name)
    {
        FrameLayout lrg_img0_fl = new FrameLayout(this);
        FrameLayout.LayoutParams lrg_img0_flp = new FrameLayout.LayoutParams(720,530);
        lrg_img0_flp.setMargins(0,30,0,0);
        lrg_img0_flp.setMarginStart(70);
        lrg_img0_fl.setLayoutParams(lrg_img0_flp);
        lrg_img0_fl.setBackgroundColor(0xFFafabab);

        FrameLayout full_scrn_fl = new FrameLayout(this);
        FrameLayout.LayoutParams full_scrn_flp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT);
        full_scrn_fl.setLayoutParams(full_scrn_flp);
        full_scrn_fl.setBackgroundColor(0xc0000000);
        full_scrn_fl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order_screen_fl[0].removeView(v);
            }
        });

        FrameLayout lrg_img_fl = new FrameLayout(this);
        FrameLayout.LayoutParams lrg_img_flp = new FrameLayout.LayoutParams(700,470);
        lrg_img_flp.setMargins(0,10,0,0);
        lrg_img_flp.setMarginStart(10);
        lrg_img_fl.setLayoutParams(lrg_img_flp);

        ImageView iv = new ImageView(this);
        FrameLayout.LayoutParams iv_flp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT);
        iv.setLayoutParams(iv_flp);
        int res2ID = getResources().getIdentifier("full0"+img_name, "drawable", getPackageName());
        iv.setBackgroundResource(res2ID);

        lrg_img_fl.addView(iv);
        lrg_img0_fl.addView(lrg_img_fl);
        full_scrn_fl.addView(lrg_img0_fl);
        order_screen_fl[0].addView(full_scrn_fl);

    }
}