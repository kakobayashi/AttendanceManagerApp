package com.kk.attendancemanagerapp.attendancemanagerapp.attendance

import android.content.Context
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.kk.attendancemanagerapp.attendancemanagerapp.data.resource.DataRepository

class AttendanceViewModel(repository: DataRepository?, context: Context) {

    private var mNavigator: AttendanceNavigator? = null
    private val mContext: Context = context

    val mIsStart: ObservableBoolean = ObservableBoolean()

    var mTodayDate: ObservableField<String> = ObservableField()

    /**
     * コールバック用Navigatorの登録
     * ViewModel -> Activity へコールバックを返す
     */
    fun setNavigator(navigator: AttendanceNavigator) {
        mNavigator = navigator
    }

    /**
     * スタートボタンタップ時の処理
     */
    fun onClickStartButton() {
        mIsStart.set(!mIsStart.get())
    }

    /**
     * 本日の日付をセット
     */
    fun setTodayDate(date: String) {
        mTodayDate.set(date)
    }
}