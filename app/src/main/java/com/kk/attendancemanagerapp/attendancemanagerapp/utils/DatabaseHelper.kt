package com.kk.attendancemanagerapp.attendancemanagerapp.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context): SQLiteOpenHelper(
    context, AttendanceTable.FILE_NAME.value, null, DATABASE_VERSION) {

    // 出勤テーブルのテーブル名やカラム名などの定義
    enum class AttendanceTable(val value: String) {
        FILE_NAME("attendance.db"),
        TABLE_NAME("attendance_table"),
        COLUMN_NAME_DATE("column_name_date"),
        COLUMN_NAME_ATTENDANCE_TIME("column_name_attendance_time"),
        COLUMN_NAME_START_TIME("column_name_start_time"),
        COLUMN_NAME_END_TIME("column_name_end_time")
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

    companion object {
        private const val TAG = "DatabaseHelper"

        // テーブル作成コマンド
        private val SQL_CREATE_TABLE: String = "CREATE TABLE " + AttendanceTable.TABLE_NAME.value + " (" +
                AttendanceTable.COLUMN_NAME_DATE.value + " TEXT PRIMARY KEY," +
                AttendanceTable.COLUMN_NAME_ATTENDANCE_TIME.value + " TEXT," +
                AttendanceTable.COLUMN_NAME_START_TIME.value + " INTEGER," +
                AttendanceTable.COLUMN_NAME_END_TIME.value + " INTEGER)"
        // テーブル削除コマンド
        private val SQL_DROP_TABLE: String = "DROP TABLE IF EXISTS " + AttendanceTable.TABLE_NAME.value
        // DBのバージョン
        private const val DATABASE_VERSION: Int = 1
    }
}