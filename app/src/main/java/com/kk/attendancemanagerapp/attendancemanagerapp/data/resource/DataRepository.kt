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

    /**
     * 開始時間を保存
     * @param pref SharedPreference
     * @param time 開始ミリ秒
     */
    override fun saveCurrentTime(pref: SharedPreferences, time: Long) {
        pref.edit().putLong(KEY_PREFERENCE_START_TIME, time).apply()
    }

    /**
     * 勤務時間を取得
     * @param pref SharedPreference
     * @param time 終了ミリ秒
     * @return 勤務時間
     */
    override fun getAttendanceTime(pref: SharedPreferences, time: Long): Long {
        val startTime: Long = pref.getLong(KEY_PREFERENCE_START_TIME, 0)
        return System.currentTimeMillis() - startTime
    }

    /**
     * 本日の勤務時間を保存
     * @param pref SharedPreference
     * @param time 本日の勤務時間
     */
    override fun saveTodayAttendanceTime(pref: SharedPreferences, time: Long?) {
        pref.edit().putLong(KEY_PREFERENCE_TODAY_ATTENDANCE_TIME, time!!).apply()
    }

    /**
     * 本日の勤務時間を取得
     * @param pref SharedPreference
     * @return 本日の勤務時間のミリ秒
     */
    override fun getTodayAttendanceTime(pref: SharedPreferences): Long {
        return pref.getLong(KEY_PREFERENCE_TODAY_ATTENDANCE_TIME, 0)
    }

    /**
     * 本日の勤務時間の初期化
     * @param pref SharedPreference
     */
    override fun clearTodayAttendanceTime(pref: SharedPreferences) {
        pref.edit().putLong(KEY_PREFERENCE_TODAY_ATTENDANCE_TIME, 0).apply()
    }

    /**
     * 初期設定完了
     * @param pref SharedPreference
     */
    override fun setCompleteInitialSetting(pref: SharedPreferences) {
        pref.edit().putBoolean(KEY_PREFERENCE_COMPLETE_INITIAL_SETTING, true).apply()
    }

    /**
     * 初期設定完了かどうか
     * @param pref SharedPreference
     * @return true: 初期設定完了済み
     */
    override fun getCompleteInitialSetting(pref: SharedPreferences): Boolean {
        return pref.getBoolean(KEY_PREFERENCE_COMPLETE_INITIAL_SETTING, false)
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

        // 開始時間
        private const val KEY_PREFERENCE_START_TIME = "key_preference_start_time"

        // 本日の勤務時間
        private const val KEY_PREFERENCE_TODAY_ATTENDANCE_TIME = "key_preference_today_attendance_time"

        // 初期設定完了
        private const val KEY_PREFERENCE_COMPLETE_INITIAL_SETTING = "key_preference_complete_initial_setting"

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