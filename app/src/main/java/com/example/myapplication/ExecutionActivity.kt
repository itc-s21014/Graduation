package com.example.myapplication

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityExecutionBinding
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ExecutionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExecutionBinding
    private lateinit var timeTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExecutionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        scheduleScreenTransition(this)

        timeTextView = findViewById(R.id.timeTextView)

        displayCurrentTime()
    }

    private fun displayCurrentTime() {

        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        val currentTime = sdf.format(Date())

        timeTextView.text = "$currentTime"
    }
}




    private fun scheduleScreenTransition(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, EndActivity::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val triggerTimeMillis = System.currentTimeMillis() + (5 * 60 * 1000) // 5分後に遷移

        alarmManager.set(AlarmManager.RTC_WAKEUP, triggerTimeMillis, pendingIntent)
    }