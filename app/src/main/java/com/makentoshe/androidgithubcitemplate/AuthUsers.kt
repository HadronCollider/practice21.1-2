package com.makentoshe.androidgithubcitemplate

import android.content.Context
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.makentoshe.androidgithubcitemplate.data.Data
import com.makentoshe.androidgithubcitemplate.data.User

class AuthUsers {

    private var user: User? = null


    fun validForm(email: String, passOne: String, passTwo: String): Boolean {
        if (email.isNotBlank() && passOne.isNotBlank() && passTwo.isNotBlank() && passOne == passTwo) {
            return true
        }

        return false
    }

    fun regUser(context: Context, email: String, pass: String, fields: List<EditText>) {
        val db = Firebase.firestore
        user = User(email, pass, null, Data())
        val docRef = db.collection(REGUSERCOLLECTION).document(email)

        docRef.get()
            .addOnSuccessListener { doc ->
                if (doc.data != null) {
                    Toast.makeText(context, "Пользователь с таким email уже существует", Toast.LENGTH_SHORT).show()
                    Log.d(TAGERROR, "User already exists")
                } else {
                    db.collection(REGUSERCOLLECTION).document(email).set(user!!).addOnSuccessListener {
                        cleanFields(fields)

                        // here will be redirect for main page of an application

                    }
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(context, "Непредвиденная ошибка", Toast.LENGTH_SHORT).show()
                Log.d(TAGERROR, e.message.toString())
            }
    }

    private fun cleanFields(fields: List<EditText>) {
        for (i in fields) {
            i.setText("")
        }
    }

//    fun uploadImg(): String {
//        // added in branch app-upload-img-activity
//        return ""
//    }

    companion object {
        const val REGUSERCOLLECTION = "users"
        const val TAGERROR = "REGISTER-ERROR"
    }
}