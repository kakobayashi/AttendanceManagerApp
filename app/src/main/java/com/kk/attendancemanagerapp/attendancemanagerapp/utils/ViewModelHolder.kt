package com.kk.attendancemanagerapp.attendancemanagerapp.utils

import android.os.Bundle
import android.support.v4.app.Fragment


class ViewModelHolder<VM> : Fragment() {

    var mViewModel: VM? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 画面回転などでFragmentを再生成させない
        retainInstance = true
    }

    /**
     * ViewHolderのセット
     * @param viewModel セットするViewModel
     */
    fun setViewModel(viewModel: VM) {
        mViewModel = viewModel
    }

    /**
     * ViewHolderの取得
     * @return ViewModel
     */
    fun getViewModel(): VM? {
        return mViewModel
    }

    companion object {
        fun <M> createContainer(viewModel: M): ViewModelHolder<*> {
            val viewModelContainer = ViewModelHolder<M>()
            viewModelContainer.setViewModel(viewModel)
            return viewModelContainer
        }
    }
}