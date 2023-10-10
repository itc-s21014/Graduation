package com.example.myapplication

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


/**
 * Created by orisa on 2017/05/31.
 */
class MyDbHelper(context: Context?) : SQLiteOpenHelper(context, DBNAME, null, version) {

    companion object {
        private const val DBNAME = "DBSample.splite"
        private const val version = 1
    }
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE person(name TEXT NOT NULL, age TEXT);")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {}
}