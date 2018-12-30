package com.kk.attendancemanagerapp.attendancemanagerapp.data.resource

import android.content.SharedPreferences

interface DataSource {

    fun saveSettingData(pref: SharedPreferences, name: String?, attendance: String?,
                        breakStart: String?, breakEnd: String?, salaried: String?)
}