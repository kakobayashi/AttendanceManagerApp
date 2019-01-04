package com.kk.attendancemanagerapp.attendancemanagerapp.basicsetting

import android.content.Context
import com.kk.attendancemanagerapp.attendancemanagerapp.data.resource.DataRepository

class BasicSettingViewModel(repository: DataRepository?, context: Context) {

    private var mNavigator: BasicSettingNavigator? = null
    val mRepository: DataRepository? = repository
    val mContext: Context = context

    /**
     * コールバック用Navigatorの登録
     * ViewModel -> Activity へコールバックを返す
     */
    fun setNavigator(navigator: BasicSettingNavigator) {
        mNavigator = navigator
    }
}