package com.acorrea.inventoried

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.acorrea.inventoried.adapters.ShoppingListAdapter
import com.acorrea.inventoried.oldEntities.DataManager
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ShoppingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val listView = findViewById<ListView>(R.id.shoppingListView)

        listView.adapter = ShoppingListAdapter(this, android.R.layout.simple_list_item_1,
            DataManager.productsShopping)


        val newButton = findViewById<FloatingActionButton>(R.id.shoppingNewButton)

        newButton.setOnClickListener {NewProduct()}

        listView.setOnItemClickListener { _, _, position, _ ->
            EditProduct(position)
        }

    }

    override fun onResume()
    {
        super.onResume()

        val listView = findViewById<ListView>(R.id.shoppingListView)

        listView.adapter = ShoppingListAdapter(this, android.R.layout.simple_list_item_1,
            DataManager.productsShopping)
    }

    fun NewProduct()
    {
        //TODO: GOTO SHOPPING ADDEDITSCENE
        val intent = Intent(this@ShoppingActivity, AddEditShoppingActivity::class.java)
        startActivity(intent)
    }

    fun EditProduct(index: Int)
    {
        val intent = Intent(this@ShoppingActivity, AddEditShoppingActivity::class.java)
        intent.putExtra("index", index)
        startActivity(intent)
    }
}