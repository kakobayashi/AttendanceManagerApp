package com.kk.attendancemanagerapp.attendancemanagerapp.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.kk.attendancemanagerapp.attendancemanagerapp.data.resource.DataRepository
import com.kk.attendancemanagerapp.attendancemanagerapp.utils.AppUtil

class AppAlarmReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null) {
            val repository: DataRepository? = DataRepository.getInstance()

            // 勤務時間を取得
            val time = repository?.getTodayAttendanceTime(context.getSharedPreferences(
                DataRepository.KEY_PREFERENCE_SETTING_DATA, Context.MODE_PRIVATE))
            // 日付と勤務時間をDBに保存
            repository?.setAttendanceTimeToDB(context, AppUtil.convertTimeToString(time))

            // 勤務時間を初期化
            repository?.clearTodayAttendanceTime(context.getSharedPreferences(
                DataRepository.KEY_PREFERENCE_SETTING_DATA, Context.MODE_PRIVATE))

            // 勤務中フラグを落とす
            repository?.setIsStartAttendance(context.getSharedPreferences(DataRepository.
                KEY_PREFERENCE_SETTING_DATA, Context.MODE_PRIVATE), false)
        }
    }
}