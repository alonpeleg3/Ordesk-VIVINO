package net.ordesk.ordesk_vivino;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class pay_screen extends AppCompatActivity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

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
    protected void onCreate(Bundle savedInstanceState) {
        delayedHide(0);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pay_screen);

        mVisible = true;
        // mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.pay_framelayout);

        final projectGlobals globalVariable = (projectGlobals)getApplicationContext();
        final boolean order_flag[] = globalVariable.getOrderArray();

        int itemNum = globalVariable.getItemNum();

        Button[] rmvbtn = new Button[itemNum];
        int i1 = 0;
        while (i1 < rmvbtn.length) {
            rmvbtn[i1] = new Button(this);
            i1++;
        }

        FrameLayout fl = (FrameLayout)findViewById(R.id.no_order_message);
        fl.setVisibility(View.VISIBLE);

        TableLayout orderTable = (TableLayout)findViewById(R.id.order_tbl);
        FrameLayout tbl_headers = (FrameLayout)findViewById(R.id.order_tbl_headers);
        FrameLayout order_summary_fl = (FrameLayout)findViewById(R.id.order_summary);

        order_summary_fl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopLockTask();
            }
        });

        String[] headers = {"הערות","מחיר","תוספות","כמות","מנה"};
        int i=0,j=0;
        int row_num=0;
        while (i < order_flag.length)
        {
            if (globalVariable.getOrder(i)){
                if (row_num == 0)
                {
                    fl.setVisibility(View.INVISIBLE);
                    order_summary_fl.setVisibility(View.VISIBLE);
                    TableRow row = new TableRow(this);
                    TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,50);
                    row.setBackgroundColor(0xFF393838);
                    row.setLayoutParams(lp);
                    LinearLayout ll = new LinearLayout(this);

                    while(j < headers.length)
                    {
                        TextView tv = new TextView(this);
                        tv.setText(headers[j]);
                        tv.setWidth(120);
                        tv.setTextColor(0xFFFFFFFF);
                        tv.setTextSize(20);
                        tv.setPadding(0,5,0,5);
                        ll.addView(tv);
                        j++;
                    }

                    row.addView(ll);
                    row.invalidate();
                    tbl_headers.addView(row);
                    //row_num++;
                }
                TableRow row = new TableRow(this);
                TableRow.LayoutParams lp = new TableRow.LayoutParams(650, 40);
                LinearLayout.LayoutParams frmLayParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 40);
                LinearLayout.LayoutParams btnLayParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 40);

                row.setBackgroundColor(0xFFffffff);
                row.setLayoutParams(lp);

                LinearLayout ll = new LinearLayout(this);
                ll.setOrientation(LinearLayout.HORIZONTAL);
                //LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(650, 40);
                //llp.setMarginStart(30);
                //ll.setLayoutParams(llp);
                ll.setBackgroundColor(0xFFc7c7c7);
                ll.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);

                FrameLayout fl1 = new FrameLayout(this);
                frmLayParams.setMargins(0,0,60,0);
                frmLayParams.setMarginStart(470);
                fl1.setLayoutParams(frmLayParams);
                TextView tv = new TextView(this);
                tv.setText(globalVariable.getDish(0,i));
                tv.setTextColor(0xFF000000);
                tv.setBackgroundColor(0xFFc7c7c7);
                tv.setTextDirection(View.TEXT_DIRECTION_RTL);
                tv.setPadding(0,8,60,2);
                fl1.addView(tv);

                FrameLayout fl2 = new FrameLayout(this);

                rmvbtn[i].setLayoutParams(btnLayParams);
                rmvbtn[i].setText("הסר");

                rmvbtn[i].setId(1000+i);
                rmvbtn[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            globalVariable.setOrder(v.getId()-1000, false);
                            Toast.makeText(getApplicationContext(), globalVariable.getDish(0, v.getId()-1000
                            ) + " הוסר מההזמנה ", Toast.LENGTH_SHORT).show();
                        FrameLayout fl0 = (FrameLayout)v.getParent();
                        LinearLayout ll0 = (LinearLayout)fl0.getParent();
                        TableRow row0 = (TableRow)ll0.getParent();
                        TableLayout tbl0 = (TableLayout)row0.getParent();
                        tbl0.removeView(row0);
                    }
                });

                fl2.addView(rmvbtn[i]);
                ll.addView(fl2,0);
                ll.addView(fl1,1);
                row.addView(ll);
                row.invalidate();
                orderTable.addView(row,row_num);
                row_num++;
            }
            i++;
        }


        Button backbtn = (Button) findViewById(R.id.imageButton_back);
        ImageButton orderbtn = (ImageButton) findViewById(R.id.menu_btn);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), welcome_info.class);
                startActivity(nextScreen);
            }
        });

        orderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), order_screen.class);
                startActivity(nextScreen);
            }
        });

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        //delayedHide(0);
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
