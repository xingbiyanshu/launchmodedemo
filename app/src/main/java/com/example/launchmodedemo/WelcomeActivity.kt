package com.example.launchmodedemo

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        println("### WelcomeActivity#onCreate")
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onNewIntent(intent: Intent) {
        println("### WelcomeActivity#onNewIntent ${intent.toString()}")
        super.onNewIntent(intent)
    }

    override fun onResume() {
        println("### WelcomeActivity#onResume")
        super.onResume()
    }

    override fun onStop() {
        println("### WelcomeActivity#onStop")
        super.onStop()
    }

    override fun onDestroy() {
        println("### WelcomeActivity#onDestroy")
        super.onDestroy()
    }

    fun onTextViewClicked(v: View){
        println("### WelcomeActivity#onTextViewClicked")
        startActivity(Intent(this, MainActivity::class.java))
    }
}