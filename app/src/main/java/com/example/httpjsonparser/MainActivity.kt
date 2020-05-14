package com.example.httpjsonparser

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import androidx.core.text.HtmlCompat
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import java.io.InputStream

class MainActivity : AppCompatActivity() {

    lateinit var jsonParserApp: JsonParserApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        jsonParserApp = (application as JsonParserApp)

        btnCount.setOnClickListener {
            fetchWithVolley()
            jsonParserApp.count = jsonParserApp.count + 1
            tvCount.text = "The number of times fetched is: ${jsonParserApp.count}"
        }
    }

    private fun fetchWithVolley() {
        val user = jsonParserApp.apiManager.getUser { user ->
            tvUsername.visibility = View.VISIBLE
            tvUsername.text = HtmlCompat.fromHtml("Let's take a look at <b>${user.username}</b>", HtmlCompat.FROM_HTML_MODE_LEGACY)
            imgUser.visibility = View.VISIBLE
            Picasso.get().load(user.profilePicURL).into(imgUser)
            tvFirstName.visibility = View.VISIBLE
            tvFirstName.text = HtmlCompat.fromHtml("<b>First Name:</b> ${user.firstName}", HtmlCompat.FROM_HTML_MODE_LEGACY)
            tvLastName.visibility = View.VISIBLE
            tvLastName.text = HtmlCompat.fromHtml("<b>Last Name:</b> ${user.lastName}", HtmlCompat.FROM_HTML_MODE_LEGACY)
            tvNose.visibility = View.VISIBLE
            if (user.hasNose) {
                tvNose.text = HtmlCompat.fromHtml("<b>Nosy?:</b> Yes", HtmlCompat.FROM_HTML_MODE_LEGACY)
            } else {
                tvNose.text = HtmlCompat.fromHtml("<b>Nosy?:</b> No", HtmlCompat.FROM_HTML_MODE_LEGACY)
            }
            tvPlatform.visibility = View.VISIBLE
            tvPlatform.text = HtmlCompat.fromHtml("<b>Platform:</b> ${user.platform}", HtmlCompat.FROM_HTML_MODE_LEGACY)
        }
    }
}

