package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

class TaskSettingActivity3 : AppCompatActivity() {

    private lateinit var name: EditText
    private lateinit var time: EditText
    private lateinit var delete: ImageView
    private lateinit var edit: ImageView
    private lateinit var db: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_setting3)

        name = findViewById(R.id.editTextTaskName)
        time = findViewById(R.id.editTextTime)
        delete = findViewById(R.id.imageView3)
        edit = findViewById(R.id.imageView4)

        db = DBHelper(this)
        val id: Int = intent.getIntExtra("id", 0)

        name.setText(intent.getStringExtra("name"))
        time.setText(intent.getStringExtra("time"))

        delete.setOnClickListener {
            val intent = Intent(this, TaskSettingActivity::class.java)

            if (id != 0) {
                val deletedata = db.deletetaskdata(id)

                if (deletedata) {
                    Toast.makeText(this, "削除しました", Toast.LENGTH_SHORT).show()
                } else{
                    Toast.makeText(this, "削除できませんでした", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "IDが無効です", Toast.LENGTH_SHORT).show()
            }
            startActivity(intent)
        }

        edit.setOnClickListener {
            val intent = Intent(this, TaskSettingActivity::class.java)
            val names = name.text.toString()
            val times = time.text.toString()

            if (TextUtils.isEmpty(names) || TextUtils.isEmpty(times)) {
                Toast.makeText(this, "タスク名と時間を入力してください", Toast.LENGTH_SHORT).show()
            } else {
                val updatedata = db.updatetaskdata(id, names, times)

                if (updatedata) {
                    Toast.makeText(this, "更新しました", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "更新できませんでした", Toast.LENGTH_SHORT).show()
                }
                startActivity(intent)
            }
        }
    }
}