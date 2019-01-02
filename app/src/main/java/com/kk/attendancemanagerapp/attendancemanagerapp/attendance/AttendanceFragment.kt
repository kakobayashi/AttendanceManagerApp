package com.kk.attendancemanagerapp.attendancemanagerapp.attendance

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kk.attendancemanagerapp.attendancemanagerapp.R
import com.kk.attendancemanagerapp.attendancemanagerapp.databinding.FragmentAttendanceBinding
import com.kk.attendancemanagerapp.attendancemanagerapp.utils.AppUtil

class AttendanceFragment : Fragment() {

    private lateinit var mViewModel: AttendanceViewModel

    private var mDataBinding: FragmentAttendanceBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val root: View? = inflater.inflate(R.layout.fragment_attendance, container, false)

        // bind
        if (mDataBinding == null) {
            mDataBinding = FragmentAttendanceBinding.bind(root!!)
        }

        // binderにViewModelを設定する
        mDataBinding?.viewmodel = mViewModel

        mViewModel.setTodayDate(AppUtil.getTodayDate())

        return mDataBinding?.root
    }

    /**
     * ViewModelのセット
     * @param viewModel セットするViewModel
     */
    fun setViewModel(viewModel: AttendanceViewModel) {
        mViewModel = viewModel
    }

    companion object {
        /**
         * インスタンス生成. singleTon
         * @return fragmentインスタンス
         */
        fun newInstance(): AttendanceFragment {
            return AttendanceFragment()
        }
    }
}
