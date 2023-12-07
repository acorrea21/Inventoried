package com.acorrea.inventoried

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.acorrea.inventoried.Interface.ApiCallback
import com.acorrea.inventoried.entity.ApiTask

class APIRESTActivity : AppCompatActivity(), ApiCallback {

    private lateinit var apiTask: ApiTask

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apirestactivity)
        apiTask = ApiTask(this)

        val button  = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            apiTask.execute(" https://opentdb.com/api.php?amount=1&difficulty=easy&type=boolean" )
        }
    }

    override fun onRequestComplete(result: String)
    {
        val textview = findViewById<TextView>(R.id.textView)
        textview.text = result
    }
}