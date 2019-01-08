package com.kk.attendancemanagerapp.attendancemanagerapp.utils

import android.content.Context
import android.database.DatabaseUtils
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context): SQLiteOpenHelper(
    context, KeywordTable.FILE_NAME.value, null, DATABASE_VERSION) {

    // 出勤テーブルのテーブル名やカラム名などの定義
    enum class KeywordTable(val value: String) {
        FILE_NAME("attendance.db"),
        TABLE_NAME("attendance_table"),
        COLUMN_NAME_DATE("column_name_date"),
        COLUMN_NAME_START_TIME("column_name_keyword"),
        COLUMN_NAME_END_TIME("column_name_priority")
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        // DBファイルが存在しない場合にファイル作成する
        p0?.execSQL(SQL_CREATE_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        // DBのバージョンアップ時にDBファイルを再生成する
        p0?.execSQL(SQL_DROP_TABLE)
        onCreate(p0)
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // DBのバージョンダウン時にDBファイルを再生成する
        onUpgrade(db, oldVersion, newVersion)
    }

    /**
     * レコード数の取得
     * @param db        データベース
     * @param tableName テーブル
     * @return レコード数. テーブルが存在しない場合には0を返す
     */
    fun getRecordCount(db: SQLiteDatabase?, tableName: String): Long {
        var count: Long = 0L
        try {
            count = DatabaseUtils.queryNumEntries(db, tableName)
        } catch (e: SQLiteException) {
            Log.w(TAG, "No exist the keyword database in SQLite")
        }
        return count
    }

    companion object {
        private const val TAG = "DatabaseHelper"

        // テーブル作成コマンド
        private val SQL_CREATE_TABLE: String = "CREATE TABLE " + KeywordTable.TABLE_NAME.value + " (" +
                KeywordTable.COLUMN_NAME_DATE.value + " INTEGER PRIMARY KEY," +
                KeywordTable.COLUMN_NAME_START_TIME.value + " INTEGER," +
                KeywordTable.COLUMN_NAME_END_TIME.value + " INTEGER)"
        // テーブル削除コマンド
        private val SQL_DROP_TABLE: String = "DROP TABLE IF EXISTS " + KeywordTable.TABLE_NAME.value
        // DBのバージョン
        private const val DATABASE_VERSION: Int = 1
    }
}