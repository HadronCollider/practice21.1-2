package com.makentoshe.androidgithubcitemplate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val ReButton = findViewById<TextView>(R.id.textView)
        val ReButtonZ = findViewById<TextView>(R.id.textView3)
        val userEmailField = findViewById<EditText>(R.id.userEmailLoginField)
        val userPassField = findViewById<EditText>(R.id.userPassLoginField)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        ReButton.setOnClickListener(){val switchActivityIntent = Intent(this, RegisterActivity::class.java)
            startActivity(switchActivityIntent)}

        ReButtonZ.setOnClickListener(){val switchActivityIntent = Intent(this, ScrollingActivity::class.java)
            startActivity(switchActivityIntent)}



        val loginUser = AuthUsers()
        btnLogin.setOnClickListener() {
            val uEmail = userEmailField.text.toString().trim()
            val uPass = userPassField.text.toString().trim()


            if (loginUser.validFormLogin(uEmail, uPass)) {

                val idsList = listOf(userEmailField, userPassField)
                loginUser.logUser(this, uEmail, uPass, idsList)

            } else {
                Toast.makeText(this, "Проверьте заполнение формы", Toast.LENGTH_SHORT).show()
                Log.d(RegisterActivity.ERRORTAG, "Form validation incomplete")
            }

        }
    }

    companion object {
        const val ERRORTAG = "LOGIN-ERROR"
    }
}