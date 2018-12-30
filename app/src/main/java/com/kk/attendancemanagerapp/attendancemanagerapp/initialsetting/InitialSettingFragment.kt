package com.kk.attendancemanagerapp.attendancemanagerapp.initialsetting

import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.kk.attendancemanagerapp.attendancemanagerapp.R
import com.kk.attendancemanagerapp.attendancemanagerapp.attendance.AttendanceActivity
import com.kk.attendancemanagerapp.attendancemanagerapp.utils.TimePickerDialogUtil
import java.util.*

class InitialSettingFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root: View? = inflater.inflate(R.layout.fragment_initial_setting, container, false)

        val userNameEdit: EditText? = root?.findViewById(R.id.user_edit_text)

        // 出勤時間
        val attendanceTimeEdit: EditText? = root?.findViewById(R.id.attendance_time_edit_text)
        setupTimePickerDialog(attendanceTimeEdit)

        // 休憩開始時間
        val breakStartEdit: EditText? = root?.findViewById(R.id.break_time_start_edit_text)
        setupTimePickerDialog(breakStartEdit)

        // 休憩終了時間
        val breakEndEdit: EditText? = root?.findViewById(R.id.break_time_end_edit_text)
        setupTimePickerDialog(breakEndEdit)

        // 残り有給日数
        val salariedEdit: EditText? = root?.findViewById(R.id.salaried_edit_text)

        val incompleteText: TextView? = root?.findViewById(R.id.initial_setting_incomplete_text)

        val nextButton : Button? = root?.findViewById(R.id.initial_setting_next_button)
        if (nextButton != null) {
            nextButton.setOnClickListener(View.OnClickListener {
                val userName: String = userNameEdit?.text.toString()
                val attendanceTime: String = attendanceTimeEdit?.text.toString()
                val breakStart: String = breakStartEdit?.text.toString()
                val breakEnd: String = breakEndEdit?.text.toString()
                val salaried: String = salariedEdit?.text.toString()

                if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(attendanceTime) &&
                    !TextUtils.isEmpty(breakStart) && !TextUtils.isEmpty(breakEnd) &&
                    !TextUtils.isEmpty(salaried)) {
                    // すべて入力されていた場合は次の画面へ移行
                    val intent = Intent(context, AttendanceActivity::class.java)
                    startActivity(intent)
                } else {
                    // すべて入力されていない場合は警告テキストを表示
                    incompleteText?.visibility = View.VISIBLE
                }
            })
        }

        return root
    }

    /**
     * EditTextに設置するTimePickerDialogの設定
     * @param editText 対象の編集テキスト
     */
    private fun setupTimePickerDialog(editText: EditText?) {
        // キーボードを開かない
        editText?.keyListener = null
        editText?.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                showTimePickerDialog(editText)
            }
        }
        editText?.setOnClickListener {
            showTimePickerDialog(editText)
        }
    }

    /**
     * TimePickerDialogの表示
     * @param editText 時間入力する編集テキスト
     */
    private fun showTimePickerDialog(editText: EditText?) {
        val calendar: Calendar = Calendar.getInstance()
        val hour: Int = calendar.get(Calendar.HOUR_OF_DAY)
        val minute: Int = calendar.get(Calendar.MINUTE)
        val picker: TimePickerDialog = TimePickerDialog(context,
            TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                editText?.setText(TimePickerDialogUtil.createTimeToTwoDigits(hourOfDay, minute))
            }, hour, minute, true)
        picker.show()
    }

    companion object {
        /**
         * インスタンス生成. singleTon
         * @return fragmentインスタンス
         */
        fun newInstance(): InitialSettingFragment {
            return InitialSettingFragment()
        }
    }
}
