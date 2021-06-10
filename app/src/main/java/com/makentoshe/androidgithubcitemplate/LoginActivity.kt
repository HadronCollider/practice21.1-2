package com.makentoshe.androidgithubcitemplate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val userEmailField = findViewById<EditText>(R.id.userEmailLoginField)
        val userPassField = findViewById<EditText>(R.id.userPassLoginField)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

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