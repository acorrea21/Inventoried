package com.acorrea.inventoried

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.acorrea.inventoried.adapters.MovementListAdapter
import com.acorrea.inventoried.entity.Utilities

class MovementActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movement)

        val database = Utilities.getDatabase(this)
        val movements = database.movementDao().getAll()

        val listView = findViewById<ListView>(R.id.movementListView)
        listView.adapter = MovementListAdapter(this, android.R.layout.simple_list_item_1,
        movements.reversed())
    }
}