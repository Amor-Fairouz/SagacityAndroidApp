package com.esprit.sagacity.Helpers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.esprit.sagacity.services.AlarmTask;

import java.util.Calendar;
/**
 * Created by amor on 06/01/2016.
 */

public class BootReceiver extends BroadcastReceiver{
    SharedPreferences preferences;
    @Override
    public void onReceive(Context context, Intent intent) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(preferences.getLong("alarmTime2",
                System.currentTimeMillis()));

        new AlarmTask(context, c).run();


    }

}
