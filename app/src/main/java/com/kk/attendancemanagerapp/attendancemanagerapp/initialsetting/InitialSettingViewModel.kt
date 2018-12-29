package com.kk.attendancemanagerapp.attendancemanagerapp.initialsetting

import android.content.Context
import com.kk.attendancemanagerapp.attendancemanagerapp.data.resource.DataRepository

class InitialSettingViewModel(repository: DataRepository?, context: Context)  {

    private val mContext: Context = context

    private var mNavigator: InitialSettingNavigator? = null

    /**
     * コールバック用Navigatorの登録
     * ViewModel -> Activity へコールバックを返す
     */
    fun setNavigator(navigator: InitialSettingNavigator) {
        mNavigator = navigator
    }
}