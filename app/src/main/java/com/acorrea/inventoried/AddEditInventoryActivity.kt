package com.acorrea.inventoried

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.IntegerRes
import com.acorrea.inventoried.entity.Product
import com.acorrea.inventoried.entity.ProductInventory

class AddEditInventoryActivity : AppCompatActivity() {

    private lateinit var nameView: EditText
    private lateinit var descriptionView: EditText
    private lateinit var amountView: EditText
    private var editProduct: ProductInventory? = null
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_inventory)

        nameView = findViewById(R.id.addInventoryNameInput)
        descriptionView = findViewById(R.id.addInventoryDescInput)
        amountView = findViewById(R.id.addInventoryAmountInput)
        val text = findViewById<TextView>(R.id.addInventoryTitle)

        val index = intent.getIntExtra("index",-1)
        if(index >= 0)
        {
            editProduct = DataManager.productsInventory[index]
        }

        val confirmButton = findViewById<Button>(R.id.addInventoryConfirmButton)
        val cancelButton = findViewById<Button>(R.id.addInventoryCancelButton)

        //Nuevo producto
        if(editProduct == null)
        {
            text.text = "¡Añade un nuevo producto!"
            confirmButton.text = "Añadir"
            cancelButton.text = "Cancelar"
            confirmButton.setOnClickListener { AddNewProduct() }
            cancelButton.setOnClickListener { finish() }
        }
        //Editar producto
        else
        {
            text.text = "¡Edita tu producto!"
            confirmButton.text = "Confirmar"
            cancelButton.text = "Eliminar"
            nameView.setText(editProduct?.product?.name)
            descriptionView.setText(editProduct?.product?.description)
            amountView.setText(editProduct?.amount.toString())
            confirmButton.setOnClickListener { EditProduct() }
            cancelButton.setOnClickListener { ErraseProduct() }
        }

    }

    fun ErraseProduct()
    {
        DataManager.productsInventory.remove(editProduct)
        finish()
    }

    fun AddNewProduct()
    {

        val name = nameView.text.toString()
        val desc = descriptionView.text.toString()
        val amount = amountView.text.toString().toInt()

        //TODO: CHECK VALUES

        DataManager.productsInventory.add(ProductInventory(Product(name,desc),amount))
        finish()
    }

    fun EditProduct()
    {
        val name = nameView.text.toString()
        val desc = descriptionView.text.toString()
        val amount = amountView.text.toString().toInt()

        //TODO: CHECK VALUES

        editProduct?.product?.name = name
        editProduct?.product?.description = desc
        editProduct?.amount = amount

        finish()
    }
}