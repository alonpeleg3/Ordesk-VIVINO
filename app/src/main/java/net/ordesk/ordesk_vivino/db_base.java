package net.ordesk.ordesk_vivino;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
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

        BackgroundWorker backgroundWorker = new BackgroundWorker(url,params);
        try {
            json_string = backgroundWorker.execute().get();
            if (json_string==null )//|| json_string!=null)
            {
                json_string="<!DOCTYPE html>\n        <html>\n            <head>\n                <meta charset=\"utf-8\"/>\n            </head>\n            </html>{\"server_response\":[{\"Item_ID\":\"1\",\"Item_Name\":\"\\u05e7\\u05e8\\u05e4\\u05d0\\u05e6'\\u05d5\",\"Item_Price\":\"25\",\"Item_Photo_Name\":\"kg0carpatcho\",\"Item_Category\":\"\\u05e8\\u05d0\\u05e9\\u05d5\\u05e0\\u05d5\\u05ea\"},{\"Item_ID\":\"2\",\"Item_Name\":\"\\u05db\\u05d3\\u05d5\\u05e8\\u05d9 \\u05e4\\u05d9\\u05e8\\u05d4\",\"Item_Price\":\"15\",\"Item_Photo_Name\":\"kg0pire0balls\",\"Item_Category\":\"\\u05e4\\u05ea\\u05d9\\u05d7\\u05d4\"},{\"Item_ID\":\"3\",\"Item_Name\":\"\\u05d3\\u05d2 \\u05de\\u05d8\\u05d5\\u05d2\\u05df\",\"Item_Price\":\"40\",\"Item_Photo_Name\":\"kg0dag0metugan\",\"Item_Category\":\"\\u05e2\\u05d9\\u05e7\\u05e8\\u05d9\\u05d5\\u05ea\"},{\"Item_ID\":\"4\",\"Item_Name\":\"\\u05d0\\u05d2\\u05e8\\u05d5\\u05dc\",\"Item_Price\":\"15\",\"Item_Photo_Name\":\"kg0egrol\",\"Item_Category\":\"\\u05e4\\u05ea\\u05d9\\u05d7\\u05d4\"},{\"Item_ID\":\"5\",\"Item_Name\":\"\\u05e4\\u05d9\\u05dc\\u05d4 \\u05d3\\u05d2\",\"Item_Price\":\"40\",\"Item_Photo_Name\":\"kg0file0dag\",\"Item_Category\":\"\\u05e2\\u05d9\\u05e7\\u05e8\\u05d9\\u05d5\\u05ea\"},{\"Item_ID\":\"6\",\"Item_Name\":\"\\u05d2\\u05d5\\u05dc\\u05e9\",\"Item_Price\":\"40\",\"Item_Photo_Name\":\"kg0gulash\",\"Item_Category\":\"\\u05e2\\u05d9\\u05e7\\u05e8\\u05d9\\u05d5\\u05ea\"},{\"Item_ID\":\"7\",\"Item_Name\":\"\\u05d4\\u05de\\u05d1\\u05d5\\u05e8\\u05d2\\u05e8\",\"Item_Price\":\"40\",\"Item_Photo_Name\":\"kg0hamburger\",\"Item_Category\":\"\\u05e2\\u05d9\\u05e7\\u05e8\\u05d9\\u05d5\\u05ea\"},{\"Item_ID\":\"8\",\"Item_Name\":\"\\u05e6'\\u05e7\\u05df \\u05d1\\u05d5\\u05e8\\u05d2\\u05e8\",\"Item_Price\":\"40\",\"Item_Photo_Name\":\"kg0hamburger0of\",\"Item_Category\":\"\\u05e2\\u05d9\\u05e7\\u05e8\\u05d9\\u05d5\\u05ea\"},{\"Item_ID\":\"9\",\"Item_Name\":\"\\u05d7\\u05e6\\u05d9\\u05dc \\u05d1\\u05d8\\u05d7\\u05d9\\u05e0\\u05d4\",\"Item_Price\":\"25\",\"Item_Photo_Name\":\"kg0hatzil0bethina\",\"Item_Category\":\"\\u05e8\\u05d0\\u05e9\\u05d5\\u05e0\\u05d5\\u05ea\"},{\"Item_ID\":\"10\",\"Item_Name\":\"\\u05d7\\u05d6\\u05d4 \\u05e2\\u05d5\\u05e3\",\"Item_Price\":\"40\",\"Item_Photo_Name\":\"kg0haze0of\",\"Item_Category\":\"\\u05e2\\u05d9\\u05e7\\u05e8\\u05d9\\u05d5\\u05ea\"},{\"Item_ID\":\"11\",\"Item_Name\":\"\\u05d7\\u05d6\\u05d4 \\u05e2\\u05d5\\u05e3 \\u05d1\\u05e4\\u05d8\\u05e8\\u05d9\\u05d5\\u05ea\",\"Item_Price\":\"40\",\"Item_Photo_Name\":\"kg0haze0of0pitriyot\",\"Item_Category\":\"\\u05e2\\u05d9\\u05e7\\u05e8\\u05d9\\u05d5\\u05ea\"},{\"Item_ID\":\"12\",\"Item_Name\":\"\\u05d7\\u05e6\\u05d9 \\u05e2\\u05d5\\u05e3\",\"Item_Price\":\"50\",\"Item_Photo_Name\":\"kg0hetzi0of\",\"Item_Category\":\"\\u05e2\\u05d9\\u05e7\\u05e8\\u05d9\\u05d5\\u05ea\"},{\"Item_ID\":\"13\",\"Item_Name\":\"\\u05d4\\u05d5\\u05dd \\u05e4\\u05e8\\u05d9\\u05d9\\u05d6\",\"Item_Price\":\"25\",\"Item_Photo_Name\":\"kg0homefries\",\"Item_Category\":\"\\u05e8\\u05d0\\u05e9\\u05d5\\u05e0\\u05d5\\u05ea\"},{\"Item_ID\":\"14\",\"Item_Name\":\"\\u05e7\\u05d1\\u05d1\",\"Item_Price\":\"40\",\"Item_Photo_Name\":\"kg0kabab\",\"Item_Category\":\"\\u05e2\\u05d9\\u05e7\\u05e8\\u05d9\\u05d5\\u05ea\"},{\"Item_ID\":\"15\",\"Item_Name\":\"\\u05db\\u05e8\\u05d9\\u05da \\u05e2\\u05d5\\u05e3\",\"Item_Price\":\"40\",\"Item_Photo_Name\":\"kg0karih0of\",\"Item_Category\":\"\\u05e2\\u05d9\\u05e7\\u05e8\\u05d9\\u05d5\\u05ea\"},{\"Item_ID\":\"16\",\"Item_Name\":\"\\u05db\\u05e8\\u05d9\\u05da \\u05e4\\u05e8\\u05d2\\u05d9\\u05ea\",\"Item_Price\":\"40\",\"Item_Photo_Name\":\"kg0karih0pargit\",\"Item_Category\":\"\\u05e2\\u05d9\\u05e7\\u05e8\\u05d9\\u05d5\\u05ea\"},{\"Item_ID\":\"17\",\"Item_Name\":\"\\u05db\\u05e8\\u05d9\\u05da \\u05e1\\u05d9\\u05e0\\u05d8\\u05d4\",\"Item_Price\":\"40\",\"Item_Photo_Name\":\"kg0karih0sinta\",\"Item_Category\":\"\\u05e2\\u05d9\\u05e7\\u05e8\\u05d9\\u05d5\\u05ea\"},{\"Item_ID\":\"18\",\"Item_Name\":\"\\u05db\\u05d1\\u05d3 \\u05e2\\u05d5\\u05e3\",\"Item_Price\":\"40\",\"Item_Photo_Name\":\"kg0kaved0pire\",\"Item_Category\":\"\\u05e2\\u05d9\\u05e7\\u05e8\\u05d9\\u05d5\\u05ea\"},{\"Item_ID\":\"19\",\"Item_Name\":\"\\u05db\\u05e0\\u05e4\\u05d9\\u05d9\\u05dd \\u05d1\\u05d0\\u05e4\\u05d0\\u05dc\\u05d5\",\"Item_Price\":\"25\",\"Item_Photo_Name\":\"kg0knafaim0bafalo\",\"Item_Category\":\"\\u05e8\\u05d0\\u05e9\\u05d5\\u05e0\\u05d5\\u05ea\"},{\"Item_ID\":\"20\",\"Item_Name\":\"\\u05db\\u05e0\\u05e4\\u05d9\\u05d9\\u05dd \\u05d8\\u05e8\\u05d9\\u05d0\\u05e7\\u05d9\",\"Item_Price\":\"25\",\"Item_Photo_Name\":\"kg0knafaim0teriaki\",\"Item_Category\":\"\\u05e8\\u05d0\\u05e9\\u05d5\\u05e0\\u05d5\\u05ea\"},{\"Item_ID\":\"21\",\"Item_Name\":\"\\u05e7\\u05e6\\u05d9\\u05e6\\u05d5\\u05ea \\u05d1\\u05e8\\u05d5\\u05d8\\u05d1\",\"Item_Price\":\"40\",\"Item_Photo_Name\":\"kg0ktzitzot0berotev\",\"I";
                jsonObject = new JSONObject(json_string.substring(json_string.indexOf("{"), json_string.lastIndexOf("}") + 1));
                jsonArray = jsonObject.getJSONArray("server_response");
                Log.i("INFO", "Device is not connected");
            }
            jsonObject = new JSONObject(json_string.substring(json_string.indexOf("{"), json_string.lastIndexOf("}") + 1));
            jsonArray = jsonObject.getJSONArray("server_response");

            Log.i("INFO", json_string);
            Log.i("INFO", json_string.substring(json_string.indexOf("{"), json_string.lastIndexOf("}") + 1));
            write_to_app_data.writeStringToFile("jsonString.txt",json_string);

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
        JSONArray itemsArray = getJsonFromServer("http://www.ordesk.net/login.php");
        for (int i = 0; i < itemsArray.length(); i++) {
            JSONObject rec = null;
            try {
                rec = itemsArray.getJSONObject(i);
                int itemId = rec.getInt("Item_ID");
                String itemTitle = rec.getString("Item_Name");
                double itemPrice = rec.getDouble("Item_Price");
                String itemImageName = rec.getString("Item_Photo_Name");
                String itemCategory = rec.getString("Item_Category");
                itemList.add(new item(itemId,itemTitle,itemPrice,itemImageName,itemCategory));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        item[] items = new item[itemList.size()];
        itemList.toArray(items);
        return items;
    }

}
