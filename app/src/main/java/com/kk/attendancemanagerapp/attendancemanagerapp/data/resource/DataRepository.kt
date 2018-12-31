package com.kk.attendancemanagerapp.attendancemanagerapp.data.resource

import android.content.SharedPreferences

class DataRepository: DataSource {

    /**
     * 設定データをPreferenceに保存
     * @param pref       SharedPreference
     * @param name       ユーザー名
     * @param attendance 出勤時間
     * @param breakStart 休憩開始時間
     * @param breakEnd   休憩終了時間
     * @param unit       時間単位
     * @param salaried   残り有給日数
     */
    override fun saveSettingData(pref: SharedPreferences, name: String?, attendanceDate: String, attendance: String?,
                                 breakStart: String?, breakEnd: String?, unit: Int, salaried: String?) {
        pref.edit().putString(KEY_PREFERENCE_USER_NAME, name).apply()
        pref.edit().putString(KEY_PREFERENCE_ATTENDANCE_DATE, attendanceDate).apply()
        pref.edit().putString(KEY_PREFERENCE_ATTENDANCE_TIME, attendance).apply()
        pref.edit().putString(KEY_PREFERENCE_BREAK_START, breakStart).apply()
        pref.edit().putString(KEY_PREFERENCE_BREAK_END, breakEnd).apply()
        pref.edit().putInt(KEY_PREFERENCE_TIME_UNIT, unit).apply()
        pref.edit().putString(KEY_PREFERENCE_SALARIED, salaried).apply()
    }

    companion object {
        // Preferenceキー
        const val KEY_PREFERENCE_SETTING_DATA = "key_preference_setting_data"
        private const val KEY_PREFERENCE_USER_NAME = "key_preference_user_name"
        private const val KEY_PREFERENCE_ATTENDANCE_DATE = "key_preference_attendance_date"
        private const val KEY_PREFERENCE_ATTENDANCE_TIME = "key_preference_attendance_time"
        private const val KEY_PREFERENCE_BREAK_START = "key_preference_break_start"
        private const val KEY_PREFERENCE_BREAK_END = "key_preference_break_end"
        private const val KEY_PREFERENCE_TIME_UNIT = "key_preference_time_unit"
        private const val KEY_PREFERENCE_SALARIED = "key_preference_salaried"

        // singleTon
        private var sInstance: DataRepository? = null

        fun getInstance(): DataRepository? {
            if (sInstance == null) {
                sInstance = DataRepository()
            }
            return sInstance
        }
    }
}