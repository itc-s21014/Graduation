package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityTimeSettingBinding

class TimeSettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTimeSettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimeSettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.start.setOnClickListener {
            val intent = Intent(this, ExecutionActivity::class.java)
            startActivity(intent)
        }
    }
}