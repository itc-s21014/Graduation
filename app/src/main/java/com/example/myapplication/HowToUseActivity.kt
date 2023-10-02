package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityHowToUseBinding

class HowToUseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHowToUseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHowToUseBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}