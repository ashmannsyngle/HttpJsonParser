package com.example.httpjsonparser.managers

import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.httpjsonparser.model.User
import com.google.gson.Gson

class ApiManager(context: Context) {

    private val queue: RequestQueue = Volley.newRequestQueue(context)
    private val ctx: Context = context

    fun getUser(onUserReady: (User) -> Unit) {
        val userURL = "https://raw.githubusercontent.com/echeeUW/codesnippets/master/user_info.json"
        val request = StringRequest(Request.Method.GET, userURL, { response ->
            val gson = Gson()
            val user = gson.fromJson(response, User::class.java)
            onUserReady(user)
        }, {error ->
            Toast.makeText(ctx, "Error while fetching: ${error.message}", Toast.LENGTH_SHORT).show()
        })
        queue.add(request)
    }

}