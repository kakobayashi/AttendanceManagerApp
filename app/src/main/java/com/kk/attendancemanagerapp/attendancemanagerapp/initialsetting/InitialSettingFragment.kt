package com.kk.attendancemanagerapp.attendancemanagerapp.initialsetting

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kk.attendancemanagerapp.attendancemanagerapp.R

class InitialSettingFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_initial_setting, container, false)
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
