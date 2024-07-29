package com.example.launchmodedemo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val count = intent.getIntExtra("count", 0)
        println("### ThirdActivity#onCreate $count")
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_third)
        val tv = findViewById<TextView>(R.id.tv)
        tv.setText("ThirdActivity $count")
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onNewIntent(intent: Intent) {
        println("### ThirdActivity#onNewIntent ${intent.toString()}")
        super.onNewIntent(intent)
    }

    override fun onResume() {
        println("### ThirdActivity#onResume")
        super.onResume()
    }

    override fun onStop() {
        println("### ThirdActivity#onStop")
        super.onStop()
    }

    override fun onDestroy() {
        println("### ThirdActivity#onDestroy")
        super.onDestroy()
    }

    fun onTextViewClicked(v: View){
        println("### ThirdActivity#onTextViewClicked")
        startActivity(Intent(this, MainActivity::class.java))
    }
}