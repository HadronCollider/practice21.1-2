package com.makentoshe.androidgithubcitemplate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val inputEmailField = findViewById<EditText>(R.id.userEmailRegField)
        val inputPassOneField = findViewById<EditText>(R.id.userPassRegFieldOne)
        val inputPassTwoField = findViewById<EditText>(R.id.userPassRegFieldTwo)
        val btnRegister = findViewById<Button>(R.id.btnRegister)

        val regUser = AuthUsers()

        btnRegister.setOnClickListener() {
            val uEmail = inputEmailField.text.toString().trim()
            val uPassOne = inputPassOneField.text.toString().trim()
            val uPassTwo = inputPassTwoField.text.toString().trim()

            val idsList = listOf<EditText>(inputEmailField, inputPassOneField, inputPassTwoField)

            if (regUser.validFormRegister(uEmail, uPassOne, uPassTwo)) {
                regUser.regUser(this, uEmail, uPassOne, idsList)
            } else {
                Toast.makeText(this, "Проверьте заполнение формы", Toast.LENGTH_SHORT).show()
                Log.d(ERRORTAG, "Form validation incomplete")
            }

        }
    }

    companion object {
        const val ERRORTAG = "REGISTER-ERROR"
    }

}
