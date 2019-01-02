package com.kk.attendancemanagerapp.attendancemanagerapp.initialsetting

import android.content.Context
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.text.TextUtils
import com.kk.attendancemanagerapp.attendancemanagerapp.data.resource.DataRepository
import com.kk.attendancemanagerapp.attendancemanagerapp.utils.TimePickerDialogUtil

class InitialSettingViewModel(repository: DataRepository?, context: Context)  {

    private val mContext: Context = context

    private var mNavigator: InitialSettingNavigator? = null

    private val mRepository: DataRepository? = repository

    // ユーザー名 editTextは自動的に動的にセットしてくれるためNextButtonを押した時にいれるだけでよさそう
    var mUserName: String? = null
    var mAttendanceTime: ObservableField<String>? = ObservableField()
    var mBreakStart: ObservableField<String>? = ObservableField()
    var mBreakEnd: ObservableField<String>? = ObservableField()
    var mSalaried: String? = null

    var mIsIncompleteText: ObservableBoolean = ObservableBoolean()

    var mSelectMonday: ObservableBoolean = ObservableBoolean()
    var mSelectTuesday: ObservableBoolean = ObservableBoolean()
    var mSelectWednesday: ObservableBoolean = ObservableBoolean()
    var mSelectThursday: ObservableBoolean = ObservableBoolean()
    var mSelectFriday: ObservableBoolean = ObservableBoolean()
    var mSelectSaturday: ObservableBoolean = ObservableBoolean()
    var mSelectSunday: ObservableBoolean = ObservableBoolean()

    var mSelectMinute: ObservableBoolean = ObservableBoolean()
    var mSelectFifteen: ObservableBoolean = ObservableBoolean()
    var mSelectThirty: ObservableBoolean = ObservableBoolean()

    /**
     * コールバック用Navigatorの登録
     * ViewModel -> Activity へコールバックを返す
     */
    fun setNavigator(navigator: InitialSettingNavigator) {
        mNavigator = navigator
    }

    /**
     * NextButtonタップ時の処理
     */
    fun onClickNextButton() {
        if (!TextUtils.isEmpty(mUserName) && isSelectAttendanceDate() && mAttendanceTime != null &&
            mBreakStart != null && mBreakEnd != null && isSelectTimeUnit() &&
            !TextUtils.isEmpty(mSalaried)) {
            // view側へコールバックを返す
            mNavigator?.onComplete()
            mIsIncompleteText.set(false)

            // 初期設定完了済みフラグをたてる
            mRepository?.setCompleteInitialSetting(mContext.getSharedPreferences(
                DataRepository.KEY_PREFERENCE_SETTING_DATA, Context.MODE_PRIVATE))

            // 設定データを保存
            mRepository?.saveSettingData(mContext.getSharedPreferences(
                DataRepository.KEY_PREFERENCE_SETTING_DATA, Context.MODE_PRIVATE), mUserName,
                getAttendanceDay(), mAttendanceTime?.get(), mBreakStart?.get(), mBreakEnd?.get(),
                getTimeUnit(), mSalaried)
        } else {
            // すべて入力されていない場合は警告テキストを表示
            mIsIncompleteText.set(true)
        }
    }

    /**
     * 月曜日ボタンタップ時の処理
     */
    fun onClickMondayButton() {
        if (mSelectMonday.get()) {
            mSelectMonday.set(false)
        } else {
            mSelectMonday.set(true)
        }
    }

    /**
     * 火曜日ボタンタップ時の処理
     */
    fun onClickTuesdayButton() {
        if (mSelectTuesday.get()) {
            mSelectTuesday.set(false)
        } else {
            mSelectTuesday.set(true)
        }
    }

    /**
     * 水曜日ボタンタップ時の処理
     *
     */
    fun onClickWednesdayButton() {
        if (mSelectWednesday.get()) {
            mSelectWednesday.set(false)
        } else {
            mSelectWednesday.set(true)
        }
    }

    /**
     * 木曜日ボタンタップ時の処理
     */
    fun onClickThursdayButton() {
        if (mSelectThursday.get()) {
            mSelectThursday.set(false)
        } else {
            mSelectThursday.set(true)
        }
    }

    /**
     * 金曜日ボタンタップ時の処理
     */
    fun onClickFridayButton() {
        if (mSelectFriday.get()) {
            mSelectFriday.set(false)
        } else {
            mSelectFriday.set(true)
        }
    }

    /**
     * 土曜日ボタンタップ時の処理
     */
    fun onClickSaturdayButton() {
        if (mSelectSaturday.get()) {
            mSelectSaturday.set(false)
        } else {
            mSelectSaturday.set(true)
        }
    }

    /**
     * 日曜日ボタンタップ時の処理
     */
    fun onClickSundayButton() {
        if (mSelectSunday.get()) {
            mSelectSunday.set(false)
        } else {
            mSelectSunday.set(true)
        }
    }

    /**
     * 出勤日が選択済みか
     * @return true: 選択済み
     */
    fun isSelectAttendanceDate(): Boolean {
        return mSelectMonday.get() || mSelectTuesday.get() || mSelectWednesday.get() ||
                mSelectThursday.get() || mSelectFriday.get() || mSelectSaturday.get() ||
                mSelectSunday.get()
    }

    /**
     * 1分ボタンタップ時の処理
     */
    fun onClickMinuteButton() {
        if (mSelectMinute.get()) {
            mSelectMinute.set(false)
        } else {
            mSelectMinute.set(true)
            mSelectFifteen.set(false)
            mSelectThirty.set(false)
        }
    }

    /**
     * 15分ボタンタップ時の処理
     */
    fun onClickFifteenButton() {
        if (mSelectFifteen.get()) {
            mSelectFifteen.set(false)
        } else {
            mSelectMinute.set(false)
            mSelectFifteen.set(true)
            mSelectThirty.set(false)
        }
    }

    /**
     * 30分ボタンタップ時の処理
     */
    fun onClickThirtyButton() {
        if (mSelectThirty.get()) {
            mSelectThirty.set(false)
        } else {
            mSelectMinute.set(false)
            mSelectFifteen.set(false)
            mSelectThirty.set(true)
        }
    }

    /**
     * 時間単位の取得
     * @return 時間単位
     */
    private fun getTimeUnit(): Int {
        return when {
            mSelectMinute.get() -> 1
            mSelectFifteen.get() -> 15
            else -> 30
        }
    }

    /**
     * 時間単位が選択済みか
     * @return true: 選択済み
     */
    fun isSelectTimeUnit(): Boolean {
        return mSelectMinute.get() || mSelectFifteen.get() || mSelectThirty.get()
    }

    /**
     * 出勤日の取得
     * @return 出勤日 0:OFF, 1:ON
     */
    private fun getAttendanceDay(): String {
        val buffer = StringBuffer()

        TimePickerDialogUtil.appendAttendanceDate(buffer, mSelectMonday)
        TimePickerDialogUtil.appendAttendanceDate(buffer, mSelectTuesday)
        TimePickerDialogUtil.appendAttendanceDate(buffer, mSelectWednesday)
        TimePickerDialogUtil.appendAttendanceDate(buffer, mSelectThursday)
        TimePickerDialogUtil.appendAttendanceDate(buffer, mSelectFriday)
        TimePickerDialogUtil.appendAttendanceDate(buffer, mSelectSaturday)
        TimePickerDialogUtil.appendAttendanceDate(buffer, mSelectSunday)

        return buffer.toString()
    }

    /**
     * 初期設定完了フラグをたてる
     */
    fun setCompleteInitialSetting() {
        mRepository?.setCompleteInitialSetting(mContext.getSharedPreferences(
            DataRepository.KEY_PREFERENCE_SETTING_DATA, Context.MODE_PRIVATE))
    }

    /**
     * 初期設定完了済みか
     */
    fun isCompleteInitialSetting(): Boolean? {
        return mRepository?.getCompleteInitialSetting(mContext.getSharedPreferences(
            DataRepository.KEY_PREFERENCE_SETTING_DATA, Context.MODE_PRIVATE))
    }
}