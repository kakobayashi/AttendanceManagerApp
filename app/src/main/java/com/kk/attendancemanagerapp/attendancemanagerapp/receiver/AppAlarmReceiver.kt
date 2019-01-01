package com.kk.attendancemanagerapp.attendancemanagerapp.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.kk.attendancemanagerapp.attendancemanagerapp.data.resource.DataRepository

class AppAlarmReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("kkkk", "onReceive")
        if (context != null) {
            val repository: DataRepository? = DataRepository.getInstance()
            repository?.clearTodayAttendanceTime(context.getSharedPreferences(
                DataRepository.KEY_PREFERENCE_SETTING_DATA, Context.MODE_PRIVATE))
        }
    }
}