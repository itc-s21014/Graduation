package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context): SQLiteOpenHelper(context, "Taskdata", null, 1) {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table Taskdata (id INTEGER PRIMARY KEY,name TEXT, time TEXT)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("drop table if exists Taskdata")
    }

    fun savetaskdata(name: String, time: String): Boolean {
        val p0 = this.writableDatabase
        val cv = ContentValues()

        cv.put("name", name)
        cv.put("time", time)
        val result = p0.insert("Taskdata", null, cv)
        if (result == -1L){
            return false
        }
        return true
    }

    fun updatetaskdata(id: Int, name: String, time: String): Boolean {
        val p0 = this.writableDatabase
        val cv = ContentValues()

        cv.put("name", name)
        cv.put("time", time)
        val cursor: Cursor = p0.rawQuery("select * from Taskdata where id = ?", arrayOf(id.toString()))
        if (cursor.count>0) {
            val result = p0.update("Taskdata", cv, "id=?", arrayOf(id.toString()))
            return result != -1
        }
        else{
            return false
        }
    }

    fun deletetaskdata(id: Int): Boolean {
        val p0 = this.writableDatabase
        val cursor: Cursor = p0.rawQuery("select * from Taskdata where id = ?", arrayOf(id.toString()))
        if (cursor.count > 0) {
            val result = p0.delete("Taskdata", "id=?", arrayOf(id.toString()))
            return result != -1
        }
        else{
            return false
        }
    }

    fun gettext(): Cursor? {
        val p0 = this.writableDatabase
        val cursor = p0.rawQuery("select * from Taskdata", null)
        return cursor
    }
}