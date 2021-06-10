package com.makentoshe.androidgithubcitemplate

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.google.firebase.storage.ktx.storage
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream
import java.util.*
import java.util.jar.Manifest

class UploadActivity : AppCompatActivity() {

    private val PICK_IMAGE_REQUEST = 71
    private var filePath: Uri? = null
    private var firebaseStorage: FirebaseStorage? = null
    private var storageReference: StorageReference? = null
    private var imgBitmap: Bitmap? = null
    private var downloadUrl: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)

        firebaseStorage = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference

        val btnUpload = findViewById<Button>(R.id.btnUpload)
        val btnChoose = findViewById<Button>(R.id.btnChooseImg)


        btnChoose.setOnClickListener() { launchGallery() }
        btnUpload.setOnClickListener() { uploadImg() }
    }

    private fun launchGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select pic"), PICK_IMAGE_REQUEST)
    }

    private fun uploadImg() {
        if (filePath != null) {

            val imgId = UUID.randomUUID()
            val ref = storageReference?.child("img/" + "${imgId.toString()}")
            val uploadTask = ref?.putFile(filePath!!)

            val urlTask = uploadTask?.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        Log.d("ERROR-UPLOADING", it.message.toString())
                    }
                }
                return@Continuation ref.downloadUrl
            })?.addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    // redirect to another activity
                    redirectNext(task.result.toString())
                    // redirect to another activity

                } else {
                    Log.d(TAG, "Some errors found!")
                }
            }?.addOnFailureListener {
                Log.d(TAG, it.message.toString())
            }

        } else {
            Toast.makeText(this, "Pls upload an img", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            if (data == null || data.data == null) { return }

            filePath = data.data
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                imgBitmap = bitmap
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun redirectNext(url: String) {
        Log.d("DEBUG-UPLOAD", url)
    }

    companion object {
        const val TAG = "ERROR-UPLOAD-ACT"
    }
}