package com.kk.attendancemanagerapp.attendancemanagerapp.history

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.kk.attendancemanagerapp.attendancemanagerapp.R
import com.kk.attendancemanagerapp.attendancemanagerapp.data.HistoryAttendance


class HistoryListAdapter(context: Context, resourceId: Int, itemList: ArrayList<HistoryAttendance>)
    : ArrayAdapter<HistoryAttendance>(context, resourceId, itemList) {

    val mResource: Int = resourceId
    val mContext: Context = context
    val mItemList: ArrayList<HistoryAttendance> = itemList
    val mInflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View
        if (convertView != null) {
            view = convertView
        } else {
            view = mInflater.inflate(mResource, null)
        }

        // 要素を取得する
        val item: HistoryAttendance = mItemList[position]

        // 日付と勤務時間を設定する
        val dateText: TextView = view.findViewById(R.id.item_date)
        dateText.text = item.mDate
        val timeText: TextView = view.findViewById(R.id.item_attendance_time)
        timeText.text = item.mAttendanceTime.toString()

        return view
    }
}