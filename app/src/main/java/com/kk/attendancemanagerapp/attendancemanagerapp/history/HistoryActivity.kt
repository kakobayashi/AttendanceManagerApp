package com.kk.attendancemanagerapp.attendancemanagerapp.history

import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.kk.attendancemanagerapp.attendancemanagerapp.R
import com.kk.attendancemanagerapp.attendancemanagerapp.data.resource.DataRepository
import com.kk.attendancemanagerapp.attendancemanagerapp.utils.ActivityUtil
import com.kk.attendancemanagerapp.attendancemanagerapp.utils.ViewModelHolder

class HistoryActivity : AppCompatActivity(), HistoryNavigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        // ツールバーの設定
        setupToolbar()

        // Fragment取得
        val fragment: HistoryFragment = findOrCreateViewFragment()

        // ViewModel取得
        val viewModel: HistoryViewModel = findOrCreateViewModel()

        // fragmentにviewModelをセット
        fragment.setViewModel(viewModel)

        // コールバック返却用にNavigatorをセットする
        viewModel.setNavigator(this)

    }

    /**
     * ツールバーのセットアップ
     */
    private fun setupToolbar() {
        val toolbar: Toolbar = findViewById(R.id.history_toolbar)
        setSupportActionBar(toolbar)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_keyboard_backspace_24px)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * Fragmentを生成し、そのFragmentを返す
     * @return 追加したフラグメント
     */
    private fun findOrCreateViewFragment(): HistoryFragment {
        var hisotoryFragment
                = supportFragmentManager.findFragmentById(R.id.content_frame) as HistoryFragment?
        if (hisotoryFragment == null) {
            hisotoryFragment = HistoryFragment.newInstance()

            ActivityUtil.addFragmentToActivity(supportFragmentManager,
                hisotoryFragment, R.id.content_frame)
        }

        return hisotoryFragment
    }

    /**
     * ViewModelを生成して、そのViewModelを返す
     * @return 追加したViewModel
     */
    @SuppressWarnings("unchecked")
    private fun findOrCreateViewModel(): HistoryViewModel {
        val retainedViewModel: ViewModelHolder<HistoryViewModel>? =
            supportFragmentManager.findFragmentByTag(TAG_VIEWMODEL_HISTORY)
                    as ViewModelHolder<HistoryViewModel>?

        return if (retainedViewModel != null || retainedViewModel?.getViewModel() != null) {
            retainedViewModel.getViewModel()!!
        } else {
            val viewModel = HistoryViewModel(
                DataRepository.getInstance(), applicationContext)
            // 画面に反映
            ActivityUtil.addFragmentToActivity(supportFragmentManager,
                ViewModelHolder.createContainer(viewModel), TAG_VIEWMODEL_HISTORY)
            viewModel
        }
    }

    companion object {
        const val TAG_VIEWMODEL_HISTORY: String = "tag_viewmodel_history"
    }
}
