package com.kk.attendancemanagerapp.attendancemanagerapp.history

import android.content.Context
import com.kk.attendancemanagerapp.attendancemanagerapp.data.HistoryAttendance
import com.kk.attendancemanagerapp.attendancemanagerapp.data.resource.DataRepository

class HistoryViewModel(repository: DataRepository?, context: Context) {

    private var mNavigator: HistoryNavigator? = null

    val mRepository: DataRepository? = repository
    val mContext: Context = context

    /**
     * コールバック用Navigatorの登録
     * ViewModel -> Activity へコールバックを返す
     */
    fun setNavigator(navigator: HistoryNavigator) {
        mNavigator = navigator
    }

    /**
     * 過去の勤務時間リストを取得
     * @param context コンテキスト
     * @return 日付/勤務時間リスト
     */
    fun getHistoryAttendance(context: Context): ArrayList<HistoryAttendance> {
        return mRepository!!.getHistoryAttendanceList(context)
    }
}