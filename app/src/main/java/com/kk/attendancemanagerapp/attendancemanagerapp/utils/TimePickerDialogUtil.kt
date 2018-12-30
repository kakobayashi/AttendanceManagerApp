package com.kk.attendancemanagerapp.attendancemanagerapp.utils

class TimePickerDialogUtil {

    companion object {
        /**
         * 時間の文字列生成
         * @param hour   時間
         * @param minute 分
         * @return XX:XX時間
         */
        fun createTimeToTwoDigits(hour: Int, minute: Int): String {
            val hourStr: String = timeToTwoDigits(hour)
            val minuteStr: String = timeToTwoDigits(minute)
            return "$hourStr:$minuteStr"
        }

        /**
         * 時間を二桁に整形
         * @param time 時間
         * @return 二桁の時間
         */
        fun timeToTwoDigits(time: Int): String {
            var timeStr = time.toString()
            if (timeStr.length != 2) {
                timeStr = "0$timeStr"
            }
            return timeStr
        }
    }
}