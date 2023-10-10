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
    var addName: EditText? = null
    var addAge: EditText? = null
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
        addName = findViewById<View>(R.id.addName) as EditText
        addAge = findViewById<View>(R.id.addAge) as EditText
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
            `val`.put("name", addName!!.text.toString())
            `val`.put("age", addAge!!.text.toString())
            db.insert("person", null, `val`)
        }

        //データの更新
        updateBtn!!.setOnClickListener { //nameとageを取得
            val name = addName!!.text.toString()
            val age = addAge!!.text.toString()

            //ContentValuesに値を入れる
            val `val` = ContentValues()
            `val`.put("age", age)

            //データベースを更新
            db.update("person", `val`, "name=?", arrayOf(name))
        }

        //データの削除
        deleteBtn!!.setOnClickListener { //名前を取得
            val name = addName!!.text.toString()

            //ContentValuesに値を入れる
            val `val` = ContentValues()
            `val`.put("name", name)

            //データベースを更新
            db.delete("person", "name=?", arrayOf(name))
        }

        //データの全削除
        deleteAllBtn!!.setOnClickListener { db.delete("person", null, null) }

        //データを表示
        showBtn!!.setOnClickListener { //TextViewをリセットする
            disp!!.setText(null)
            val c = db.query(
                "person",
                arrayOf("name", "age"),
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