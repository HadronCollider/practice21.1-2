package com.makentoshe.androidgithubcitemplate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.makentoshe.androidgithubcitemplate.data.User

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val userEmailField = findViewById<EditText>(R.id.userEmailLoginField)
        val userPassField = findViewById<EditText>(R.id.userPassLoginField)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener() {
            var uEmail = userEmailField.text.toString().trim()
            val uPass = userPassField.text.toString().trim()

            login(uEmail, uPass)

        }
    }

    private fun login(email: String, pass: String) {

        val db = Firebase.firestore
        val docRef = db.collection(COLLECTION).document(email)

        docRef.get()
            .addOnSuccessListener { document ->
                if (document.data != null) {
                    val gotUser = document.toObject(User::class.java)

                    if (pass == gotUser?.password.toString()) {

                        // redirect on another activity
                        redirect(gotUser)
                        // redirect on another activity

                    } else {
                        noUserWithEmailPassFound()
                    }
                } else {
                    noUserWithEmailPassFound()
                }
            }
            .addOnFailureListener { e ->
                Log.d(TAG, "Some error: ", e)
                somethingWentWrong()
            }
    }

    private fun redirect(user: User?) {
        // will be added later

        // toast is a placeholder
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
    }

    private fun noUserWithEmailPassFound() {
        Toast.makeText(this, "Incorrect email or password", Toast.LENGTH_SHORT).show()
    }

    private fun somethingWentWrong() {
        Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val TAG = "LOGIN-ACTIVITY"
        const val COLLECTION = "users"
    }
}