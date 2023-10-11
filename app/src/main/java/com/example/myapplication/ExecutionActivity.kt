package com.example.myapplication

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ExecutionActivity : AppCompatActivity() {
    private lateinit var timeTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_execution)

        scheduleScreenTransition(this)

        val timeTextView = findViewById<TextView>(R.id.timeTextView)

        val startTime = Date()
        val endTime = Date(System.currentTimeMillis() + 3600000)
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        val startTimeStr = timeFormat.format(startTime)
        val endTimeStr = timeFormat.format(endTime)

        val timeRangeText = "$startTimeStr - $endTimeStr"
        timeTextView.text = timeRangeText

        displayCurrentTime()
    }

    private fun displayCurrentTime() {

        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        val currentTime = sdf.format(Date())

        timeTextView.text = "$currentTime"
    }

    private fun scheduleScreenTransition(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, EndActivity::class.java)
        val pendingIntent =
            PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val triggerTimeMillis = System.currentTimeMillis() + (5 * 60 * 1000) // 5分後に遷移

        alarmManager.set(AlarmManager.RTC_WAKEUP, triggerTimeMillis, pendingIntent)
    }
}