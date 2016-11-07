package net.ordesk.ordesk_vivino;

import android.content.Context;
import android.content.Intent;

/**
 * Created by Omri Elmaleh on 07/11/2016.


public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent myIntent = new Intent(context, welcome_screen.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(myIntent);
    }
}
*/