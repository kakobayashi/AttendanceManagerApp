package com.kk.attendancemanagerapp.attendancemanagerapp.attendance

import android.content.Context
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.text.TextUtils
import com.kk.attendancemanagerapp.attendancemanagerapp.data.resource.DataRepository
import com.kk.attendancemanagerapp.attendancemanagerapp.utils.AppUtil

class AttendanceViewModel(repository: DataRepository?, context: Context) {

    private var mNavigator: AttendanceNavigator? = null
    private val mContext: Context = context
    private val mRepository: DataRepository? = repository

    val mIsStart: ObservableBoolean = ObservableBoolean()

    var mTodayDate: ObservableField<String> = ObservableField()

    var mTodayAttendanceTime: ObservableField<String> = ObservableField()

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
        if (!(mRepository!!.isStartAttendance(mContext.getSharedPreferences(DataRepository.
                KEY_PREFERENCE_SETTING_DATA, Context.MODE_PRIVATE)))) {
            // 開始時には現在の時刻を保存
            mRepository.saveCurrentTime(mContext.getSharedPreferences(DataRepository.
                KEY_PREFERENCE_SETTING_DATA, Context.MODE_PRIVATE), System.currentTimeMillis())
            // 勤務中フラグをたてる
            mRepository.setIsStartAttendance(mContext.getSharedPreferences(DataRepository.
                KEY_PREFERENCE_SETTING_DATA, Context.MODE_PRIVATE), true)
        } else {
            // 終了だったら、開始時間と比較して差分を取得
            val time = mRepository.getAttendanceTime(mContext.getSharedPreferences(DataRepository.
                KEY_PREFERENCE_SETTING_DATA, Context.MODE_PRIVATE), System.currentTimeMillis())

            // ミリ秒を文字列に変換する
            val timeStr: String = AppUtil.convertTimeToString(time)

            if (!TextUtils.isEmpty(timeStr)) {
                // 本日の勤務時間もミリ秒で取得
                val timeToday: Long? = mRepository.getTodayAttendanceTime(
                    mContext.getSharedPreferences(DataRepository.KEY_PREFERENCE_SETTING_DATA,
                        Context.MODE_PRIVATE))

                // 差分を本日の勤務時間に加算して保存する
                mRepository.saveTodayAttendanceTime(mContext.getSharedPreferences(DataRepository.
                    KEY_PREFERENCE_SETTING_DATA, Context.MODE_PRIVATE), timeToday?.plus(time)
                )
                // 本日の勤務時間をUI反映する
                mTodayAttendanceTime.set(AppUtil.convertTimeToString(timeToday?.plus(time)))
            }
            // 勤務中フラグをおろす
            mRepository.setIsStartAttendance(mContext.getSharedPreferences(DataRepository.
                KEY_PREFERENCE_SETTING_DATA, Context.MODE_PRIVATE), false)
        }

        // ボタンテキストを逆転する
        mIsStart.set(!mIsStart.get())
    }

    /**
     * 本日の日付をセット
     */
    fun setTodayDate(date: String) {
        mTodayDate.set(date)
    }

    /**
     * 出勤中かどうか初期セットアップ
     */
    fun setupStartFlag() {
        mIsStart.set(mRepository?.isStartAttendance(mContext.getSharedPreferences(DataRepository.
            KEY_PREFERENCE_SETTING_DATA, Context.MODE_PRIVATE))!!)
    }

    /**
     * 出勤時間のセットアップ
     */
    fun setupAttendanceTime() {
        val timeToday: Long? = mRepository?.getTodayAttendanceTime(
            mContext.getSharedPreferences(DataRepository.KEY_PREFERENCE_SETTING_DATA,
                Context.MODE_PRIVATE))

        // 本日の勤務時間をUI反映する
        mTodayAttendanceTime.set(AppUtil.convertTimeToString(timeToday))
    }
}