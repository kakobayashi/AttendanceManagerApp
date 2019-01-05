package com.kk.attendancemanagerapp.attendancemanagerapp.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.kk.attendancemanagerapp.attendancemanagerapp.data.resource.DataRepository

class AppAlarmReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null) {
            val repository: DataRepository? = DataRepository.getInstance()
            // 勤務時間を初期化
            repository?.clearTodayAttendanceTime(context.getSharedPreferences(
                DataRepository.KEY_PREFERENCE_SETTING_DATA, Context.MODE_PRIVATE))

            // 勤務中フラグを落とす
            repository?.setIsStartAttendance(context.getSharedPreferences(DataRepository.
                KEY_PREFERENCE_SETTING_DATA, Context.MODE_PRIVATE), false)
        }
    }
}