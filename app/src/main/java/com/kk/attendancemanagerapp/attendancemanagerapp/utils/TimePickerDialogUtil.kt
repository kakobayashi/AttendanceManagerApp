package com.kk.attendancemanagerapp.attendancemanagerapp.utils

import android.databinding.ObservableBoolean

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

        /**
         * 出勤日の追加
         * @param buffer StringBuffer
         * @param data   曜日
         * @return StringBuffer 0:OFF, 1:ON
         */
        fun appendAttendanceDate(buffer: StringBuffer, data: ObservableBoolean): StringBuffer {
            if (data.get()) {
                buffer.append("1")
            } else {
                buffer.append("0")
            }
            return buffer
        }
    }
}