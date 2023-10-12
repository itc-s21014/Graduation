package com.example.myapplication

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityExecutionBinding
import android.widget.TextView
import kotlinx.coroutines.Runnable
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ExecutionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExecutionBinding
    private lateinit var timeTextView: TextView
    private lateinit var taskTextView: TextView
    private val handler = Handler(Looper.getMainLooper())
    private val taskList = ArrayList<Pair<String, String>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExecutionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        timeTextView = findViewById(R.id.timeTextView)

        taskList.add(Pair("10:00 AM - 11:00 AM", "支度のタスク1"))
        taskList.add(Pair("11:00 AM - 12:00 PM", "支度のタスク2"))

        taskTextView = findViewById(R.id.textView7)

        scheduleScreenTransition(this)

        displayCurrentTime()

        updateRealTime()

        updateTask()

        val updateTimeDelayMillis: Int = 1000 * 60
        handler.postDelayed(object : Runnable {
            override fun run() {
                updateTask()
                handler.postDelayed(this, updateTimeDelayMillis.toLong())
            }
        }, updateTimeDelayMillis.toLong())
    }

    private fun updateTask() {
        val currentTime = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date())
        val task = getTaskForTime(currentTime)
        taskTextView.text = task
    }

    private fun getTaskForTime(time: String): String {
        for (pair in taskList) {
            if (pair.first == time) {
                return pair.second
            }
        }
        return "該当するタスクがありません"
    }

    private fun displayCurrentTime() {

        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        val currentTime = sdf.format(Date())

        timeTextView.text = "$currentTime"
    }

    private fun updateRealTime() {
        val timeRangeTextView = findViewById<TextView>(R.id.timeRangeTextView)
        val textView7 = findViewById<TextView>(R.id.textView7)

        handler.post(object : Runnable {
            override fun run() {
                val currentTime = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date())

                timeRangeTextView.text = currentTime
                textView7.text = "$currentTime"

                handler.postDelayed(this, 1000)
            }
        })
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