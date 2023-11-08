package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class TaskSettingActivity2 : AppCompatActivity() {

    private lateinit var name: TextInputEditText
    private lateinit var time: TextInputEditText
    private lateinit var save: Button
    private lateinit var db: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_setting2)

        name = findViewById(R.id.textedit)
        time = findViewById(R.id.textedit2)
        save = findViewById(R.id.button)
        db = DBHelper(this)
        val id = intent.getIntExtra("id", 0)

        save.setOnClickListener {
            val names = name.text.toString()
            val times = time.text.toString()
            if (TextUtils.isEmpty(names) || TextUtils.isEmpty(times)){
                Toast.makeText(this, "タスク名と時間を入力してください", Toast.LENGTH_SHORT).show()
            } else{
                val savedata = db.savetaskdata(names, times)
                if (savedata){
                    Toast.makeText(this, "保存しました", Toast.LENGTH_SHORT).show()
                } else{
                    Toast.makeText(this, "保存できませんでした", Toast.LENGTH_SHORT).show()
                }
            }
            val intent = Intent(this, TaskSettingActivity::class.java)
            startActivity(intent)

        }
    }
}