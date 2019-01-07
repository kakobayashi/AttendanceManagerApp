package com.kk.attendancemanagerapp.attendancemanagerapp.history

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import com.kk.attendancemanagerapp.attendancemanagerapp.R
import com.kk.attendancemanagerapp.attendancemanagerapp.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {

    private lateinit var mViewModel: HistoryViewModel

    private var mDataBinding: FragmentHistoryBinding? = null

    private var mListView: ListView? = null

    private var mListener: AdapterView.OnItemClickListener =
        AdapterView.OnItemClickListener { parent, view, position, id ->
            // do nothing.
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val root: View? = inflater.inflate(R.layout.fragment_history, container, false)

        // bind
        if (mDataBinding == null) {
            mDataBinding = FragmentHistoryBinding.bind(root!!)
        }

        // リストビューにアダプターを設定する
        mListView = root?.findViewById(R.id.history_list)

        return mDataBinding?.root
    }

    /**
     * ViewModelのセット
     * @param viewModel セットするViewModel
     */
    fun setViewModel(viewModel: HistoryViewModel) {
        mViewModel = viewModel
    }

    companion object {
        /**
         * インスタンス生成. singleTon
         * @return fragmentインスタンス
         */
        fun newInstance(): HistoryFragment {
            return HistoryFragment()
        }
    }
}
