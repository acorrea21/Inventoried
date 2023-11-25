package com.acorrea.inventoried

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.acorrea.inventoried.oldEntities.Utilities
import com.acorrea.inventoried.oldEntities.Product
import com.acorrea.inventoried.oldEntities.ProductShopping

class AddEditShoppingActivity : AppCompatActivity() {
    private lateinit var nameView: EditText
    private lateinit var descriptionView: EditText
    private lateinit var amountView: EditText
    private lateinit var priceView: EditText

    private var editProduct: ProductShopping? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_shopping)

        nameView = findViewById(R.id.addShoppingNameInput)
        descriptionView = findViewById(R.id.addShoppingDescInput)
        amountView = findViewById(R.id.addShoppingAmountInput)
        priceView = findViewById(R.id.addShoppingPriceInput)

        val text = findViewById<TextView>(R.id.addShoppingTitle)

        val index = intent.getIntExtra("index", -1)
        if (index >= 0)
        {
            editProduct = Utilities.productsShopping[index]
        }

        val confirmButton = findViewById<Button>(R.id.addShoppingConfirmButton)
        val cancelButton = findViewById<Button>(R.id.addShoppingCancelButton)

        //Nuevo producto
        if (editProduct == null) {
            text.text = "¡Añade un nuevo producto!"
            confirmButton.text = "Añadir"
            cancelButton.text = "Cancelar"
            confirmButton.setOnClickListener { AddNewProduct() }
            cancelButton.setOnClickListener { finish() }
        }
        //Editar producto
        else {
            text.text = "¡Edita tu producto!"
            confirmButton.text = "Confirmar"
            cancelButton.text = "Eliminar"
            nameView.setText(editProduct?.product?.name)
            descriptionView.setText(editProduct?.product?.description)
            amountView.setText(editProduct?.amount.toString())
            priceView.setText(editProduct?.price.toString())
            confirmButton.setOnClickListener { EditProduct() }
            cancelButton.setOnClickListener { ErraseProduct() }
        }

    }

    fun ErraseProduct()
    {
        Utilities.productsShopping.remove(editProduct)
        finish()
    }

    fun AddNewProduct() {

        val name = nameView.text.toString()
        val desc = descriptionView.text.toString()
        val amount = amountView.text.toString().toInt()
        val price = priceView.text.toString().toInt()
        //TODO: CHECK VALUES

        Utilities.productsShopping.add(ProductShopping(Product(name, desc), price, amount))
        finish()
    }

    fun EditProduct() {
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