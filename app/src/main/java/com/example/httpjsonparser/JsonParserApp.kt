package com.example.httpjsonparser

import android.app.Application
import com.example.httpjsonparser.managers.ApiManager

class JsonParserApp: Application() {

    var count = 0
    lateinit var apiManager: ApiManager

    override fun onCreate() {
        super.onCreate()

        apiManager = ApiManager(this)
    }
}
