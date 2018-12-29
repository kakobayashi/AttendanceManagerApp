package com.kk.attendancemanagerapp.attendancemanagerapp.initialsetting

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.kk.attendancemanagerapp.attendancemanagerapp.R
import com.kk.attendancemanagerapp.attendancemanagerapp.data.resource.DataRepository
import com.kk.attendancemanagerapp.attendancemanagerapp.utils.ActivityUtil
import com.kk.attendancemanagerapp.attendancemanagerapp.utils.ViewModelHolder

class InitialSettingActivity : AppCompatActivity(), InitialSettingNavigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial_setting)

        setupToolbar()

        // fragmentを追加して取得する
        val fragment: InitialSettingFragment = findOrCreateViewFragment()

        // viewModelを生成
        val viewModel: InitialSettingViewModel = findOrCreateViewModel()
        // コールバック返却用にNavigatorをセットする
        viewModel.setNavigator(this)
    }

    /**
     * ツールバーのセットアップ
     */
    private fun setupToolbar() {
        val toolbar: Toolbar = findViewById(R.id.initial_setting_toolbar)
        setSupportActionBar(toolbar)
    }

    /**
     * Fragmentを生成し、そのFragmentを返す
     * @return 追加したフラグメント
     */
    private fun findOrCreateViewFragment(): InitialSettingFragment {
        var initialSettingFragment
                = supportFragmentManager.findFragmentById(R.id.content_frame) as InitialSettingFragment?
        if (initialSettingFragment == null) {
            initialSettingFragment = InitialSettingFragment.newInstance()

            ActivityUtil.addFragmentToActivity(supportFragmentManager,
                initialSettingFragment, R.id.content_frame)
        }

        return initialSettingFragment
    }

    /**
     * ViewModelを生成して、そのViewModelを返す
     * @return 追加したViewModel
     */
    @SuppressWarnings("unchecked")
    private fun findOrCreateViewModel(): InitialSettingViewModel {
        val retainedViewModel: ViewModelHolder<InitialSettingViewModel>? =
            supportFragmentManager.findFragmentByTag(TAG_VIEWMODEL_INITIAL_SETTING)
                    as ViewModelHolder<InitialSettingViewModel>?

        return if (retainedViewModel != null || retainedViewModel?.getViewModel() != null) {
            retainedViewModel.getViewModel()!!
        } else {
            val viewModel = InitialSettingViewModel(
                DataRepository.getInstance(), applicationContext)
            // 画面に反映
            ActivityUtil.addFragmentToActivity(supportFragmentManager,
                ViewModelHolder.createContainer(viewModel), TAG_VIEWMODEL_INITIAL_SETTING)
            viewModel
        }
    }

    companion object {
        const val TAG_VIEWMODEL_INITIAL_SETTING = "TAG_VIEWMODEL_INITIAL_SETTING"
    }
}
