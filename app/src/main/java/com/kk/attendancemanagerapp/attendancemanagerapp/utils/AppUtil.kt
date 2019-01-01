package com.kk.attendancemanagerapp.attendancemanagerapp.utils

import java.text.SimpleDateFormat
import java.util.*

class AppUtil {
    companion object {
        /**
         * 本日の日付取得
         * @return 本日の日付
         */
        fun getTodayDate(): String {
            val now = Date(System.currentTimeMillis())
            val sdf = SimpleDateFormat("yyyy/MM/dd")
            return sdf.format(now)
        }
    }
}