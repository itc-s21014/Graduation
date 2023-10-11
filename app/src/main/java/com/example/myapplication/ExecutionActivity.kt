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
import android.os.Handler

class ExecutionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExecutionBinding
    private lateinit var timeTextView: TextView
    private val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    private val handler = Handler()
    private lateinit var updateTimeRunnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExecutionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        timeTextView = binding.timeTextView
        updateTimeRunnable = Runnable { updateClock() }

        scheduleScreenTransition(this)

        timeTextView = findViewById(R.id.timeTextView)

        displayCurrentTime()
    }
    override fun onResume() {
        super.onResume()
        // アクティビティがフォアグラウンドにある間、1秒ごとに時刻を更新
        handler.postDelayed(updateTimeRunnable, 1000)
    }
    override fun onPause() {
        super.onPause()
        // アクティビティがバックグラウンドにあるときに更新を停止
        handler.removeCallbacks(updateTimeRunnable)
    }
    private fun updateClock() {
        val currentTime = dateFormat.format(Date())
        timeTextView.text = "$currentTime"
        handler.postDelayed(updateTimeRunnable, 1000) // 1秒ごとに更新
    }

    private fun displayCurrentTime() {

        val sdf = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
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