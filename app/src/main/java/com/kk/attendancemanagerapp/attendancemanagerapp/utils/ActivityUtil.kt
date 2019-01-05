package com.kk.attendancemanagerapp.attendancemanagerapp.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction


class ActivityUtil {

    companion object {
        /**
         * ActiivtyにFragmentを追加
         * @param fragmentManager フラグメントマネージャー
         * @param fragment        追加するフラグメント
         * @param frameId         追加場所のレイアウトID
         */
        fun addFragmentToActivity(fragmentManager: FragmentManager,
                                  fragment: Fragment?, frameId: Int) {
            val transaction: FragmentTransaction = fragmentManager.beginTransaction()
            transaction.add(frameId, fragment)
            transaction.commit()
        }

        /**
         * ActiivtyにFragmentを追加
         * @param fragmentManager フラグメントマネージャー
         * @param fragment        追加するフラグメント
         * @param tag             追加するフラグメントタグ
         */
        fun addFragmentToActivity(fragmentManager: FragmentManager,
                                  fragment: Fragment, tag: String) {
            val transaction: FragmentTransaction = fragmentManager.beginTransaction()
            transaction.add(fragment, tag)
            transaction.commit()
        }
    }
}