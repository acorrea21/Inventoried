package com.acorrea.inventoried

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.widget.Toolbar
import com.acorrea.inventoried.adapters.ProductListAdapter
import com.acorrea.inventoried.database.MyDatabase
import com.acorrea.inventoried.database.ProductDAO
import com.acorrea.inventoried.database.ProductEntity
import com.acorrea.inventoried.entity.Utilities

class HomeActivity : AppCompatActivity()
{
    lateinit var database: MyDatabase
    lateinit var productDAO: ProductDAO
    var products = listOf<ProductEntity>()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        var toolbar : Toolbar? = findViewById<Toolbar>(R.id.homeToolbar)
        setSupportActionBar(toolbar)

        val newButton = findViewById<Button>(R.id.homeAddButton)
        newButton.setOnClickListener {NewProduct()}

        val listView = findViewById<ListView>(R.id.homeListView)
        listView.setOnItemClickListener { _, _, position, _ ->
            EditProduct(position)
        }

        RefreshListView()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean
    {

        menuInflater.inflate(R.menu.home_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        when (item.itemId)
        {
            R.id.toolbar_settings ->
            {
                intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.toolbar_movements ->
            {
                intent = Intent(this, MovementActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onResume() {
        super.onResume()
        RefreshListView()
    }

    fun RefreshListView()
    {
        val listView = findViewById<ListView>(R.id.homeListView)

        database = Utilities.getDatabase(this)
        productDAO = database.productDao()
        Utilities.products = productDAO.getAll()

        listView.adapter = ProductListAdapter(this, android.R.layout.simple_list_item_1,
            Utilities.products)
    }

    fun NewProduct()
    {
        val intent = Intent(this, AddEditProductsActivity::class.java)
        startActivity(intent)
    }


    fun EditProduct(index: Int)
    {
        val intent = Intent(this, AddEditProductsActivity::class.java)
        intent.putExtra("index", index)
        startActivity(intent)
    }
}