package net.ordesk.ordesk_vivino;
import android.app.Application;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class projectGlobals extends Application{

    private int index = 0;
    db_base db_base = new db_base();
    item[] global_menu_items = db_base.importMenuItems();
    private int menu_item_num = global_menu_items.length;

    private boolean[] order_flag = new boolean[menu_item_num];
    private FrameLayout[] order_btn_fl = new FrameLayout[menu_item_num];
    private ImageButton[] add2cart_btns = new ImageButton[menu_item_num];

    public String json_string = "nothing yet";

    private LinearLayout menu_ll = null;
    final String[] menu_categories = {"פתיחה","ראשונות","פסטות","סלטים","עיקריות","קינוחים","שתיה קלה","אלכוהול"};
    private LinearLayout[] menu_ll_array = new LinearLayout[menu_categories.length];

    private String[][] dish = {{"קרפאצ'ו","כדורי פירה","דג מטוגן","אגרול","פילה דג","גולש","המבורגר","צ'קן בורגר","חציל בטחינה","חזה עוף","חזה עוף בפטריות","חצי עוף","הום פרייז","קבב","כריך עוף","כריך פרגית","כריך סינטה","כבד עוף","כנפיים באפאלו","כנפיים טריאקי","קציצות ברוטב","קובה","לחם הבית","מוס שוקולד","נתח פילה","ניוקי בטטה","ניוקי קריספי","עוף בקארי","פרגית","פסטה אווז","פסטה בקר","פסטה בולונז","פסטה חזה עוף","פסטה פיטריות","פסטה רצועות עוף","פסטה סלמון","פאי תפוחים","פורטבלו עיזים","רביולי גבינות","סלט בטטה","סלט בולגרית","סלט בורגול","סלט בורגול ופלפלים","סלט דוריטוס","סלט חלומי","סלט חלומי אגוזים","סלט חזה עוף","סלט נבטים","סלט נודלס","סלט פלפל בולגרית","סלט סביח","שווארמה ומג'דרה","שיפוד חלומי","שיפוד סלמון","נתח סינטה","סטייק","פילה סלמון","סופלה","טרמיסו","טחינה","עוגת נטיפים","עוגת גבינה","עוגת ביסקוויטים"},
                               {"25","15","40","15","40","40","40","40","25","40","40","50","25","40","40","40","40","40","25","25","40","15","15","15","40","30","15","40","40","35","35","35","35","30","35","35","15","25","35","30","30","30","30","30","35","35","35","30","30","30","35","40","15","25","50","50","50","15","15","15","15","15","15"},
                               {"kg0carpatcho","kg0pire0balls","kg0dag0metugan","kg0egrol","kg0file0dag","kg0gulash","kg0hamburger","kg0hamburger0of","kg0hatzil0bethina","kg0haze0of","kg0haze0of0pitriyot","kg0hetzi0of","kg0homefries","kg0kabab","kg0karih0of","kg0karih0pargit","kg0karih0sinta","kg0kaved0pire","kg0knafaim0bafalo","kg0knafaim0teriaki","kg0ktzitzot0berotev","kg0kube","kg0lehem0bait","kg0moos0chokolade","kg0netah0file","kg0nioki0batata","kg0nioki0krispi","kg0of0bekari","kg0pargit","kg0pene0avaz","kg0pene0bakar","kg0pene0bolonez","kg0pene0haze0of","kg0pene0pitriyot","kg0pene0retzuot0of","kg0pene0salmon","kg0pi0tapuhim","kg0portabelo","kg0ravioli0gvinot","kg0salat0batata","kg0salat0bulgarit","kg0salat0burgul","kg0salat0burgul0pilpel","kg0salat0doritos","kg0salat0halumi","kg0salat0halumi0egozim","kg0salat0haze0of","kg0salat0nevatim","kg0salat0nudels","kg0salat0pilpel0bulgarit","kg0salat0sabih","kg0shawarma","kg0shipud0halumi","kg0shipud0salmon","kg0sinta","kg0steak","kg0file0salmon","kg0sufle","kg0teramisu","kg0thina","kg0uga0katzefet","kg0uga0pasiflora","kg0ugat0biskwitim"},
                               {"ראשונות","פתיחה","עיקריות","פתיחה","עיקריות","עיקריות","עיקריות","עיקריות","ראשונות","עיקריות","עיקריות","עיקריות","ראשונות","עיקריות","עיקריות","עיקריות","עיקריות","עיקריות","ראשונות","ראשונות","עיקריות","פתיחה","פתיחה","קינוחים","עיקריות","פסטות","פתיחה","עיקריות","עיקריות","פסטות","פסטות","פסטות","פסטות","פסטות","פסטות","פסטות","קינוחים","ראשונות","פסטות","סלטים","סלטים","סלטים","סלטים","סלטים","סלטים","סלטים","סלטים","סלטים","סלטים","סלטים","סלטים","עיקריות","פתיחה","ראשונות","עיקריות","עיקריות","עיקריות","קינוחים","קינוחים","פתיחה","קינוחים","קינוחים","קינוחים"}};


    public LinearLayout getMenuLL()
    {
        return menu_ll;
    }

    public void setMenuLL(LinearLayout ll1)
    {
        menu_ll = ll1;
    }

    private FrameLayout[] order_screen_fl = new FrameLayout[1];

    public FrameLayout getOrderScrnFL()
    {
        return order_screen_fl[0];
    }

    public void setOrderScrnFL(FrameLayout i)
    {
        order_screen_fl[0] = i;
    }

    public item[] getItems() {
        return db_base.importMenuItems();
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

    public void setJsonString(String str)
    {
        json_string = str;
    }

    public String getJsonString()
    {
        return json_string;
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

    public void menu_ll_array_Init()
    {
        int i=0;
        while(i < menu_categories.length)
        {
            menu_ll_array[i] = new LinearLayout(this);
            menu_ll_array[i].setOrientation(LinearLayout.VERTICAL);
            FrameLayout.LayoutParams llp = new FrameLayout.LayoutParams(680, ViewGroup.LayoutParams.MATCH_PARENT);
            llp.setMargins(0,0,0,65);
            llp.setMarginStart(45);
            menu_ll_array[i].setLayoutParams(llp);
            i++;
        }
    }

    public void add2cart_btns_Init()
    {
        int i = 0;
        while (i < add2cart_btns.length) {
            add2cart_btns[i] = new ImageButton(this);
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

    public void payment_scrn(int sum_to_pay, View v, Window win)
    {
        FrameLayout pay_scrn_fl = (FrameLayout)((v.getParent()).getParent()).getParent();

        FrameLayout pay_window_fl0 = new FrameLayout(this);
        FrameLayout.LayoutParams pay_window_fl0_flp = new FrameLayout.LayoutParams(720,330);
        pay_window_fl0_flp.setMargins(0,120,0,0);
        pay_window_fl0_flp.setMarginStart(70);
        pay_window_fl0.setLayoutParams(pay_window_fl0_flp);
        pay_window_fl0.setBackgroundColor(0xFFafabab);

        FrameLayout pay_window_full_scrn_fl = new FrameLayout(this);
        FrameLayout.LayoutParams full_scrn_flp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT);
        pay_window_full_scrn_fl.setLayoutParams(full_scrn_flp);
        pay_window_full_scrn_fl.setBackgroundColor(0xc0000000);
        pay_window_full_scrn_fl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FrameLayout pay_scrn_fl = (FrameLayout)v.getParent();
                pay_scrn_fl.removeView(v);
            }
        });

        FrameLayout pay_window_fl1 = new FrameLayout(this);
        FrameLayout.LayoutParams pay_window_fl1_flp = new FrameLayout.LayoutParams(700,470);
        pay_window_fl1_flp.setMargins(0,10,0,0);
        pay_window_fl1_flp.setMarginStart(10);
        pay_window_fl1.setLayoutParams(pay_window_fl1_flp);

        TextView tv = new TextView(this);
        FrameLayout.LayoutParams tv_flp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.WRAP_CONTENT);
        tv_flp.setMargins(0,50,0,0);
        tv_flp.setMarginStart(30);
        tv.setLayoutParams(tv_flp);
        tv.setText("נא להעביר כרטיס אשראי");
        tv.setTextColor(0xff000000);
        tv.setTextSize(50);
        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        final EditText pay_et = new EditText(this);
        pay_et.setTransformationMethod(PasswordTransformationMethod.getInstance());
        pay_et.setTextSize(20);
        FrameLayout.LayoutParams pay_et_flp = new FrameLayout.LayoutParams(400,50);
        pay_et_flp.setMargins(0,180,0,0);
        pay_et_flp.setMarginStart(160);
        pay_et.setLayoutParams(pay_et_flp);
        pay_et.setBackgroundColor(0xffffffff);
        pay_et.setTextColor(0xff000000);
        pay_et.setClickable(false);
        pay_et.requestFocus();
        pay_et.setCursorVisible(true);

        final StringBuilder sb = new StringBuilder();

        pay_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
                if (sb.length()>0)
                {
                    sb.deleteCharAt(0);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if (s.charAt(s.length()-1)=='\n')
                {
                    Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                    pay_et.clearFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s)
            {
                if(sb.length()==0)
                {
                    pay_et.requestFocus();
                }
            }
        });

        pay_window_fl1.addView(tv);
        pay_window_fl1.addView(pay_et);
        pay_window_fl0.addView(pay_window_fl1);
        pay_window_full_scrn_fl.addView(pay_window_fl0);
        pay_scrn_fl.addView(pay_window_full_scrn_fl);
    }

    public void build_cat_ll()
    {
        int k=0;
        int i,count;
        int[] cat_indx = new int[menu_item_num];

        while(k < menu_categories.length) {
            String category = menu_categories[k];
            i = 0;
            count = 0;

            while (i < global_menu_items.length) {
                if (global_menu_items[i].getCategory().equals(category)) {
                    cat_indx[count] = i;
                    count++;
                }
                i++;
            }
            cat_indx[count] = -1;

            int j = 0;
            while (cat_indx[j] != -1) {
                LinearLayout dish_linlay = new LinearLayout(this);
                LinearLayout.LayoutParams dish_linlay_llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                dish_linlay.setOrientation(LinearLayout.HORIZONTAL);
                dish_linlay.setLayoutParams(dish_linlay_llp);
                dish_linlay.setPadding(25, 10, 0, 0);
                dish_linlay.setBackgroundColor(0x00000000);
                dish_linlay.invalidate();

                //-----------------------------------------  Create Left Item  ---------------------------------------------//

                FrameLayout dish1_fl = new FrameLayout(this);
                FrameLayout.LayoutParams dish1_fl_flp = new FrameLayout.LayoutParams(505, 135);
                dish1_fl_flp.setMargins(0, 20, 0, 10);
                dish1_fl_flp.setMarginStart(70);
                dish1_fl.setLayoutParams(dish1_fl_flp);
                dish1_fl.setBackgroundColor(0xFF666666);

                FrameLayout dish1_fl2 = new FrameLayout(this);
                FrameLayout.LayoutParams dish1_fl2_flp = new FrameLayout.LayoutParams(495, 125);
                dish1_fl2_flp.setMargins(0, 5, 0, 0);
                dish1_fl2_flp.setMarginStart(5);
                dish1_fl2.setLayoutParams(dish1_fl2_flp);
                dish1_fl2.setBackgroundColor(0xFFadadad);

                LinearLayout ll1 = new LinearLayout(this);
                LinearLayout.LayoutParams ll1_llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                ll1.setOrientation(LinearLayout.HORIZONTAL);
                ll1.setLayoutParams(ll1_llp);

                LinearLayout ll2 = new LinearLayout(this);
                LinearLayout.LayoutParams ll2_llp = new LinearLayout.LayoutParams(300, LinearLayout.LayoutParams.MATCH_PARENT);
                ll2.setOrientation(LinearLayout.VERTICAL);
                ll2.setLayoutParams(ll2_llp);

                TextView tv1 = new TextView(this);
                tv1.setText(dish[0][cat_indx[j]]);
                tv1.setTextSize(20);
                tv1.setTextColor(0xFF8C5111);
                tv1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                tv1.setTypeface(Typeface.DEFAULT_BOLD);
                LinearLayout.LayoutParams tv1_llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 65);
                tv1.setLayoutParams(tv1_llp);
                tv1.setPadding(0, 0, 0, 0);

                LinearLayout ll3 = new LinearLayout(this);
                LinearLayout.LayoutParams ll3_llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                ll3.setOrientation(LinearLayout.HORIZONTAL);
                ll3_llp.setMargins(5, 15, 0, 0);
                ll3.setLayoutParams(ll3_llp);

                TextView tv2 = new TextView(this);
                tv2.setText("מחיר: " + dish[1][cat_indx[j]] + "שח");
                tv2.setTextSize(17);
                tv2.setTextColor(0xFF000000);
                tv2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                //tv2.setTypeface(Typeface.DEFAULT_BOLD);
                LinearLayout.LayoutParams tv2_llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                tv2.setLayoutParams(tv2_llp);
                tv2.setPadding(0, 0, 0, 0);

                FrameLayout order_btn_fl = getOrderBtnFl(cat_indx[j]);
                FrameLayout.LayoutParams order_btn_flp = new FrameLayout.LayoutParams(40, 40);
                order_btn_flp.setMargins(0, 15, 0, 0);
                order_btn_flp.setMarginStart(6);
                order_btn_fl.setLayoutParams(order_btn_flp);
                order_btn_fl.setBackgroundColor(0xFF9f9f9f);
                setOrderBtnFl(order_btn_fl, cat_indx[j]);

                if (getOrder(cat_indx[j])) {
                    add2cart_btns[cat_indx[j]].setBackgroundResource(R.drawable.add2cartbtnpush);
                    add2cart_btns[cat_indx[j]].invalidate();
                } else {
                    add2cart_btns[cat_indx[j]].setBackgroundResource(R.drawable.add2cartbtnnopush);
                    add2cart_btns[cat_indx[j]].invalidate();
                }

                add2cart_btns[cat_indx[j]].setId(cat_indx[j]);
                add2cart_btns[cat_indx[j]].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (getOrder(v.getId())) {
                            v.setBackgroundResource(R.drawable.add2cartbtnnopush);
                            v.invalidate();
                            setOrder(v.getId(), false);
                            Toast.makeText(getApplicationContext(), getDish(0, v.getId()) + " הוסר מההזמנה ", Toast.LENGTH_SHORT).show();
                        } else {
                            v.setBackgroundResource(R.drawable.add2cartbtnpush);
                            v.invalidate();
                            setOrder(v.getId(), true);
                            Toast.makeText(getApplicationContext(), getDish(0, v.getId()) + " הוסף להזמנה ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                FrameLayout dish_img_fl = new FrameLayout(this);
                FrameLayout.LayoutParams dish_img_flp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
                dish_img_fl.setLayoutParams(dish_img_flp);

                ImageView dish1_iv2 = new ImageView(this);
                FrameLayout.LayoutParams dish1_iv2_flp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
                dish1_iv2.setLayoutParams(dish1_iv2_flp);

                String imageId = dish[2][cat_indx[j]];
                int resID = getResources().getIdentifier(imageId, "drawable", getPackageName());
                dish1_iv2.setBackgroundResource(resID);

                Button lrg_img_btn2 = new Button(this);
                lrg_img_btn2.setBackgroundColor(0x00000000);
                lrg_img_btn2.setTextColor(0x00000000);
                FrameLayout.LayoutParams lrg_img_btn2_flp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
                lrg_img_btn2.setLayoutParams(lrg_img_btn2_flp);

                menu_item img_tag = new menu_item();
                img_tag.setItemName(dish[2][cat_indx[j]]);
                lrg_img_btn2.setTag(img_tag);

                lrg_img_btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        menu_item img_tag = (menu_item) v.getTag();
                        String imgName = img_tag.getItemName();
                        lrg_img(imgName);
                    }
                });

                order_btn_fl.addView(add2cart_btns[cat_indx[j]]);
                //ll3.addView(order_btn_fl, 0);
                ll3.addView(tv2, 0);// if using the order buttons (the line above) change 0 -> 1
                ll2.addView(tv1, 0);
                ll2.addView(ll3, 1);
                ll1.addView(ll2, 0);
                dish_img_fl.addView(dish1_iv2);
                dish_img_fl.addView(lrg_img_btn2);
                ll1.addView(dish_img_fl, 1);
                dish1_fl2.addView(ll1);
                dish1_fl.addView(dish1_fl2);
                dish_linlay.addView(dish1_fl);
                menu_ll_array[k].addView(dish_linlay);
                j++;
            }
            k++;
        }

    }

    public LinearLayout show_cat_ll(View v)
    {
        String category = ((item)v.getTag()).getTitle();
        int k=0;

        while(k < menu_categories.length)
        {
            if (category==menu_categories[k])
            {
                return menu_ll_array[k];
            }
            k++;
        }
        return null;
    }

}