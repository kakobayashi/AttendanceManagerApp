package com.kk.attendancemanagerapp.attendancemanagerapp.initialsetting

import android.content.Context
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.text.TextUtils
import com.kk.attendancemanagerapp.attendancemanagerapp.data.resource.DataRepository

class InitialSettingViewModel(repository: DataRepository?, context: Context)  {

    private val mContext: Context = context

    private var mNavigator: InitialSettingNavigator? = null

    private val mRepository: DataRepository? = repository

    // ユーザー名 editTextは自動的に動的にセットしてくれるためNextButtonを押した時にいれるだけでよさそう
    var mUserName: String? = null
    var mAttendanceTime: ObservableField<String>? = ObservableField()
    var mBreakStart: ObservableField<String>? = ObservableField()
    var mBreakEnd: ObservableField<String>? = ObservableField()
    var mSalaried: String? = null

    var mIsIncompleteText: ObservableBoolean = ObservableBoolean()

    /**
     * コールバック用Navigatorの登録
     * ViewModel -> Activity へコールバックを返す
     */
    fun setNavigator(navigator: InitialSettingNavigator) {
        mNavigator = navigator
    }

    /**
     * NextButtonタップ時の処理
     */
    fun onClickNextButton() {
        if (!TextUtils.isEmpty(mUserName) && mAttendanceTime != null &&
            mBreakStart != null && mBreakEnd != null &&
            !TextUtils.isEmpty(mSalaried)) {
            // view側へコールバックを返す
            mNavigator?.onComplete()
            mIsIncompleteText.set(false)

            // 設定データを保存
            mRepository?.saveSettingData(mContext.getSharedPreferences(
                DataRepository.KEY_PREFERENCE_SETTING_DATA, Context.MODE_PRIVATE), mUserName,
                mAttendanceTime?.get(), mBreakStart?.get(), mBreakEnd?.get(), mSalaried)
        } else {
            // すべて入力されていない場合は警告テキストを表示
            mIsIncompleteText.set(true)
        }
    }
}