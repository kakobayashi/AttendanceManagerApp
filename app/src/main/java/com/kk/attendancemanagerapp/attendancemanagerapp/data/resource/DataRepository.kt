package com.kk.attendancemanagerapp.attendancemanagerapp.data.resource

import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.kk.attendancemanagerapp.attendancemanagerapp.data.HistoryAttendance
import com.kk.attendancemanagerapp.attendancemanagerapp.utils.AppUtil
import com.kk.attendancemanagerapp.attendancemanagerapp.utils.DatabaseHelper

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

    /**
     * 出勤中かどうか
     * @param pref SharedPreference
     * @return true: 出勤中
     */
    override fun isStartAttendance(pref: SharedPreferences): Boolean {
        return pref.getBoolean(KEY_PREFERENCE_IS_START_ATTENDANCE, false)
    }

    /**
     * 出勤中かどうかをセット
     * @param pref    SharedPreference
     * @param isStart 出勤中フラグ
     */
    override fun setIsStartAttendance(pref: SharedPreferences, isStart: Boolean) {
        pref.edit().putBoolean(KEY_PREFERENCE_IS_START_ATTENDANCE, isStart).apply()
    }

    /**
     * 本日の勤務時間と日付をDBに保存
     * @param context コンテキスト
     * @param time    本日の勤務時間
     */
    override fun setAttendanceTimeToDB(context: Context, time: String) {
        val dbHelper = DatabaseHelper(context)
        val writer: SQLiteDatabase = dbHelper.writableDatabase

        val values = ContentValues()
        values.put(DatabaseHelper.AttendanceTable.COLUMN_NAME_DATE.value, AppUtil.getYesterdayDate())
        values.put(DatabaseHelper.AttendanceTable.COLUMN_NAME_ATTENDANCE_TIME.value, time)
        android.util.Log.d("kkkk", "set time: " + time + ", " + AppUtil.getYesterdayDate())
        writer.insert(DatabaseHelper.AttendanceTable.TABLE_NAME.value, null, values)
    }

    /**
     * 勤務時間日付をDBから取得
     * @param context コンテキスト
     * @return 日付リスト
     */
    override fun getAttendanceDateToDB(context: Context): ArrayList<String> {
        val dbHelper: DatabaseHelper = DatabaseHelper(context)
        val reader: SQLiteDatabase = dbHelper.readableDatabase

        // DBの日付を全件取得する
        val projection: Array<String> = arrayOf(
            DatabaseHelper.AttendanceTable.COLUMN_NAME_DATE.value)
        val cursor: Cursor? = reader.query(
            DatabaseHelper.AttendanceTable.TABLE_NAME.value,
            projection,
            null,
            null,
            null,
            null,
            null,
            null)

        val list: ArrayList<String> = ArrayList()
        while (cursor!!.moveToNext()) {
            list.add(cursor.getString(cursor.getColumnIndexOrThrow(
                DatabaseHelper.AttendanceTable.COLUMN_NAME_DATE.value)))
        }
        cursor.close()

        return list
    }

    /**
     * 勤務時間をDBから取得
     * @param context コンテキスト
     * @return 勤務時間リスト
     */
    override fun getAttendanceTimeToDB(context: Context): ArrayList<String> {
        val dbHelper = DatabaseHelper(context)
        val reader: SQLiteDatabase = dbHelper.readableDatabase

        // DBの時間を全件取得する
        val projection: Array<String> = arrayOf(
            DatabaseHelper.AttendanceTable.COLUMN_NAME_ATTENDANCE_TIME.value)
        val cursor: Cursor? = reader.query(
            DatabaseHelper.AttendanceTable.TABLE_NAME.value,
            projection,
            null,
            null,
            null,
            null,
            null,
            null)

        val list: ArrayList<String> = ArrayList()
        while (cursor!!.moveToNext()) {
            list.add(cursor.getString(cursor.getColumnIndexOrThrow(
                DatabaseHelper.AttendanceTable.COLUMN_NAME_ATTENDANCE_TIME.value)))
        }
        cursor.close()

        return list
    }

    /**
     * 過去の勤務時間リストを取得する
     * @param context コンテキスト
     * @return 日時/勤務時間リスト
     */
    override fun getHistoryAttendanceList(context: Context): ArrayList<HistoryAttendance> {
        val dateList: ArrayList<String> = getAttendanceDateToDB(context)
        val timeList: ArrayList<String> = getAttendanceTimeToDB(context)

        val list: ArrayList<HistoryAttendance> = ArrayList()
        var i = 0
        dateList.forEach { _ ->
            val attendance = HistoryAttendance(dateList[i], timeList[i])
            list.add(attendance)
            i++
        }

        return list
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
        private const val KEY_PREFERENCE_IS_START_ATTENDANCE = "key_preference_is_start_attendance"

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