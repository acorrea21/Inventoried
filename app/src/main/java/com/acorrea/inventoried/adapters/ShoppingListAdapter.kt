package com.acorrea.inventoried.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.acorrea.inventoried.oldEntities.ProductShopping
import com.acorrea.inventoried.R

class ShoppingListAdapter (
    context: Context,
    resource: Int,
    products: List<ProductShopping>
) : ArrayAdapter<ProductShopping>(context, resource, products)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val listItemView = convertView ?: inflater.inflate(R.layout.list_item_productshopping, null)

        // Obtenemos el producto de la lista
        val shopProduct = getItem(position)

        // Obtenemos las view del item_productshopping
        val nameTextView = listItemView.findViewById<TextView>(R.id.textViewShoppingName)
        val priceTextView = listItemView.findViewById<TextView>(R.id.textViewShoppingPrice)
        val amountTextView = listItemView.findViewById<TextView>(R.id.textViewShoppingAmount)

        // Colocamos la info en las views
        if(shopProduct?.product != null)
        {
            nameTextView.text = "Nombre:" + shopProduct?.product?.name
            priceTextView.text = "Precio:" + shopProduct?.price.toString()
        }
        else
        {
            nameTextView.text = "Nombre:"
            priceTextView.text = "Precio:"
        }

        amountTextView.text = "Cantidad:" + shopProduct?.amount.toString()

        return listItemView
    }
}