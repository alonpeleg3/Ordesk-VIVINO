package net.ordesk.ordesk_vivino;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
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

        final projectGlobals globalVariable = (projectGlobals)getApplicationContext();
        final boolean order_flag[] = globalVariable.getOrderArray();

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
        });
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
