package com.acorrea.inventoried.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.acorrea.inventoried.R
import com.acorrea.inventoried.database.MovementEntity
import com.acorrea.inventoried.entity.Utilities

class MovementListAdapter (
    context: Context,
    resource: Int,
    products: List<MovementEntity>
) : ArrayAdapter<MovementEntity>(context, resource, products)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val listItemView = convertView ?: inflater.inflate(R.layout.list_item_movement, null)

        val movement = getItem(position)

        val nameTextView = listItemView.findViewById<TextView>(R.id.listViewMovementName)
        val typeTextView = listItemView.findViewById<TextView>(R.id.listViewMovementType)
        val detailsTextView = listItemView.findViewById<TextView>(R.id.listViewMovementDetails)
        val dateTextView = listItemView.findViewById<TextView>(R.id.listViewMovementDate)

        val name = context.getString(R.string.name) + ":"
        val type = context.getString(R.string.type) + ":"
        val details = context.getString(R.string.details) + ":"
        val date = context.getString(R.string.date) + ":"

        if(movement != null)
        {
            nameTextView.text = name + movement.name
            typeTextView.text = type + Utilities.getMovementType(context)[movement.type!!]
            
            if(movement.details!!.isBlank())
            {
                detailsTextView.text = details + context.getString(R.string.none)
            }
            else
            {
                detailsTextView.text = details + movement.details
            }
            
            dateTextView.text = date + movement.date
        }
        else
        {
            nameTextView.text = name
            typeTextView.text = type
            detailsTextView.text = details
            dateTextView.text = date
        }

        return listItemView
    }
}