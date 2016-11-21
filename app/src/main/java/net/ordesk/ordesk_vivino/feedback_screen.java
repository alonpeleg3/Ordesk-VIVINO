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
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class feedback_screen extends AppCompatActivity {
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

        setContentView(R.layout.activity_feedback_screen);

        mVisible = true;
        mContentView = findViewById(R.id.feedback_screen_frameLayout);
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        final projectGlobals globalVariable = (projectGlobals)getApplicationContext();
        final boolean order_flag[] = globalVariable.getOrderArray();

        findViewById(R.id.q1_btns_fl).setBackgroundResource(R.drawable.kg0feedback0buttons);
        findViewById(R.id.q2_btns_fl).setBackgroundResource(R.drawable.kg0feedback0buttons);
        findViewById(R.id.q3_btns_fl).setBackgroundResource(R.drawable.kg0feedback0buttons);

        findViewById(R.id.feedback_menu_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), order_screen_pilot.class);
                startActivity(nextScreen);
            }
        });

        findViewById(R.id.feedback_members_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), membership_screen.class);
                startActivity(nextScreen);
            }
        });

        findViewById(R.id.feedback_info_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), welcome_info_pilot.class);
                startActivity(nextScreen);
            }
        });

        findViewById(R.id.feedback_play_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), play_screen.class);
                startActivity(nextScreen);
            }
        });

        findViewById(R.id.q1_good).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.q1_btns_fl).setBackgroundResource(R.drawable.kg0feedback0buttons0good);
                feedback_answers feedback_answers = new feedback_answers();
                feedback_answers.set_ans(1,TRUE);
            }
        });

        findViewById(R.id.q1_nuteral).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.q1_btns_fl).setBackgroundResource(R.drawable.kg0feedback0buttons0nutetal);
                feedback_answers feedback_answers = new feedback_answers();
                feedback_answers.set_ans(1,TRUE);
            }
        });

        findViewById(R.id.q1_bad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.q1_btns_fl).setBackgroundResource(R.drawable.kg0feedback0buttons0bad);
                feedback_answers feedback_answers = new feedback_answers();
                feedback_answers.set_ans(1,TRUE);
            }
        });

        findViewById(R.id.q2_good).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.q2_btns_fl).setBackgroundResource(R.drawable.kg0feedback0buttons0good);
                feedback_answers feedback_answers = new feedback_answers();
                feedback_answers.set_ans(2,TRUE);
            }
        });

        findViewById(R.id.q2_nuteral).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.q2_btns_fl).setBackgroundResource(R.drawable.kg0feedback0buttons0nutetal);
                feedback_answers feedback_answers = new feedback_answers();
                feedback_answers.set_ans(2,TRUE);
            }
        });

        findViewById(R.id.q2_bad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.q2_btns_fl).setBackgroundResource(R.drawable.kg0feedback0buttons0bad);
                feedback_answers feedback_answers = new feedback_answers();
                feedback_answers.set_ans(2,TRUE);
            }
        });

        findViewById(R.id.q3_good).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.q3_btns_fl).setBackgroundResource(R.drawable.kg0feedback0buttons0good);
                feedback_answers feedback_answers = new feedback_answers();
                feedback_answers.set_ans(3,TRUE);
            }
        });

        findViewById(R.id.q3_nuteral).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.q3_btns_fl).setBackgroundResource(R.drawable.kg0feedback0buttons0nutetal);
                feedback_answers feedback_answers = new feedback_answers();
                feedback_answers.set_ans(3,TRUE);
            }
        });

        findViewById(R.id.q3_bad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.q3_btns_fl).setBackgroundResource(R.drawable.kg0feedback0buttons0bad);
                feedback_answers feedback_answers = new feedback_answers();
                feedback_answers.set_ans(3,TRUE);
            }
        });

        findViewById(R.id.send_feedback_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedback_answers feedback_answers = new feedback_answers();
                if (feedback_answers.get_ans(1) && feedback_answers.get_ans(2) && feedback_answers.get_ans(3)) {
                    Toast.makeText(getApplicationContext(), "המשוב נשלח בהצלחה! תודה.", Toast.LENGTH_SHORT).show();
                    findViewById(R.id.q1_btns_fl).setBackgroundResource(R.drawable.kg0feedback0buttons);
                    findViewById(R.id.q2_btns_fl).setBackgroundResource(R.drawable.kg0feedback0buttons);
                    findViewById(R.id.q3_btns_fl).setBackgroundResource(R.drawable.kg0feedback0buttons);
                    feedback_answers.set_ans(1,FALSE);
                    feedback_answers.set_ans(2,FALSE);
                    feedback_answers.set_ans(3,FALSE);
                }
                else{
                    Toast.makeText(getApplicationContext(), "אנא ענה על כל השאלות לפני שליחה", Toast.LENGTH_SHORT).show();
                }
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
