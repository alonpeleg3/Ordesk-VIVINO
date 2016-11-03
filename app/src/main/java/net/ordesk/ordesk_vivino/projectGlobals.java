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
    final String[] menu_categories = {"פתיחה","ראשונות","פסטות","סלטים","עיקריות","קינוחים","שתיה קלה","אלכוהול"};
    private String[][] dish = {{"קרפאצ'ו","דג מטוגן","אגרול","פילה דג","גולש","המבורגר","צ'קן בורגר","חציל בטחינה","חזה עוף","חזה עוף בפטריות","חצי עוף","הום פרייז","קבב","כריך עוף","כריך פרגית","כריך סינטה","כבד עוף","כנפיים באפאלו","כנפיים טריאקי","קציצות ברוטב","קובה","לחם הבית","מוס שוקולד","נתח פילה","ניוקי בטטה","ניוקי קריספי","עוף בקארי","פרגית","פסטה אווז","פסטה בקר","פסטה בולונז","פסטה חזה עוף","פסטה פיטריות","פסטה רצועות עוף","פסטה סלמון","פאי תפוחים","פורטבלו עיזים","רביולי גבינות","סלט בטטה","סלט בולגרית","סלט בורגול","סלט בורגול ופלפלים","סלט דוריטוס","סלט חלומי","סלט חלומי אגוזים","סלט חזה עוף","סלט נבטים","סלט נודלס","סלט פלפל בולגרית","סלט סביח","שווארמה ומג'דרה","שיפוד חלומי","שיפוד סלמון","נתח סינטה","סטייק","פילה סלמון","סופלה","טרמיסו","טחינה","עוגת נטיפים","עוגת גבינה","עוגת ביסקוויטים"},
                               {"25","40","15","40","40","40","40","25","40","40","50","25","40","40","40","40","40","25","25","40","15","15","15","40","30","15","40","40","35","35","35","35","30","35","35","15","25","35","30","30","30","30","30","35","35","35","30","30","30","35","40","15","25","50","50","50","15","15","15","15","15","15"},
                               {"kg0carpatcho","kg0dag0metugan","kg0egrol","kg0file0dag","kg0gulash","kg0hamburger","kg0hamburger0of","kg0hatzil0bethina","kg0haze0of","kg0haze0of0pitriyot","kg0hetzi0of","kg0homefries","kg0kabab","kg0karih0of","kg0karih0pargit","kg0karih0sinta","kg0kaved0pire","kg0knafaim0bafalo","kg0knafaim0teriaki","kg0ktzitzot0berotev","kg0kube","kg0lehem0bait","kg0moos0chokolade","kg0netah0file","kg0nioki0batata","kg0nioki0krispi","kg0of0bekari","kg0pargit","kg0pene0avaz","kg0pene0bakar","kg0pene0bolonez","kg0pene0haze0of","kg0pene0pitriyot","kg0pene0retzuot0of","kg0pene0salmon","kg0pi0tapuhim","kg0portabelo","kg0ravioli0gvinot","kg0salat0batata","kg0salat0bulgarit","kg0salat0burgul","kg0salat0burgul0pilpel","kg0salat0doritos","kg0salat0halumi","kg0salat0halumi0egozim","kg0salat0haze0of","kg0salat0nevatim","kg0salat0nudels","kg0salat0pilpel0bulgarit","kg0salat0sabih","kg0shawarma","kg0shipud0halumi","kg0shipud0salmon","kg0sinta","kg0steak","kg0file0salmon","kg0sufle","kg0teramisu","kg0thina","kg0uga0katzefet","kg0uga0pasiflora","kg0ugat0biskwitim"},
                               {"ראשונות","עיקריות","פתיחה","עיקריות","עיקריות","עיקריות","עיקריות","ראשונות","עיקריות","עיקריות","עיקריות","ראשונות","עיקריות","עיקריות","עיקריות","עיקריות","עיקריות","ראשונות","ראשונות","עיקריות","פתיחה","פתיחה","קינוחים","עיקריות","פסטות","פתיחה","עיקריות","עיקריות","פסטות","פסטות","פסטות","פסטות","פסטות","פסטות","פסטות","קינוחים","ראשונות","פסטות","סלטים","סלטים","סלטים","סלטים","סלטים","סלטים","סלטים","סלטים","סלטים","סלטים","סלטים","סלטים","עיקריות","פתיחה","ראשונות","עיקריות","עיקריות","עיקריות","קינוחים","קינוחים","פתיחה","קינוחים","קינוחים","קינוחים"}};
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
}