package com.acorrea.inventoried.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.acorrea.inventoried.R
import com.acorrea.inventoried.oldEntities.ProductInventory

class InventoryListAdapter (
    context: Context,
    resource: Int,
    products: List<ProductInventory>
) : ArrayAdapter<ProductInventory>(context, resource, products)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val listItemView = convertView ?: inflater.inflate(R.layout.list_item_productinventory, null)

        // Obtenemos el producto de la lista
        val inventoryProduct = getItem(position)

        // Obtenemos las view del item_productshopping
        val nameTextView = listItemView.findViewById<TextView>(R.id.textViewInventoryName)
        val descTextView = listItemView.findViewById<TextView>(R.id.textViewInventoryDescription)
        val amountTextView = listItemView.findViewById<TextView>(R.id.textViewInventoryAmount)

        // Colocamos la info en las views
        if(inventoryProduct?.product != null)
        {
            nameTextView.text = "Nombre:" + inventoryProduct?.product?.name
            descTextView.text = "Descripción:" + inventoryProduct?.product?.description
        }
        else
        {
            nameTextView.text = "Nombre:"
            descTextView.text = "Descripción:"
        }

        amountTextView.text = "Cantidad:" + inventoryProduct?.amount.toString()

        return listItemView
    }
}