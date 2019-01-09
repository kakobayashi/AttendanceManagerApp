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

        /**
         * 昨日の日付取得
         * @return 本日の日付
         */
        fun getYesterdayDate(): String {
            val now = Date(System.currentTimeMillis())
            val cal: Calendar = Calendar.getInstance()
            cal.time = now
            cal.add(Calendar.DAY_OF_MONTH, -1);
            val sdf = SimpleDateFormat("yyyy/MM/dd")
            return sdf.format(cal.time)
        }

        /**
         * ミリ秒から文字列に時間変換
         * @param time ミリ秒
         * @return 文字列時間
         */
        fun convertTimeToString(time: Long?): String {
            if (time != null) {
                var sec = time / 1000L
                val min = sec / 60L
                val hour = min / 60L

                if (sec >= 60) {
                    sec %= 60
                }

                return TimePickerDialogUtil.timeToTwoDigits(hour) + ":" +
                        TimePickerDialogUtil.timeToTwoDigits(min) + ":" +
                        TimePickerDialogUtil.timeToTwoDigits(sec)
            }
            return ""
        }
    }
}