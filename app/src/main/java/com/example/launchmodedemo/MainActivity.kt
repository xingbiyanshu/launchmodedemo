package com.example.launchmodedemo

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Timer
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {

    var t: Timer?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        println("### MainActivity#onCreate")
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onNewIntent(intent: Intent) {
        println("### MainActivity#onNewIntent ${intent.toString()}")
        super.onNewIntent(intent)
    }

    override fun onResume() {
        println("### MainActivity#onResume")
        super.onResume()
        // 30s内消息处理前如果该activity销毁则会导致内存泄漏。
        // "adb shell dumpsys meminfo"命令可以观察到Activities数量。
//        Handler(Looper.getMainLooper()).postDelayed({
//            printHello()
//        }, 30*1000)

        t?.cancel()
    }

    override fun onStop() {
        println("### MainActivity#onStop")
        super.onStop()
        /*周期性创建通知，点击通知启动ThirdActivity，ThirdActivity作为通知信息展示页。
        若当前ThirdActivity处于top，则会导致ThirdActivity重复创建，所以ThirdActivity合理的启动模式是
        singleTop，这是singleTop的使用场景之一*/
        t =timer(period = 5000){
            createNotification()
        }
    }

    override fun onDestroy() {
        println("### MainActivity#onDestroy")
        super.onDestroy()
        t?.cancel()
    }

    fun onTextViewClicked(v: View){
        println("### MainActivity#onTextViewClicked")
        startActivity(Intent(this, SecondActivity::class.java))
    }

    fun printHello(){
        println("### MainActivity#printHello")
    }

    var count=0
    private fun createNotification() {
        println("### MainActivity#createNotification")
        val intent = Intent(this, ThirdActivity::class.java)
        intent.putExtra("count", count++)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "com.example.launchmodedemo"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, "main", NotificationManager.IMPORTANCE_HIGH)
            channel.setShowBadge(true)
            notificationManager.createNotificationChannel(channel)
        }
        val builder = NotificationCompat.Builder(this, channelId)
            .setContentTitle("重要通知")
            .setContentText("主界面发出通知")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setAutoCancel(true)
            .setNumber(999) // 自定义桌面通知数量
            .addAction(R.mipmap.ic_launcher, "去看看", pendingIntent)// 通知上的操作
            .setCategory(NotificationCompat.CATEGORY_MESSAGE) // 通知类别，"勿扰模式"时系统会决定要不要显示你的通知
        notificationManager.notify(1, builder.build())
    }

}