package com.kk.attendancemanagerapp.attendancemanagerapp.data.resource

import android.content.Context
import android.content.SharedPreferences
import com.kk.attendancemanagerapp.attendancemanagerapp.data.HistoryAttendance

interface DataSource {

    fun saveSettingData(pref: SharedPreferences, name: String?, attendanceDay: String, attendance: String?,
                        breakStart: String?, breakEnd: String?, unit: Int, salaried: String?)

    fun saveCurrentTime(pref: SharedPreferences, time: Long)

    fun getAttendanceTime(pref: SharedPreferences, time: Long): Long

    fun saveTodayAttendanceTime(pref: SharedPreferences, time: Long?)

    fun getTodayAttendanceTime(pref: SharedPreferences): Long

    fun clearTodayAttendanceTime(pref: SharedPreferences)

    fun setCompleteInitialSetting(pref: SharedPreferences)

    fun getCompleteInitialSetting(pref: SharedPreferences): Boolean

    fun isStartAttendance(pref: SharedPreferences): Boolean

    fun setIsStartAttendance(pref: SharedPreferences, isStart: Boolean)

    fun setAttendanceTimeToDB(context: Context, time: String)

    fun getAttendanceDateToDB(context: Context): ArrayList<String>

    fun getAttendanceTimeToDB(context: Context): ArrayList<String>

    fun getHistoryAttendanceList(context: Context): ArrayList<HistoryAttendance>
}