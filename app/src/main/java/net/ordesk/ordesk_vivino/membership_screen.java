package net.ordesk.ordesk_vivino;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class membership_screen extends AppCompatActivity {
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
    private static final int UI_ANIMATION_DELAY = 1;
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

        setContentView(R.layout.activity_membership_screen);

        mVisible = true;
        mContentView = findViewById(R.id.membership_screen_frameLayout);
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        final projectGlobals globalVariable = (projectGlobals)getApplicationContext();
        final boolean order_flag[] = globalVariable.getOrderArray();

        findViewById(R.id.member_btns_fl).setVisibility(View.VISIBLE);
        findViewById(R.id.new_member_reg_fl).setVisibility(View.INVISIBLE);
        findViewById(R.id.exist_member_reg_fl).setVisibility(View.INVISIBLE);

        findViewById(R.id.new_member_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.member_btns_fl).setVisibility(View.INVISIBLE);
                findViewById(R.id.new_member_reg_fl).setVisibility(View.VISIBLE);
                findViewById(R.id.exist_member_reg_fl).setVisibility(View.INVISIBLE);
            }
        });

        findViewById(R.id.existing_member_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.member_btns_fl).setVisibility(View.INVISIBLE);
                findViewById(R.id.new_member_reg_fl).setVisibility(View.INVISIBLE);
                findViewById(R.id.exist_member_reg_fl).setVisibility(View.VISIBLE);
            }
        });

        findViewById(R.id.exist_member_back_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.member_btns_fl).setVisibility(View.VISIBLE);
                findViewById(R.id.new_member_reg_fl).setVisibility(View.INVISIBLE);
                findViewById(R.id.exist_member_reg_fl).setVisibility(View.INVISIBLE);
            }
        });

        findViewById(R.id.sign_up_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.member_btns_fl).setVisibility(View.VISIBLE);
                findViewById(R.id.new_member_reg_fl).setVisibility(View.INVISIBLE);
                findViewById(R.id.exist_member_reg_fl).setVisibility(View.INVISIBLE);
            }
        });

        findViewById(R.id.sign_up_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String memb_priv_name = ((EditText)findViewById(R.id.editText_private_name)).getText().toString();
                String memb_fam_name = ((EditText)findViewById(R.id.editText_family_name)).getText().toString();
                String memb_eMail = ((EditText)findViewById(R.id.editText_eMail)).getText().toString();
                String memb_phon_num = ((EditText)findViewById(R.id.editText_private_phone)).getText().toString();
                String memb_id_num = ((EditText)findViewById(R.id.editText_IDnum)).getText().toString();

                /*
                Here we should insert some test to see that the input is ok
                 */

                ((EditText)findViewById(R.id.editText_private_name)).setText("");
                ((EditText)findViewById(R.id.editText_family_name)).setText("");
                ((EditText)findViewById(R.id.editText_eMail)).setText("");
                ((EditText)findViewById(R.id.editText_private_phone)).setText("");
                ((EditText)findViewById(R.id.editText_IDnum)).setText("");

                Toast.makeText(getApplicationContext(),memb_priv_name+", "+memb_fam_name+", "+memb_eMail+", "+memb_phon_num+", "+memb_id_num, Toast.LENGTH_SHORT).show();

                mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
            }
        });

        findViewById(R.id.member_info_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), welcome_info_pilot.class);
                startActivity(nextScreen);
            }
        });

        findViewById(R.id.member_menu_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        Intent nextScreen = new Intent(getApplicationContext(), order_screen_pilot.class);
                        startActivity(nextScreen);
                    }
        });

        findViewById(R.id.member_play_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), play_screen.class);
                startActivity(nextScreen);
            }
        });

        findViewById(R.id.member_feedback_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), feedback_screen.class);
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

    @Override
    public void onBackPressed() {
        // nothing to do here
        // … really
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
