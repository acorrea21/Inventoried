package com.acorrea.inventoried

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.acorrea.inventoried.adapters.InventoryListAdapter
import com.acorrea.inventoried.oldEntities.Utilities
import com.google.android.material.floatingactionbutton.FloatingActionButton

class InventoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory)

        val listView = findViewById<ListView>(R.id.inventoryListView)
        
        listView.adapter = InventoryListAdapter(this, android.R.layout.simple_list_item_1,
            Utilities.productsInventory)


        val newButton = findViewById<FloatingActionButton>(R.id.inventoryNewButton)

        newButton.setOnClickListener {NewProduct()}

        listView.setOnItemClickListener { _, _, position, _ ->
            EditProduct(position)
        }

    }

    override fun onResume()
    {
        super.onResume()

        val listView = findViewById<ListView>(R.id.inventoryListView)

        listView.adapter = InventoryListAdapter(this, android.R.layout.simple_list_item_1,
            Utilities.productsInventory)
    }

    fun NewProduct()
    {
        val intent = Intent(this@InventoryActivity, AddEditInventoryActivity::class.java)
        startActivity(intent)
    }

    fun EditProduct(index: Int)
    {
        val intent = Intent(this@InventoryActivity, AddEditInventoryActivity::class.java)
        intent.putExtra("index", index)
        startActivity(intent)
    }
}