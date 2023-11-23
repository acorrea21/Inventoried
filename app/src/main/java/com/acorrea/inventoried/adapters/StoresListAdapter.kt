package com.acorrea.inventoried.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.acorrea.inventoried.R
import com.acorrea.inventoried.oldEntities.StoreData

class StoresListAdapter (
    context: Context,
    resource: Int,
    products: List<StoreData>
) : ArrayAdapter<StoreData>(context, resource, products)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val listItemView = convertView ?: inflater.inflate(R.layout.list_item_store, null)

        // Obtenemos el producto de la lista
        val store = getItem(position)

        // Obtenemos las view del item_productshopping
        val nameTextView = listItemView.findViewById<TextView>(R.id.textViewStoreName)
        val descriptionTextView = listItemView.findViewById<TextView>(R.id.textViewStoreDescription)

        // Colocamos la info en las views
        if(store != null)
        {
            nameTextView.text = "Nombre:" + store?.name
            descriptionTextView.text = "Descripcion:" + store?.description
        }
        else
        {
            nameTextView.text = "Nombre:"
            descriptionTextView.text = "Descripcion:"
        }

        return listItemView
    }
}