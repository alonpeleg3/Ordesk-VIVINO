package net.ordesk.ordesk_vivino;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by alonpele on 009-09/11/16.
 */

public class db_base {
    db_base(){};
    JSONArray getJsonFromServer(String url, String params){
        String json_string="";
        JSONObject jsonObject;
        JSONArray jsonArray=null;

        BackgroundWorker backgroundWorker=new BackgroundWorker(url,params);
        try {
            json_string=backgroundWorker.execute().get();
            jsonObject=new JSONObject(json_string);
            jsonArray=jsonObject.getJSONArray("server_response");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    JSONArray getJsonFromServer(String url){
        return getJsonFromServer(url,"");
    }

    public item[] importMenuItems()
    {
        List<item> itemList = new ArrayList<item>();
        JSONArray itemsArray = getJsonFromServer("http://ordesk.net/login.php");
        for (int i = 0; i < itemsArray.length(); i++) {
            JSONObject rec = null;
            try {
                rec = itemsArray.getJSONObject(i);
                int itemId=rec.getInt("Item_ID");
                String itemTitle=rec.getString("Item_Name");
                double itemPrice=rec.getDouble("Item_Price");
                //String itemImageName=rec.getString("Item_Image_Name");
                //int itemCategoryId=rec.getInt("Item_Category_Id");
                itemList.add(new item(itemId,itemTitle,itemPrice));//,itemImageName,itemCategoryId
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        item[] items=new item[itemList.size()];
        itemList.toArray(items);
        return items;
    }

}
