package com.example.launchmodedemo

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        println("### SecondActivity#onCreate")
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onNewIntent(intent: Intent) {
        println("### SecondActivity#onNewIntent ${intent.toString()}")
        super.onNewIntent(intent)
    }

    override fun onResume() {
        println("### SecondActivity#onResume")
        super.onResume()
    }

    override fun onStop() {
        println("### SecondActivity#onStop")
        super.onStop()
    }

    override fun onDestroy() {
        println("### SecondActivity#onDestroy")
        super.onDestroy()
    }

    fun onTextViewClicked(v: View){
        println("### SecondActivity#onTextViewClicked")
        startActivity(Intent(this, ThirdActivity::class.java))
    }
}