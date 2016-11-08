package net.ordesk.ordesk_vivino;

import android.annotation.SuppressLint;
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
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class order_screen extends AppCompatActivity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;
    //private boolean order_flag[] = {true,true};

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 0;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 0;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
   // private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.hide();
            }
          //  mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
           /* if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }*/
            return false;
        }
    };

    @Override
    public void onBackPressed() {
        // nothing to do here
        // … really
    }

//------------------------------------------------------------------------------//
//------------------------------- onCreate Start -------------------------------//
//------------------------------------------------------------------------------//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        delayedHide(0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_screen);
        mVisible = true;
        mContentView = findViewById(R.id.order_framelayout);
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        final projectGlobals globalVariable = (projectGlobals)getApplicationContext();
        final menu_item menu_item_objects = new menu_item();
        int itemNum = globalVariable.getItemNum();
        globalVariable.OrderBtnFlInit();
        globalVariable.setOrderScrnFL((FrameLayout)findViewById(R.id.order_framelayout));

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

            LinearLayout linlay = (LinearLayout) findViewById(R.id.menu_linear_layout);
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
        /*
        findViewById(R.id.ext_lrg).setVisibility(findViewById(R.id.ext_lrg).INVISIBLE);
        Button exit_lrg = (Button)findViewById(R.id.ext_lrg);

        exit_lrg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.ext_lrg).setVisibility(findViewById(R.id.ext_lrg).INVISIBLE);
                findViewById(R.id.itm11_lrg).setVisibility(findViewById(R.id.itm11_lrg).INVISIBLE);
                findViewById(R.id.itm12_lrg).setVisibility(findViewById(R.id.itm12_lrg).INVISIBLE);
                findViewById(R.id.itm13_lrg).setVisibility(findViewById(R.id.itm13_lrg).INVISIBLE);
                findViewById(R.id.itm14_lrg).setVisibility(findViewById(R.id.itm14_lrg).INVISIBLE);
            }
        });
*/
//--------------------------- MenuBar Definitions -----------------------------//

        Button backbtn = (Button)findViewById(R.id.imageButton_back);
        ImageButton paybtn = (ImageButton)findViewById(R.id.order_pay_imageButton);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), welcome_info.class);
                startActivity(nextScreen);
            }
        });

        paybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), pay_screen.class);
                startActivity(nextScreen);
            }
        });

//--------------------------- ITEM 11 Definitions -----------------------------//
/*

        //---------------- ITEM 11 Frames -----------------//
        findViewById(R.id.itm11_lrg).setVisibility(findViewById(R.id.itm11_lrg).INVISIBLE);

        FrameLayout p11 =(FrameLayout)findViewById(R.id.frmlout11);
        if (!globalVariable.getOrder(1)){
            p11.setBackgroundColor(0xFF9F9F9F);
            p11.invalidate();}
        else{
            p11.setBackgroundColor(0xFF00FF00);
            p11.invalidate();}

        //---------------- ITEM 11 Buttons -----------------//
        Button addToCartButton11 = (Button)findViewById(R.id.nemu_item_addToCartButton11);
        Button itm11_Large_btn = (Button)findViewById(R.id.itm11_lrg_btn);
        Button itm11_Large_ext_btn = (Button)findViewById(R.id.ext_itm11_lrg_btn);

        //---------------- ITEM 11 OnClockListeners -----------------//
        itm11_Large_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                findViewById(R.id.ext_lrg).setVisibility(findViewById(R.id.ext_lrg).VISIBLE);
                findViewById(R.id.itm11_lrg).setVisibility(findViewById(R.id.itm11_lrg).VISIBLE);
            }
        });

        itm11_Large_ext_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                findViewById(R.id.itm11_lrg).setVisibility(findViewById(R.id.itm11_lrg).INVISIBLE);
                findViewById(R.id.ext_lrg).setVisibility(findViewById(R.id.ext_lrg).INVISIBLE);
            }
        });

        addToCartButton11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FrameLayout p1 =(FrameLayout)findViewById(R.id.frmlout11);
                if (globalVariable.getOrder(1)){
                    p1.setBackgroundColor(0xFF9F9F9F);
                    p1.invalidate();
                    globalVariable.setOrder(1,false);
                    Toast.makeText(getApplicationContext(), findViewById(R.id.item11_desc).getContentDescription()+" הוסר מההזמנה ", Toast.LENGTH_SHORT).show();}
                else{
                    p1.setBackgroundColor(0xFF00FF00);
                    p1.invalidate();
                    globalVariable.setOrder(1,true);
                    Toast.makeText(getApplicationContext(), findViewById(R.id.item11_desc).getContentDescription()+" הוסף להזמנה ", Toast.LENGTH_SHORT).show();}
            }
        });

//--------------------------- ITEM 12 Definitions -----------------------------//

        //---------------- ITEM 12 Frames -----------------//
        findViewById(R.id.itm12_lrg).setVisibility(findViewById(R.id.itm12_lrg).INVISIBLE);

        FrameLayout p12 =(FrameLayout)findViewById(R.id.frmlout12);
        if (!globalVariable.getOrder(2)){
            p12.setBackgroundColor(0xFF9F9F9F);
            p12.invalidate();}
        else{
            p12.setBackgroundColor(0xFF00FF00);
            p12.invalidate();}

        //---------------- ITEM 12 Buttons -----------------//
        final Button addToCartButton12 = (Button)findViewById(R.id.nemu_item_addToCartButton12);
        Button itm12_Large_btn = (Button)findViewById(R.id.itm12_lrg_btn);
        Button itm12_Large_ext_btn = (Button)findViewById(R.id.ext_itm12_lrg_btn);

        //---------------- ITEM 12 OnClockListeners -----------------//
        itm12_Large_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                findViewById(R.id.itm12_lrg).setVisibility(findViewById(R.id.itm12_lrg).VISIBLE);
                findViewById(R.id.ext_lrg).setVisibility(findViewById(R.id.ext_lrg).VISIBLE);
            }
        });

        itm12_Large_ext_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                findViewById(R.id.itm12_lrg).setVisibility(findViewById(R.id.itm12_lrg).INVISIBLE);
                findViewById(R.id.ext_lrg).setVisibility(findViewById(R.id.ext_lrg).INVISIBLE);
            }
        });

        addToCartButton12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FrameLayout p2 =(FrameLayout)findViewById(R.id.frmlout12);
                if (globalVariable.getOrder(2)){
                    p2.setBackgroundColor(0xFF9F9F9F);
                    p2.invalidate();
                    globalVariable.setOrder(2,false);
                    Toast.makeText(getApplicationContext(), findViewById(R.id.item12_desc).getContentDescription()+" הוסר מההזמנה ", Toast.LENGTH_SHORT).show();}
                else{
                    p2.setBackgroundColor(0xFF00FF00);
                    p2.invalidate();
                    globalVariable.setOrder(2,true);
                    Toast.makeText(getApplicationContext(), findViewById(R.id.item12_desc).getContentDescription()+" הוסף להזמנה ", Toast.LENGTH_SHORT).show();}
            }
        });

//--------------------------- ITEM 13 Definitions -----------------------------//


        //---------------- ITEM 13 Frames -----------------//
        findViewById(R.id.itm13_lrg).setVisibility(findViewById(R.id.itm13_lrg).INVISIBLE);

        FrameLayout p13 =(FrameLayout)findViewById(R.id.frmlout13);
        if (!globalVariable.getOrder(3)){
            p13.setBackgroundColor(0xFF9F9F9F);
            p13.invalidate();}
        else{
            p13.setBackgroundColor(0xFF00FF00);
            p13.invalidate();}

        //---------------- ITEM 11 Buttons -----------------//
        Button addToCartButton13 = (Button)findViewById(R.id.nemu_item_addToCartButton13);
        Button itm13_Large_btn = (Button)findViewById(R.id.itm13_lrg_btn);
        Button itm13_Large_ext_btn = (Button)findViewById(R.id.ext_itm13_lrg_btn);

        //---------------- ITEM 11 OnClockListeners -----------------//
        itm13_Large_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                findViewById(R.id.itm13_lrg).setVisibility(findViewById(R.id.itm13_lrg).VISIBLE);
                findViewById(R.id.ext_lrg).setVisibility(findViewById(R.id.ext_lrg).VISIBLE);
            }
        });

        itm13_Large_ext_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                findViewById(R.id.itm13_lrg).setVisibility(findViewById(R.id.itm13_lrg).INVISIBLE);
                findViewById(R.id.ext_lrg).setVisibility(findViewById(R.id.ext_lrg).INVISIBLE);
            }
        });

        addToCartButton13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FrameLayout p1 =(FrameLayout)findViewById(R.id.frmlout13);
                if (globalVariable.getOrder(3)){
                    p1.setBackgroundColor(0xFF9F9F9F);
                    p1.invalidate();
                    globalVariable.setOrder(3,false);
                    Toast.makeText(getApplicationContext(), findViewById(R.id.item13_desc).getContentDescription()+" הוסר מההזמנה ", Toast.LENGTH_SHORT).show();}
                else{
                    p1.setBackgroundColor(0xFF00FF00);
                    p1.invalidate();
                    globalVariable.setOrder(3,true);
                    Toast.makeText(getApplicationContext(), findViewById(R.id.item13_desc).getContentDescription()+" הוסף להזמנה ", Toast.LENGTH_SHORT).show();}
            }
        });

//--------------------------- ITEM 14 Definitions -----------------------------//

        //---------------- ITEM 14 Frames -----------------//
        findViewById(R.id.itm14_lrg).setVisibility(findViewById(R.id.itm14_lrg).INVISIBLE);

        FrameLayout p14 =(FrameLayout)findViewById(R.id.frmlout14);
        if (!globalVariable.getOrder(4)){
            p14.setBackgroundColor(0xFF9F9F9F);
            p14.invalidate();}
        else{
            p14.setBackgroundColor(0xFF00FF00);
            p14.invalidate();}

        //---------------- ITEM 12 Buttons -----------------//
        final Button addToCartButton14 = (Button)findViewById(R.id.nemu_item_addToCartButton14);
        Button itm14_Large_btn = (Button)findViewById(R.id.itm14_lrg_btn);
        Button itm14_Large_ext_btn = (Button)findViewById(R.id.ext_itm14_lrg_btn);

        //---------------- ITEM 12 OnClockListeners -----------------//
        itm14_Large_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                findViewById(R.id.ext_lrg).setVisibility(findViewById(R.id.ext_lrg).VISIBLE);
                findViewById(R.id.itm14_lrg).setVisibility(findViewById(R.id.itm14_lrg).VISIBLE);
            }
        });

        itm14_Large_ext_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                findViewById(R.id.itm14_lrg).setVisibility(findViewById(R.id.itm14_lrg).INVISIBLE);
                findViewById(R.id.ext_lrg).setVisibility(findViewById(R.id.ext_lrg).INVISIBLE);
            }
        });

        addToCartButton14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FrameLayout p2 =(FrameLayout)findViewById(R.id.frmlout14);
                if (globalVariable.getOrder(4)){
                    p2.setBackgroundColor(0xFF9F9F9F);
                    p2.invalidate();
                    globalVariable.setOrder(4,false);
                    Toast.makeText(getApplicationContext(), findViewById(R.id.item14_desc).getContentDescription()+" הוסר מההזמנה ", Toast.LENGTH_SHORT).show();}
                else{
                    p2.setBackgroundColor(0xFF00FF00);
                    p2.invalidate();
                    globalVariable.setOrder(4,true);
                    Toast.makeText(getApplicationContext(), findViewById(R.id.item14_desc).getContentDescription()+" הוסף להזמנה ", Toast.LENGTH_SHORT).show();}
            }
        });*/
    }

//------------------------------------------------------------------------------//
//------------------------------- onCreate End ---------------------------------//
//------------------------------------------------------------------------------//

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        //delayedHide(0);

        final projectGlobals globalVariable = (projectGlobals)getApplicationContext();
        final menu_item menu_item_objects = new menu_item();
        int itemNum = globalVariable.getItemNum();
        globalVariable.OrderBtnFlInit();
        globalVariable.setOrderScrnFL((FrameLayout)findViewById(R.id.order_framelayout));

        final String[] cat = globalVariable.getCategories();
        final String[][] dish = globalVariable.getDishArr();
        int dish_num = globalVariable.getItemNum();

        ImageButton[] add2cart_btns = new ImageButton[itemNum];
        int i1 = 0;
        while (i1 < add2cart_btns.length) {
            add2cart_btns[i1] = new ImageButton(this);
            i1++;
        }

        LinearLayout linlay = (LinearLayout) findViewById(R.id.menu_linear_layout);
        linlay.setBackgroundColor(0x00000000);
        linlay.invalidate();

        int i = 1, j = 0, item_count=0;

        while (i < cat.length) {
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

        item_count=0;
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
                item_count++;
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


    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
           // show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
       // mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }
/*
    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
      //  mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
       //         | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = false;

       // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }*/

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
}
