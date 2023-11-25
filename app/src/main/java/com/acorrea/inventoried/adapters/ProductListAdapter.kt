package com.acorrea.inventoried.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.acorrea.inventoried.R
import com.acorrea.inventoried.database.ProductEntity
import com.acorrea.inventoried.oldEntities.Utilities

class ProductListAdapter (
    context: Context,
    resource: Int,
    products: List<ProductEntity>
) : ArrayAdapter<ProductEntity>(context, resource, products)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val listItemView = convertView ?: inflater.inflate(R.layout.list_item_product, null)

        val product = getItem(position)

        val nameTextView = listItemView.findViewById<TextView>(R.id.listViewProductName)
        val amountTextView = listItemView.findViewById<TextView>(R.id.listViewAmount)
        val priceTextView = listItemView.findViewById<TextView>(R.id.listViewPrice)
        val brandTextView = listItemView.findViewById<TextView>(R.id.listViewBrandName)
        val typeTextView = listItemView.findViewById<TextView>(R.id.listViewType)

        val name = context.getString(R.string.name) + ":"
        val amount = context.getString(R.string.amount) + ":"
        val price = context.getString(R.string.price) + ":"
        val brand = context.getString(R.string.brand) + ":"
        val type = context.getString(R.string.type) + ":"

        if(product != null)
        {
            nameTextView.text = name + product.name
            amountTextView.text = amount + product.amount
            priceTextView.text = price + product.price
            brandTextView.text = brand + product.brand
            typeTextView.text = type + Utilities.getProductType(context)[product.type!!]
        }
        else
        {
            nameTextView.text = name
            amountTextView.text = amount
            priceTextView.text = price
            brandTextView.text = brand
            typeTextView.text = type
        }

        return listItemView
    }
}