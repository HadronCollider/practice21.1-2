package com.makentoshe.androidgithubcitemplate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.makentoshe.androidgithubcitemplate.data.Data
import com.makentoshe.androidgithubcitemplate.data.User

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val inputEmailField = findViewById<EditText>(R.id.userEmailRegField)
        val inputPassOneField = findViewById<EditText>(R.id.userPassRegFieldOne)
        val inputPassTwoField = findViewById<EditText>(R.id.userPassRegFieldTwo)
        val btnRegister = findViewById<Button>(R.id.btnRegister)

        btnRegister.setOnClickListener() {
            var uEmail = inputEmailField.text.toString().trim()
            var uPassOne = inputPassOneField.text.toString().trim()
            var uPassTwo = inputPassTwoField.text.toString().trim()

            if (uPassOne == uPassTwo) {
                register(uEmail, uPassOne)
            } else {
                differentPassError()
            }
        }
    }

    private fun register(email: String, pass: String) {
        val db = Firebase.firestore

        var usr = User(email, null, pass, Data())

        val docRef = db.collection(COLLECTION).document(email)

        docRef.get()
            .addOnSuccessListener { document ->
                if (document.data != null) {
                    emailExistsError()
                } else {
                    db.collection(COLLECTION).document(email).set(usr)
                        .addOnSuccessListener {
                            successReg()
                        }
                        .addOnFailureListener() { e ->
                            failReg(e)
                        }
                }
            }
            .addOnFailureListener { e ->
                Log.d(TAG, "Some error: ", e)
            }

    }



    private fun successReg() {
        findViewById<EditText>(R.id.userEmailRegField).setText("")
        findViewById<EditText>(R.id.userPassRegFieldOne).setText("")
        findViewById<EditText>(R.id.userPassRegFieldTwo).setText("")

        Log.d(TAG, "Success!")
    }

    private fun failReg(e: Exception) {
        Log.d(TAG, "Fail!", e)
    }

    private fun differentPassError() {
        Toast.makeText(this, "Passwords are different", Toast.LENGTH_SHORT).show()
    }

    private fun emailExistsError() {
        Toast.makeText(this, "Email already exists", Toast.LENGTH_SHORT).show()
    }

    private fun showError() {
        // Will be added later :/
    }

    companion object {
        const val TAG = "REGISTER-ACTIVITY"
        const val COLLECTION = "users"
    }

}
