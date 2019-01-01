package com.kk.attendancemanagerapp.attendancemanagerapp.attendance

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.kk.attendancemanagerapp.attendancemanagerapp.R
import com.kk.attendancemanagerapp.attendancemanagerapp.data.resource.DataRepository
import com.kk.attendancemanagerapp.attendancemanagerapp.utils.ActivityUtil
import com.kk.attendancemanagerapp.attendancemanagerapp.utils.ViewModelHolder

class AttendanceActivity : AppCompatActivity(), AttendanceNavigator {

    private lateinit var mDrawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attendance)

        // ツールバーの設定
        setupToolbar()

        // ナビゲーションドロワーの設定
        setupNavigationDrawer()

        // Fragment取得
        val fragment: AttendanceFragment = findOrCreateViewFragment()

        // ViewModel取得
        val viewModel: AttendanceViewModel = findOrCreateViewModel()

        // fragmentにviewModelをセット
        fragment.setViewModel(viewModel)

        // コールバック返却用にNavigatorをセットする
        viewModel.setNavigator(this)
    }

    /**
     * ツールバーのセットアップ
     */
    private fun setupToolbar() {
        val toolbar: Toolbar = findViewById(R.id.attendance_toolbar)
        setSupportActionBar(toolbar)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
    }

    /**
     * ナビゲーションドロワーのセットアップ
     */
    private fun setupNavigationDrawer() {
        mDrawerLayout = findViewById(R.id.attendance_drawer)
        mDrawerLayout.setStatusBarBackground(R.color.colorPrimary)
        val navigationView = findViewById<NavigationView>(R.id.navigation_drawer)
        if (navigationView != null) {
            setupDrawerContent(navigationView)
        }
    }

    /**
     * ナビゲーションドロワーのコンテンツ設定
     */
    private fun setupDrawerContent(navigationView: NavigationView) {
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.list_navigation_menu_basic_setting -> {
                }
                R.id.list_navigation_menu_change_templete -> {
                }
                R.id.list_navigation_menu_edit_history -> {
                }
            }
            mDrawerLayout.closeDrawers()
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                // Open the navigation drawer when the home icon is selected from the toolbar.
                mDrawerLayout.openDrawer(GravityCompat.START)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * Fragmentを生成し、そのFragmentを返す
     * @return 追加したフラグメント
     */
    private fun findOrCreateViewFragment(): AttendanceFragment {
        var attendanceFragment
                = supportFragmentManager.findFragmentById(R.id.content_frame) as AttendanceFragment?
        if (attendanceFragment == null) {
            attendanceFragment = AttendanceFragment.newInstance()

            ActivityUtil.addFragmentToActivity(supportFragmentManager,
                attendanceFragment, R.id.content_frame)
        }

        return attendanceFragment
    }

    /**
     * ViewModelを生成して、そのViewModelを返す
     * @return 追加したViewModel
     */
    @SuppressWarnings("unchecked")
    private fun findOrCreateViewModel(): AttendanceViewModel {
        val retainedViewModel: ViewModelHolder<AttendanceViewModel>? =
            supportFragmentManager.findFragmentByTag(TAG_VIEWMODEL_ATTENDANCE)
                    as ViewModelHolder<AttendanceViewModel>?

        return if (retainedViewModel != null || retainedViewModel?.getViewModel() != null) {
            retainedViewModel.getViewModel()!!
        } else {
            val viewModel = AttendanceViewModel(
                DataRepository.getInstance(), applicationContext)
            // 画面に反映
            ActivityUtil.addFragmentToActivity(supportFragmentManager,
                ViewModelHolder.createContainer(viewModel), TAG_VIEWMODEL_ATTENDANCE)
            viewModel
        }
    }

    companion object {
        const val TAG_VIEWMODEL_ATTENDANCE: String = "tag_viewmodel_attendance"
    }
}
