package com.kk.attendancemanagerapp.attendancemanagerapp.initialsetting

import android.app.TimePickerDialog
import android.databinding.ObservableField
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.kk.attendancemanagerapp.attendancemanagerapp.R
import com.kk.attendancemanagerapp.attendancemanagerapp.databinding.FragmentInitialSettingBinding
import com.kk.attendancemanagerapp.attendancemanagerapp.utils.TimePickerDialogUtil
import java.util.*

class InitialSettingFragment : Fragment() {

    private lateinit var mViewModel: InitialSettingViewModel

    private var mDataBinding: FragmentInitialSettingBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root: View? = inflater.inflate(R.layout.fragment_initial_setting, container, false)

        // bind
        if (mDataBinding == null) {
            mDataBinding = FragmentInitialSettingBinding.bind(root!!)
        }

        // binderにViewModelを設定する
        mDataBinding?.viewmodel = mViewModel

        // 出勤時間
        val attendanceTimeEdit: EditText? = root?.findViewById(R.id.attendance_time_edit_text)
        setupTimePickerDialog(attendanceTimeEdit, mViewModel.mAttendanceTime)

        // 休憩開始時間
        val breakStartEdit: EditText? = root?.findViewById(R.id.break_time_start_edit_text)
        setupTimePickerDialog(breakStartEdit, mViewModel.mBreakStart)

        // 休憩終了時間
        val breakEndEdit: EditText? = root?.findViewById(R.id.break_time_end_edit_text)
        setupTimePickerDialog(breakEndEdit, mViewModel.mBreakEnd)

        return mDataBinding?.root
    }

    /**
     * ViewModelのセット
     * @param viewModel セットするViewModel
     */
    fun setViewModel(viewModel: InitialSettingViewModel) {
        mViewModel = viewModel
    }

    /**
     * EditTextに設置するTimePickerDialogの設定
     * @param editText 対象の編集テキスト
     * @param field    Binding編集テキスト文言
     */
    private fun setupTimePickerDialog(editText: EditText?, field: ObservableField<String>?) {
        // キーボードを開かない
        editText?.keyListener = null
        editText?.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                showTimePickerDialog(field)
            }
        }
    }

    /**
     * TimePickerDialogの表示
     * @param field Binding編集テキスト
     */
    private fun showTimePickerDialog(field: ObservableField<String>?) {
        val calendar: Calendar = Calendar.getInstance()
        val hour: Int = calendar.get(Calendar.HOUR_OF_DAY)
        val minute: Int = calendar.get(Calendar.MINUTE)
        val picker = TimePickerDialog(context,
            TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                // viewmodelの変数に値をセットしてあげないといけない. データをbindしてあげる
                field?.set(TimePickerDialogUtil.createTimeToTwoDigits(hourOfDay, minute))
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
