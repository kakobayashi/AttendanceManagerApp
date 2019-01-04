package com.kk.attendancemanagerapp.attendancemanagerapp.basicsetting

import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.kk.attendancemanagerapp.attendancemanagerapp.R
import com.kk.attendancemanagerapp.attendancemanagerapp.attendance.AttendanceActivity
import com.kk.attendancemanagerapp.attendancemanagerapp.data.resource.DataRepository
import com.kk.attendancemanagerapp.attendancemanagerapp.utils.ActivityUtil
import com.kk.attendancemanagerapp.attendancemanagerapp.utils.ViewModelHolder

class BasicSettingActivity : AppCompatActivity(), BasicSettingNavigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_setting)

        // ツールバーの設定
        setupToolbar()

        // Fragment取得
        val fragment: BasicSettingFragment = findOrCreateViewFragment()

        // ViewModel取得
        val viewModel: BasicSettingViewModel = findOrCreateViewModel()

        // fragmentにviewModelをセット
        fragment.setViewModel(viewModel)

        // コールバック返却用にNavigatorをセットする
        viewModel.setNavigator(this)
    }

    /**
     * ツールバーのセットアップ
     */
    private fun setupToolbar() {
        val toolbar: Toolbar = findViewById(R.id.basic_setting_toolbar)
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
    private fun findOrCreateViewFragment(): BasicSettingFragment {
        var basicSettingFragment
                = supportFragmentManager.findFragmentById(R.id.content_frame) as BasicSettingFragment?
        if (basicSettingFragment == null) {
            basicSettingFragment = BasicSettingFragment.newInstance()

            ActivityUtil.addFragmentToActivity(supportFragmentManager,
                basicSettingFragment, R.id.content_frame)
        }

        return basicSettingFragment
    }

    /**
     * ViewModelを生成して、そのViewModelを返す
     * @return 追加したViewModel
     */
    @SuppressWarnings("unchecked")
    private fun findOrCreateViewModel(): BasicSettingViewModel {
        val retainedViewModel: ViewModelHolder<BasicSettingViewModel>? =
            supportFragmentManager.findFragmentByTag(TAG_VIEWMODEL_BASIC_SETTING)
                    as ViewModelHolder<BasicSettingViewModel>?

        return if (retainedViewModel != null || retainedViewModel?.getViewModel() != null) {
            retainedViewModel.getViewModel()!!
        } else {
            val viewModel = BasicSettingViewModel(
                DataRepository.getInstance(), applicationContext)
            // 画面に反映
            ActivityUtil.addFragmentToActivity(supportFragmentManager,
                ViewModelHolder.createContainer(viewModel),
                AttendanceActivity.TAG_VIEWMODEL_ATTENDANCE
            )
            viewModel
        }
    }

    companion object {
        const val TAG_VIEWMODEL_BASIC_SETTING: String = "tag_viewmodel_basic_setting"
    }
}
