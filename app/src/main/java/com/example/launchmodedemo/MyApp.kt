package com.example.launchmodedemo

import android.app.Application

class MyApp : Application() {
    override fun onCreate() {
        println("### MyApp#onCreate")
        super.onCreate()
    }
}