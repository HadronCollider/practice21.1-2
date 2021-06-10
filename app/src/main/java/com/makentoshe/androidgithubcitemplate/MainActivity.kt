package com.makentoshe.androidgithubcitemplate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnGoReg = findViewById<Button>(R.id.btnGoReg)
        btnGoReg.setOnClickListener() {
            val i = Intent(this, RegisterActivity::class.java)
            startActivity(i)
        }

        val btnGoLog = findViewById<Button>(R.id.btnGoLog)
        btnGoLog.setOnClickListener {
            val j = Intent(this, LoginActivity::class.java)
            startActivity(j)
        }
    }
}
