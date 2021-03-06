/*
   Copyright (C) 2015 Martin Bayerl, Christoph Krasa, Linda Spindler, Clemens Hlawacek

   This file is part of Slideshow.

   Slideshow is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.

   Slideshow is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with Slideshow.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.github.howeyc.slideshow.helper.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.GregorianCalendar;

import com.github.howeyc.slideshow.helper.Keys;
import com.github.howeyc.slideshow.settings.AppData;


/**
 * Created by Martin on 05.12.2015.
 */

public class AlarmReceiver extends BroadcastReceiver {
    private static final String TAG = AlarmReceiver.class.getSimpleName();
    TimeConverter tc;

    @Override
    public void onReceive(Context context, Intent intent) {

        tc = new TimeConverter();

        System.out.println(" ALARMRECEIVER ");

//        Long scheduledAlarm = lastAlarmTime+AppData.getUpdateIntervalInHours();
        Long currentTime = new GregorianCalendar().getTimeInMillis();

        AppData.setLastAlarmTime(currentTime);

        Log.d(TAG, "Current Time    : " + tc.millisecondsToDate(currentTime));
        AlarmScheduler alarmScheduler = new AlarmScheduler();
        Long nextAlarm = alarmScheduler.scheduleAlarm();
        Log.d(TAG, "Next Alarm      : "+tc.millisecondsToDate(nextAlarm));
        Log.d(TAG,"Start time: " + tc.millisecondsToDate(currentTime) + " " + (currentTime));
        Log.d(TAG, "Go OFF time: " + tc.millisecondsToDate(nextAlarm) + " " + nextAlarm);
    }
}
