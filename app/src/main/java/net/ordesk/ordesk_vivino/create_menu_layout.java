package net.ordesk.ordesk_vivino;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by USER1 on 16-Nov-16.
 */

public class create_menu_layout extends Application
{
    public LinearLayout build_menu_layout()
    {
        final projectGlobals globalVariable = new projectGlobals();
        final menu_item menu_item_objects = new menu_item();
        int itemNum = globalVariable.getItemNum();
        globalVariable.OrderBtnFlInit();

        final String[] cat = globalVariable.getCategories();
        final String[][] dish = globalVariable.getDishArr();
        //final item[] items = globalVariable.getItems();
        //int dish_num = items.length;
        int dish_num = globalVariable.getItemNum();

        ImageButton[] add2cart_btns = new ImageButton[itemNum];
        int i1 = 0;
        while (i1 < add2cart_btns.length) {
            add2cart_btns[i1] = new ImageButton(this);
            i1++;
        }

        LinearLayout linlay = new LinearLayout(this);
        linlay.setBackgroundColor(0x00000000);
        linlay.invalidate();

        int i = 0, j;

        while (i < 1/*cat.length*/) {
            LinearLayout linlay1 = new LinearLayout(this);
            linlay1.setOrientation(LinearLayout.HORIZONTAL);
            ImageView iv1 = new ImageView(this);
            ImageView iv2 = new ImageView(this);
            TextView tv = new TextView(this);
            iv1.setBackgroundResource(R.drawable.sideiconleft);
            iv2.setBackgroundResource(R.drawable.sideiconright);
            tv.setText(cat[i]);
            tv.setTextSize(35);
            tv.setTextColor(0xFF000000);
            tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tv.setTypeface(Typeface.DEFAULT_BOLD);
            LinearLayout.LayoutParams tv_llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            LinearLayout.LayoutParams iv1_llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            LinearLayout.LayoutParams iv2_llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            iv1.setLayoutParams(iv1_llp);
            tv_llp.setMargins(0, 10, 0, 10);
            tv_llp.setMarginStart(10);
            tv.setLayoutParams(tv_llp);
            tv.measure(0,0);
            iv1.measure(0,0);
            iv1_llp.setMargins(0,35, 0,0);
            iv1_llp.setMarginStart(380-iv1.getMeasuredWidth()-(tv.getMeasuredWidth())/2);
            iv2_llp.setMargins(0, 35, 0, 0);
            iv2_llp.setMarginStart(10);
            iv1.setLayoutParams(iv1_llp);
            iv2.setLayoutParams(iv2_llp);
            linlay1.addView(iv1);
            linlay1.addView(tv);
            linlay1.addView(iv2);
            linlay.addView(linlay1);

            j=0;
            while (j < dish_num) {
                if (cat[i]==dish[3][j]) {
                    LinearLayout dish_linlay = new LinearLayout(this);
                    LinearLayout.LayoutParams dish_linlay_llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    dish_linlay.setOrientation(LinearLayout.HORIZONTAL);
                    dish_linlay.setLayoutParams(dish_linlay_llp);
                    dish_linlay.setPadding(25, 10, 0, 0);
                    dish_linlay.setBackgroundColor(0x00000000);
                    dish_linlay.invalidate();

                    //-----------------------------------------  Create Left Item  ---------------------------------------------//

                    FrameLayout dish1_fl = new FrameLayout(this);
                    FrameLayout.LayoutParams dish1_fl_flp = new FrameLayout.LayoutParams(355, 135);
                    dish1_fl_flp.setMargins(0, 20, 0, 0);
                    dish1_fl_flp.setMarginStart(25);
                    dish1_fl.setLayoutParams(dish1_fl_flp);
                    dish1_fl.setBackgroundColor(0xFFafabab);

                    FrameLayout dish1_fl2 = new FrameLayout(this);
                    FrameLayout.LayoutParams dish1_fl2_flp = new FrameLayout.LayoutParams(345, 125);
                    dish1_fl2_flp.setMargins(0, 5, 0, 0);
                    dish1_fl2_flp.setMarginStart(5);
                    dish1_fl2.setLayoutParams(dish1_fl2_flp);
                    dish1_fl2.setBackgroundColor(0xFFffffff);

                    LinearLayout ll1 = new LinearLayout(this);
                    LinearLayout.LayoutParams ll1_llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                    ll1.setOrientation(LinearLayout.HORIZONTAL);
                    ll1.setLayoutParams(ll1_llp);

                    LinearLayout ll2 = new LinearLayout(this);
                    LinearLayout.LayoutParams ll2_llp = new LinearLayout.LayoutParams(200, LinearLayout.LayoutParams.MATCH_PARENT);
                    ll2.setOrientation(LinearLayout.VERTICAL);
                    ll2.setLayoutParams(ll2_llp);

                    TextView tv1 = new TextView(this);
                    tv1.setText(dish[0][j]);
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
                    tv2.setText("מחיר: " + dish[1][j] + "שח");
                    tv2.setTextSize(17);
                    tv2.setTextColor(0xFF000000);
                    tv2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    //tv2.setTypeface(Typeface.DEFAULT_BOLD);
                    LinearLayout.LayoutParams tv2_llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    tv2.setLayoutParams(tv2_llp);
                    tv2.setPadding(0, 0, 0, 0);

                    FrameLayout order_btn_fl = globalVariable.getOrderBtnFl(j);
                    FrameLayout.LayoutParams order_btn_flp = new FrameLayout.LayoutParams(40, 40);
                    order_btn_flp.setMargins(0, 15, 0, 0);
                    order_btn_flp.setMarginStart(6);
                    order_btn_fl.setLayoutParams(order_btn_flp);
                    order_btn_fl.setBackgroundColor(0xFF9f9f9f);
                    globalVariable.setOrderBtnFl(order_btn_fl, j);

                    if (globalVariable.getOrder(j)) {
                        add2cart_btns[j].setBackgroundResource(R.drawable.add2cartbtnpush);
                        add2cart_btns[j].invalidate();
                    } else {
                        add2cart_btns[j].setBackgroundResource(R.drawable.add2cartbtnnopush);
                        add2cart_btns[j].invalidate();
                    }

                    add2cart_btns[j].setId(j);
                    add2cart_btns[j].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (globalVariable.getOrder(v.getId())) {
                                v.setBackgroundResource(R.drawable.add2cartbtnnopush);
                                v.invalidate();
                                globalVariable.setOrder(v.getId(), false);
                                Toast.makeText(getApplicationContext(), globalVariable.getDish(0, v.getId()) + " הוסר מההזמנה ", Toast.LENGTH_SHORT).show();
                            } else {
                                v.setBackgroundResource(R.drawable.add2cartbtnpush);
                                v.invalidate();
                                globalVariable.setOrder(v.getId(), true);
                                Toast.makeText(getApplicationContext(), globalVariable.getDish(0, v.getId()) + " הוסף להזמנה ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    FrameLayout dish_img_fl = new FrameLayout(this);
                    FrameLayout.LayoutParams dish_img_flp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT);
                    dish_img_fl.setLayoutParams(dish_img_flp);

                    ImageView dish1_iv2 = new ImageView(this);
                    FrameLayout.LayoutParams dish1_iv2_flp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
                    dish1_iv2.setLayoutParams(dish1_iv2_flp);

                    String imageId = dish[2][j];
                    int resID = getResources().getIdentifier(imageId, "drawable", getPackageName());
                    dish1_iv2.setBackgroundResource(resID);

                    Button lrg_img_btn2 = new Button(this);
                    lrg_img_btn2.setBackgroundColor(0x00000000);
                    lrg_img_btn2.setTextColor(0x00000000);
                    FrameLayout.LayoutParams lrg_img_btn2_flp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
                    lrg_img_btn2.setLayoutParams(lrg_img_btn2_flp);

                    menu_item img_tag = new menu_item();
                    img_tag.setItemName(dish[2][j]);
                    lrg_img_btn2.setTag(img_tag);

                    lrg_img_btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            menu_item img_tag = (menu_item) v.getTag();
                            String imgName = img_tag.getItemName();
                            globalVariable.lrg_img(imgName);
                        }
                    });

                    order_btn_fl.addView(add2cart_btns[j]);
                    ll3.addView(order_btn_fl, 0);
                    ll3.addView(tv2, 1);
                    ll2.addView(tv1, 0);
                    ll2.addView(ll3, 1);
                    ll1.addView(ll2, 0);
                    dish_img_fl.addView(dish1_iv2);
                    dish_img_fl.addView(lrg_img_btn2);
                    ll1.addView(dish_img_fl, 1);
                    dish1_fl2.addView(ll1);
                    dish1_fl.addView(dish1_fl2);
                    dish_linlay.addView(dish1_fl);
                    j = j + 1;

//-----------------------------------------  End Of Left Item  ---------------------------------------------//


//-----------------------------------------  Create Right Item  ---------------------------------------------//
                    while (j < dish_num){
                        if (cat[i]==dish[3][j]){
                            break;
                        }
                        else {
                            j++;
                        }
                    }
                    if (j < dish_num) {
                        FrameLayout dish2_fl0 = new FrameLayout(this);
                        FrameLayout.LayoutParams dish2_fl0_flp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
                        dish2_fl0_flp.setMargins(25, 0, 0, 0);
                        //dish2_fl0_flp.setMarginStart(5);
                        dish2_fl0.setLayoutParams(dish2_fl0_flp);
                        dish2_fl0.setBackgroundColor(0x00ffffff);

                        FrameLayout dish2_fl = new FrameLayout(this);
                        FrameLayout.LayoutParams dish2_fl_flp = new FrameLayout.LayoutParams(355, 135);
                        dish2_fl_flp.setMargins(0, 0, 0, 0);
                        dish2_fl_flp.setMarginStart(25);
                        dish2_fl.setLayoutParams(dish2_fl_flp);
                        dish2_fl.setBackgroundColor(0xFFafabab);

                        FrameLayout dish2_fl2 = new FrameLayout(this);
                        FrameLayout.LayoutParams dish2_fl2_flp = new FrameLayout.LayoutParams(345, 125);
                        dish2_fl2_flp.setMargins(0, 5, 0, 0);
                        dish2_fl2_flp.setMarginStart(5);
                        dish2_fl2.setLayoutParams(dish2_fl2_flp);
                        dish2_fl2.setBackgroundColor(0xFFffffff);

                        LinearLayout dish2_ll1 = new LinearLayout(this);
                        LinearLayout.LayoutParams dish2_ll1_llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                        dish2_ll1.setOrientation(LinearLayout.HORIZONTAL);
                        dish2_ll1.setLayoutParams(dish2_ll1_llp);

                        LinearLayout dish2_ll2 = new LinearLayout(this);
                        LinearLayout.LayoutParams dish2_ll2_llp = new LinearLayout.LayoutParams(200, LinearLayout.LayoutParams.MATCH_PARENT);
                        dish2_ll2.setOrientation(LinearLayout.VERTICAL);
                        dish2_ll2.setLayoutParams(dish2_ll2_llp);

                        TextView dish2_tv1 = new TextView(this);
                        dish2_tv1.setText(dish[0][j]);
                        dish2_tv1.setTextSize(20);
                        dish2_tv1.setTextColor(0xFF8C5111);
                        dish2_tv1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        dish2_tv1.setTypeface(Typeface.DEFAULT_BOLD);
                        LinearLayout.LayoutParams dish2_tv1_llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 65);
                        dish2_tv1.setLayoutParams(dish2_tv1_llp);
                        dish2_tv1.setPadding(0, 0, 0, 0);

                        LinearLayout dish2_ll3 = new LinearLayout(this);
                        LinearLayout.LayoutParams dish2_ll3_llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                        dish2_ll3.setOrientation(LinearLayout.HORIZONTAL);
                        dish2_ll3_llp.setMargins(5, 15, 0, 0);
                        dish2_ll3.setLayoutParams(dish2_ll3_llp);

                        TextView dish2_tv2 = new TextView(this);
                        dish2_tv2.setText("מחיר: " + dish[1][j] + "שח");
                        dish2_tv2.setTextSize(17);
                        dish2_tv2.setTextColor(0xFF000000);
                        dish2_tv2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        //tv2.setTypeface(Typeface.DEFAULT_BOLD);
                        LinearLayout.LayoutParams dish2_tv2_llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        dish2_tv2.setLayoutParams(dish2_tv2_llp);
                        dish2_tv2.setPadding(0, 0, 0, 0);

                        FrameLayout dish2_order_btn_fl = globalVariable.getOrderBtnFl(j);
                        FrameLayout.LayoutParams dish2_order_btn_flp = new FrameLayout.LayoutParams(40, 40);
                        dish2_order_btn_flp.setMargins(0, 35, 0, 0);
                        dish2_order_btn_flp.setMarginStart(6);
                        dish2_order_btn_fl.setLayoutParams(dish2_order_btn_flp);
                        dish2_order_btn_fl.setBackgroundColor(0xFF9f9f9f);
                        globalVariable.setOrderBtnFl(dish2_order_btn_fl, j);

                        if (globalVariable.getOrder(j)) {
                            add2cart_btns[j].setBackgroundResource(R.drawable.add2cartbtnpush);
                            add2cart_btns[j].invalidate();
                        } else {
                            add2cart_btns[j].setBackgroundResource(R.drawable.add2cartbtnnopush);
                            add2cart_btns[j].invalidate();
                        }

                        add2cart_btns[j].setId(j);
                        add2cart_btns[j].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (globalVariable.getOrder(v.getId())) {
                                    v.setBackgroundResource(R.drawable.add2cartbtnnopush);
                                    v.invalidate();
                                    globalVariable.setOrder(v.getId(), false);
                                    Toast.makeText(getApplicationContext(), globalVariable.getDish(0, v.getId()) + " הוסר מההזמנה ", Toast.LENGTH_SHORT).show();
                                } else {
                                    v.setBackgroundResource(R.drawable.add2cartbtnpush);
                                    v.invalidate();
                                    globalVariable.setOrder(v.getId(), true);
                                    Toast.makeText(getApplicationContext(), globalVariable.getDish(0, v.getId()) + " הוסף להזמנה ", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        FrameLayout dish2_img_fl = new FrameLayout(this);
                        FrameLayout.LayoutParams dish2_img_flp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT);
                        dish2_img_fl.setLayoutParams(dish2_img_flp);

                        ImageView dish2_iv2 = new ImageView(this);
                        FrameLayout.LayoutParams dish2_iv2_flp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
                        dish2_iv2.setLayoutParams(dish2_iv2_flp);

                        String image2Id = dish[2][j];
                        int res2ID = getResources().getIdentifier(image2Id, "drawable", getPackageName());
                        dish2_iv2.setBackgroundResource(res2ID);

                        Button lrg_img_btn = new Button(this);
                        lrg_img_btn.setBackgroundColor(0x00000000);
                        lrg_img_btn.setTextColor(0x00000000);
                        FrameLayout.LayoutParams lrg_img_btn_flp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
                        lrg_img_btn.setLayoutParams(lrg_img_btn_flp);

                        menu_item img_tag2 = new menu_item();
                        img_tag2.setItemName(dish[2][j]);
                        lrg_img_btn.setTag(img_tag2);

                        lrg_img_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                menu_item img_tag = (menu_item) v.getTag();
                                String imgName = img_tag.getItemName();
                                globalVariable.lrg_img(imgName);
                            }
                        });

                        dish2_order_btn_fl.addView(add2cart_btns[j]);
                        dish2_ll3.addView(dish2_order_btn_fl, 0);
                        dish2_ll3.addView(dish2_tv2, 1);
                        dish2_ll2.addView(dish2_tv1, 0);
                        dish2_ll2.addView(dish2_ll3, 1);
                        dish2_ll1.addView(dish2_ll2, 0);
                        dish2_img_fl.addView(dish2_iv2);
                        dish2_img_fl.addView(lrg_img_btn);
                        dish2_ll1.addView(dish2_img_fl, 1);
                        dish2_fl2.addView(dish2_ll1);
                        dish2_fl.addView(dish2_fl2);
                        dish2_fl0.addView(dish2_fl);
                        dish_linlay.addView(dish2_fl0);
                        dish_linlay.setBackgroundColor(0x00000000);
                        dish_linlay.invalidate();
                        j = j + 1;
                    }
                    linlay.addView(dish_linlay);
//-----------------------------------------  End Of Left Item  ---------------------------------------------//
                }
                else{
                    j++;
                }
            }

            FrameLayout fl1 = new FrameLayout(this);
            FrameLayout.LayoutParams fl1_flp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, 50);
            fl1.setLayoutParams(fl1_flp);
            linlay.addView(fl1);
            i++;
        }
        return linlay;
    }






}
