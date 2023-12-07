package com.acorrea.inventoried

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.widget.SearchView
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
    lateinit var searchView: SearchView

    var products = listOf<ProductEntity>()
    var filtedProducts = mutableListOf<ProductEntity>()

    var clearQuery = false

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        var toolbar : Toolbar? = findViewById<Toolbar>(R.id.homeToolbar)
        setSupportActionBar(toolbar)

        searchView = findViewById(R.id.homeSearch)
        // Agrega un listener para la SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                RefreshListViewFilter(newText)
                return true
            }
        })


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
        GetProducts()
        RefreshListViewFilter(searchView.query.toString())
    }

    fun GetProducts()
    {
        database = Utilities.getDatabase(this)
        productDAO = database.productDao()
        Utilities.products = productDAO.getAll()
    }

    fun RefreshListView()
    {
        val listView = findViewById<ListView>(R.id.homeListView)

        val adapter = ProductListAdapter(this, android.R.layout.simple_list_item_1, Utilities.products)
        listView.adapter = adapter
    }

    fun RefreshListViewFilter(query :String?)
    {
        val listView = findViewById<ListView>(R.id.homeListView)


        if(query.isNullOrBlank())
        {
            RefreshListView()
            return
        }

        var originalList = Utilities.products
        filtedProducts.clear()

        for(p in originalList)
        {
            if(!p.name.isNullOrBlank() && p.name.lowercase().contains(query.lowercase()))
            {
                filtedProducts.add(p)
            }
        }

        // Cambiar aquí
        val adapter = ProductListAdapter(this, android.R.layout.simple_list_item_1, filtedProducts)
        listView.adapter = adapter
    }

    fun NewProduct()
    {
        val intent = Intent(this, APIRESTActivity::class.java)
        startActivity(intent)
    }


    fun EditProduct(index: Int)
    {
        var i = index

        //Si hay algo en la busqueda, tenemos que revisar
        if(!searchView.query.toString().isNullOrBlank())
        {
            //Debido a como funciona los filtros primero debemos asegurarnos de que sea el index correcto
            val p = filtedProducts[index]
            i = products.indexOf(p)
        }

        val intent = Intent(this, AddEditProductsActivity::class.java)


        intent.putExtra("index", index)
        startActivity(intent)
    }
}