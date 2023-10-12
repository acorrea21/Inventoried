package com.acorrea.inventoried

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.acorrea.inventoried.adapters.InventoryListAdapter
import com.acorrea.inventoried.adapters.StoresListAdapter
import com.acorrea.inventoried.entity.Product
import com.acorrea.inventoried.entity.ProductInventory
import com.google.android.material.floatingactionbutton.FloatingActionButton

class StoreListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_list)

        val listView = findViewById<ListView>(R.id.storeListView)

        listView.adapter = StoresListAdapter(this, android.R.layout.simple_list_item_1,DataManager.stores)


        val newButton = findViewById<FloatingActionButton>(R.id.storeNewButton)

        newButton.setOnClickListener {NewStore()}

        listView.setOnItemClickListener { _, _, position, _ ->
            EditStore(position)
        }

    }

    override fun onResume()
    {
        super.onResume()

        val listView = findViewById<ListView>(R.id.storeListView)

        listView.adapter = StoresListAdapter(this, android.R.layout.simple_list_item_1,DataManager.stores)
    }

    fun NewStore()
    {
        val intent = Intent(this@StoreListActivity, AddEditStoreActivity::class.java)
        startActivity(intent)
    }

    fun EditStore(index: Int)
    {
        val intent = Intent(this@StoreListActivity, AddEditStoreActivity::class.java)
        intent.putExtra("index", index)
        startActivity(intent)
    }
}