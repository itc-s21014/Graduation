package com.example.myapplication

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class TaskSettingActivity : AppCompatActivity() {
    var addTask: EditText? = null
    var addTime: EditText? = null
    var addBtn: Button? = null
    var updateBtn: Button? = null
    var deleteBtn: Button? = null
    var deleteAllBtn: Button? = null
    var showBtn: Button? = null
    var disp: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_setting)

        //部品の取得
        addTask = findViewById<View>(R.id.addTask) as EditText
        addTime = findViewById<View>(R.id.addTime) as EditText
        addBtn = findViewById<View>(R.id.addBtn) as Button
        updateBtn = findViewById<View>(R.id.updateBtn) as Button
        deleteBtn = findViewById<View>(R.id.deleteBtn) as Button
        deleteAllBtn = findViewById<View>(R.id.deleteAllBtn) as Button
        showBtn = findViewById<View>(R.id.showBtn) as Button
        disp = findViewById<View>(R.id.disp) as TextView

        //データベースの生成
        val hlp = MyDbHelper(this)
        val db: SQLiteDatabase = hlp.getReadableDatabase() //finalをつけるのに注意

        //データを追加
        addBtn!!.setOnClickListener {
            val `val` = ContentValues()
            `val`.put("task", addTask!!.text.toString())
            `val`.put("time", addTime!!.text.toString())
            db.insert("tasktable", null, `val`)
        }

        //データの更新
        updateBtn!!.setOnClickListener { //nameとageを取得
            val task = addTask!!.text.toString()
            val time = addTime!!.text.toString()

            //ContentValuesに値を入れる
            val `val` = ContentValues()
            `val`.put("time", time)

            //データベースを更新
            db.update("tasktable", `val`, "task=?", arrayOf(task))
        }

        //データの削除
        deleteBtn!!.setOnClickListener { //名前を取得
            val task = addTask!!.text.toString()

            //ContentValuesに値を入れる
            val `val` = ContentValues()
            `val`.put("task", task)

            //データベースを更新
            db.delete("tasktable", "task=?", arrayOf(task))
        }

        //データの全削除
        deleteAllBtn!!.setOnClickListener { db.delete("tasktable", null, null) }

        //データを表示
        showBtn!!.setOnClickListener { //TextViewをリセットする
            disp!!.setText(null)
            val c = db.query(
                "tasktable",
                arrayOf("task", "time"),
                null,
                null,
                null,
                null,
                null
            )
            var mov = c.moveToFirst()
            while (mov) {
                disp!!.append(
                    """
                        ${c.getString(0)}: ${c.getInt(1)}
                        
                        """.trimIndent()
                )
                mov = c.moveToNext()
            }
            c.close()
        }
    }
}