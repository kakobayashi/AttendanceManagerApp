package com.kk.attendancemanagerapp.attendancemanagerapp.basicsetting

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import com.kk.attendancemanagerapp.attendancemanagerapp.R
import com.kk.attendancemanagerapp.attendancemanagerapp.databinding.FragmentBasicSettingBinding
import com.kk.attendancemanagerapp.attendancemanagerapp.editsetting.EditSettingActivity

class BasicSettingFragment : Fragment() {

    private lateinit var mViewModel: BasicSettingViewModel

    private var mDataBinding: FragmentBasicSettingBinding? = null

    private var mListView: ListView? = null

    private var mListener: AdapterView.OnItemClickListener =
        AdapterView.OnItemClickListener { parent, view, position, id ->

            when (position) {
                POSITION_USER_NAME -> {
                    val intent = Intent(context, EditSettingActivity::class.java)
                    startActivity(intent)
                }
                POSITION_ATTENDANCE_DATE -> {
                    val intent = Intent(context, EditSettingActivity::class.java)
                    startActivity(intent)
                }
                POSITION_ATTENDANCE_TIME -> {
                    val intent = Intent(context, EditSettingActivity::class.java)
                    startActivity(intent)
                }
                POSITION_BREAK_TIME -> {
                    val intent = Intent(context, EditSettingActivity::class.java)
                    startActivity(intent)
                }
                POSITION_TIME_UNIT -> {
                    val intent = Intent(context, EditSettingActivity::class.java)
                    startActivity(intent)
                }
                POSITION_SALARIED -> {
                    val intent = Intent(context, EditSettingActivity::class.java)
                    startActivity(intent)
                }
            }
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val root: View? = inflater.inflate(R.layout.fragment_basic_setting, container, false)

        // bind
        if (mDataBinding == null) {
            mDataBinding = FragmentBasicSettingBinding.bind(root!!)
        }

        // binderにViewModelを設定する
        mDataBinding?.viewmodel = mViewModel

        // リストビューにアダプターを設定する
        mListView = root?.findViewById(R.id.basic_setting_list)
        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(context,
            android.R.layout.simple_list_item_1,
            context?.resources?.getStringArray(R.array.basic_setting_item_list))
        mListView?.adapter = arrayAdapter
        mListView?.onItemClickListener = mListener

        return mDataBinding?.root
    }

    /**
     * ViewModelのセット
     * @param viewModel セットするViewModel
     */
    fun setViewModel(viewModel: BasicSettingViewModel) {
        mViewModel = viewModel
    }

    companion object {
        const val POSITION_USER_NAME = 0
        const val POSITION_ATTENDANCE_DATE = 1
        const val POSITION_ATTENDANCE_TIME = 2
        const val POSITION_BREAK_TIME = 3
        const val POSITION_TIME_UNIT = 4
        const val POSITION_SALARIED = 5


        /**
         * インスタンス生成. singleTon
         * @return fragmentインスタンス
         */
        fun newInstance(): BasicSettingFragment {
            return BasicSettingFragment()
        }
    }
}
