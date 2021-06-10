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


class MainActivity : AppCompatActivity(), ElementMainListAdapter.OnItemClickListener {

    //LISTS
    private lateinit var elementMainListAdapter: ElementMainListAdapter
    private var alertDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu)

        //POPUP MENU
        val button = findViewById<ImageButton>(R.id.btn_main_menu)
        button.setOnClickListener { v: View ->
            showMenu(v, R.menu.popup_menu)
        }

        //LISTS

        initRecyclerView()

        /*
        addPersonButton.setOnClickListener{ //toDo: part of popup_menu.xml
            personAdapter.addPerson(
                id = "1", //toDo
                name = "Name", //toDo
                description = "No comments", //toDo
                avatarUrl = "https://miro.medium.com/max/2400/0*nwOC-SxX0_fb3Xwc.jpg"//toDo
            )

        }
        */
    }
}

//POPUP MENU
private fun showMenu(v: View, @MenuRes menuRes: Int) {
    val popup = PopupMenu(v.context!!, v)
    popup.menuInflater.inflate(menuRes, popup.menu)

    popup.setOnMenuItemClickListener {
        //Here's a respond to menu item click
        //toDo
        true
    }
    popup.setOnDismissListener {
        //Respond to popup being dismissed
        //toDo
    }
    popup.show()
}