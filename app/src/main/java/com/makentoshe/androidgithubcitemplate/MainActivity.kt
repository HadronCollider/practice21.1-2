package com.makentoshe.androidgithubcitemplate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnGoUpload = findViewById<Button>(R.id.btnGoUpload)
        btnGoUpload.setOnClickListener() {
            val i = Intent(this, UploadActivity::class.java)
            startActivity(i)
        }
    }
}
