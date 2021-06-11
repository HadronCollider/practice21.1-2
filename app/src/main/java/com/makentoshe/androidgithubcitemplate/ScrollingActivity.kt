package com.makentoshe.androidgithubcitemplate

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.makentoshe.androidgithubcitemplate.databinding.ActivityScrollingBinding

class ScrollingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScrollingBinding
    private var listItems = ArrayList<String>()
    private var adapter: ArrayAdapter<String>? = null
    private lateinit var EditableText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityScrollingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = title
        adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            listItems
        )
        val list = findViewById<View>(R.id.list) as ListView
        list.adapter = adapter

        EditableText = findViewById<EditText>(R.id.EditText)
    }

    private fun addItems(): Boolean {
        EditableText.setVisibility(View.VISIBLE)

        EditableText.setOnKeyListener(View.OnKeyListener{v, keyCode,event ->

            if(keyCode == KeyEvent.KEYCODE_ENTER){
                val txt = EditableText.text.toString()
                listItems.add(txt)
                adapter?.notifyDataSetChanged()
                EditableText.setVisibility(View.INVISIBLE)
                return@OnKeyListener true
            }
            return@OnKeyListener false
        })
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_new_list -> addItems()
            R.id.action_my_groups -> myGroupsActivity()
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun myGroupsActivity() : Boolean {
        val switchActivityIntent = Intent(this, MyGroupsActivity::class.java)
        startActivity(switchActivityIntent)
        return true
    }

}