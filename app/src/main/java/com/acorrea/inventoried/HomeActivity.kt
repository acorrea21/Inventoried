package com.acorrea.inventoried

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var inventoryButton = findViewById<Button>(R.id.HomeInventoryButton)
        var shoppingButton = findViewById<Button>(R.id.HomeShoppingButton)
        var storeButton = findViewById<Button>(R.id.HomeStoresButton)

        inventoryButton.setOnClickListener{
            val intent = Intent(this@HomeActivity, InventoryActivity::class.java)
            startActivity(intent)
        }

        shoppingButton.setOnClickListener{
            val intent = Intent(this@HomeActivity, ShoppingActivity::class.java)
            startActivity(intent)
        }

        storeButton.setOnClickListener{
            val intent = Intent(this@HomeActivity, StoreListActivity::class.java)
            startActivity(intent)
        }
    }

}