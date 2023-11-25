package com.acorrea.inventoried

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.acorrea.inventoried.database.MovementEntity
import com.acorrea.inventoried.database.MyDatabase
import com.acorrea.inventoried.database.ProductEntity
import com.acorrea.inventoried.oldEntities.Utilities
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AddEditProductsActivity : AppCompatActivity()
{

    private lateinit var nameView: EditText
    private lateinit var amountView: EditText
    private lateinit var priceView: EditText
    private lateinit var brandView: EditText
    private lateinit var typeView: Spinner

    private var editProduct: ProductEntity? = null

    private var database :MyDatabase = Utilities.getDatabase(this)
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_products)
        nameView = findViewById(R.id.addProductNameInput)
        amountView = findViewById(R.id.addProductAmountInput)
        priceView = findViewById(R.id.addProductPriceInput)
        brandView = findViewById(R.id.addProductBrandInput)
        typeView = findViewById(R.id.addProductType)
        val text = findViewById<TextView>(R.id.addProductTitle)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, Utilities.getProductType(this))
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        typeView.adapter = adapter
        val index = intent.getIntExtra("index",-1)

        if(index >= 0)
        {
            editProduct = Utilities.products[index]
        }

        val confirmButton = findViewById<Button>(R.id.addProductConfirmButton)
        val cancelButton = findViewById<Button>(R.id.addProductCancelButton)

        //Nuevo producto
        if(editProduct == null)
        {
            text.text = getString(R.string.product_add)
            confirmButton.text = getString(R.string.add)
            cancelButton.text = getString(R.string.cancel)

            confirmButton.setOnClickListener { AddNewProduct() }
            cancelButton.setOnClickListener { finish() }
        }
        //Editar producto
        else
        {
            text.text = getString(R.string.product_edit)
            confirmButton.text = getString(R.string.confirm)
            cancelButton.text = getString(R.string.erase)

            nameView.setText(editProduct!!.name)
            amountView.setText(editProduct!!.amount.toString())
            priceView.setText(editProduct!!.price.toString())
            brandView.setText(editProduct!!.brand)
            typeView.setSelection(editProduct!!.type!!)

            confirmButton.setOnClickListener { EditProduct() }
            cancelButton.setOnClickListener { ErraseProduct() }
        }
    }


    fun ErraseProduct()
    {
        //Esto solo deberia pasar cuando editproduct != null, comprobaciones arriba
        database.productDao().delete(editProduct!!)

        //Agregar movimiento
        val currentDate = Date()
        // Formatear la fecha en "día mes año"
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val m = MovementEntity(
            name = editProduct!!.name,
            type = Utilities.getMovementIndex(this,getString(R.string.movement_type_remove)),
            details = "",
            date = dateFormat.format(currentDate)
        )
        database.movementDao().insert(m)

        finish()
    }

    fun AddNewProduct()
    {

        val name = nameView.text.toString()
        val amount = amountView.text.toString().toIntOrNull()
        val price = priceView.text.toString().toIntOrNull()
        val brand = brandView.text.toString()
        val type = typeView.selectedItemPosition

        //CHECK VALUES
        if(name.isBlank() || amount == null || price == null || brand.isBlank() || type == null)
        {
            Toast.makeText(this, getString(R.string.add_product_error),Toast.LENGTH_LONG).show()
        }
        else
        {
            //Agregar producto
            val p = ProductEntity(
                name = name,
                brand = brand,
                type = type,
                amount = amount,
                price = price
            )
            database.productDao().insert(p)

            AddMovement(p.name!!,R.string.movement_type_change_type, "")

            finish()
        }
    }

    fun EditProduct()
    {
        val name = nameView.text.toString()
        val amount = amountView.text.toString().toIntOrNull()
        val price = priceView.text.toString().toIntOrNull()
        val brand = brandView.text.toString()
        val type = typeView.selectedItemPosition

        //CHECK VALUES
        if(name.isBlank() || amount == null || price == null || brand.isBlank() || type == null)
        {
            Toast.makeText(this, getString(R.string.add_product_error),Toast.LENGTH_LONG).show()
        }
        else
        {
            val p = editProduct?.copy(
                name = name,
                brand = brand,
                type = type,
                amount = amount,
                price = price
            )

            p?.let {
                database.productDao().update(it)
                AddEditMovement(editProduct!!,p!!)
                finish()
            }
        }
    }

    fun AddEditMovement(beforeProduct :ProductEntity, newProduct: ProductEntity)
    {
        var detail = ""

        //Cambios de nombre
        if(beforeProduct.name != newProduct.name)
        {
            detail = beforeProduct.name + " -> " + newProduct.name
            //Solo aqui colocamos el antiguo nombre
            AddMovement(beforeProduct.name!!,R.string.movement_type_change_name,detail)
        }

        //Cambios de cantidad
        if(beforeProduct.amount != newProduct.amount)
        {
            detail = beforeProduct.amount.toString() + " -> " + newProduct.amount.toString()
            AddMovement(newProduct.name!!,R.string.movement_type_change_amount,detail)
        }

        // Cambios de precio
        if (beforeProduct.price != newProduct.price)
        {
            detail = beforeProduct.price.toString() + " -> " + newProduct.price.toString()
            AddMovement(newProduct.name!!, R.string.movement_type_change_price, detail)
        }

        // Cambios de marca
        if (beforeProduct.brand != newProduct.brand)
        {
            detail = beforeProduct.brand + " -> " + newProduct.brand
            AddMovement(newProduct.name!!, R.string.movement_type_change_brand, detail)
        }

        //Cambios de tipo
        if(beforeProduct.type != newProduct.type)
        {
            val beforeType = Utilities.getProductType(this)[beforeProduct.type!!]
            val newType = Utilities.getProductType(this)[newProduct.type!!]

            detail = "$beforeType -> $newType"
            AddMovement(newProduct.name!!, R.string.movement_type_change_type, detail)
        }
    }


    fun AddMovement(name: String, type: Int, details: String)
    {
        //Agregar movimiento
        val currentDate = Date()
        // Formatear la fecha en "día mes año"
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        val m = MovementEntity(
            name = name,
            type = Utilities.getMovementIndex(this,getString(type)),
            details = details,
            date = dateFormat.format(currentDate)
        )
        database.movementDao().insert(m)

    }
}