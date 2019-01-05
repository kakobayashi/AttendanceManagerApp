package com.kk.attendancemanagerapp.attendancemanagerapp.manager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.kk.attendancemanagerapp.attendancemanagerapp.receiver.AppAlarmReceiver
import java.util.*


class AppAlarmManager(context: Context) {

    private val mContext: Context = context

    /**
     * 日時アラームのセット
     */
    fun setDailyAlarm() {
        val intent = Intent(mContext, AppAlarmReceiver::class.java)
        val sender = PendingIntent.getBroadcast(mContext, 0, intent, 0)

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 24)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)

        val alarmManager = mContext.getSystemService(Context.ALARM_SERVICE) as android.app.AlarmManager
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, sender)
    }
}