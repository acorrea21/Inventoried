package com.acorrea.inventoried

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.acorrea.inventoried.oldEntities.Utilities
import com.acorrea.inventoried.oldEntities.StoreData

class AddEditStoreActivity : AppCompatActivity() {
    private lateinit var nameView: EditText
    private lateinit var descriptionView: EditText

    private var editStore: StoreData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_store)

        nameView = findViewById(R.id.addStoreNameInput)
        descriptionView = findViewById(R.id.addStoreDescInput)

        val text = findViewById<TextView>(R.id.addStoreTitle)

        val index = intent.getIntExtra("index", -1)
        if (index >= 0)
        {
            editStore = Utilities.stores[index]
        }

        val confirmButton = findViewById<Button>(R.id.addStoreConfirmButton)
        val cancelButton = findViewById<Button>(R.id.addStoreCancelButton)

        //Nuevo producto
        if (editStore == null) {
            text.text = "¡Añade una nueva tienda!"
            confirmButton.text = "Añadir"
            cancelButton.text = "Cancelar"
            confirmButton.setOnClickListener { AddNewStore() }
            cancelButton.setOnClickListener { finish() }
        }
        //Editar producto
        else {
            text.text = "¡Edita tu tienda!"
            confirmButton.text = "Confirmar"
            cancelButton.text = "Eliminar"
            nameView.setText(editStore?.name)
            descriptionView.setText(editStore?.description)
            confirmButton.setOnClickListener { EditStore() }
            cancelButton.setOnClickListener { ErraseStore() }
        }

    }


    fun AddNewStore() {

        val name = nameView.text.toString()
        val desc = descriptionView.text.toString()
        //TODO: CHECK VALUES

        Utilities.stores.add(StoreData(name, desc))
        finish()
    }


    fun ErraseStore()
    {
        Utilities.stores.remove(editStore)
        finish()
    }

    fun EditStore() {
        val name = nameView.text.toString()
        val desc = descriptionView.text.toString()

        //TODO: CHECK VALUES

        editStore?.name = name
        editStore?.description = desc

        finish()
    }
}