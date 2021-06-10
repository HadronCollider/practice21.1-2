package com.makentoshe.androidgithubcitemplate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ListView
import androidx.annotation.MenuRes
import androidx.appcompat.widget.PopupMenu

import kotlinx.android.synthetic.main.activity_main

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Collections.addAll
import java.util.ArrayList
import java.util.List


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu)

        val button = findViewById<ImageButton>(R.id.btn_main_menu)
        button.setOnClickListener { v: View ->
            showMenu(v, R.menu.popup_menu)
        }
    }
}

//POPUP MENU
private fun showMenu(v: View, @MenuRes menuRes: Int) {
    val popup = PopupMenu(v.context!!, v)
    popup.menuInflater.inflate(menuRes, popup.menu)

    popup.setOnMenuItemClickListener {
        //Here's a respond to menu item click

        true
    }
    popup.setOnDismissListener {
        //Respond to popup being dismissed

    }
    popup.show()
}