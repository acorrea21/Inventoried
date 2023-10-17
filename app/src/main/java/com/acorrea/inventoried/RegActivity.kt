package com.acorrea.inventoried

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.acorrea.inventoried.entity.UserData

class RegActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)

        val regButton = findViewById<Button>(R.id.regRegButton)
        val backButton = findViewById<Button>(R.id.regBackButton)

        regButton.setOnClickListener { TryToReg() }
        backButton.setOnClickListener { GoBack() }
    }

    private fun TryToReg()
    {
        val  userInput = findViewById<EditText>(R.id.regUserInput)
        val  passInput = findViewById<EditText>(R.id.regPassInput)
        val users = DataManager.users;

        //No queremos usuarios repetidos, asi que por comprobamos que no exista el mismo usuario
        for (userdata in users)
        {
            if(userdata.username == userInput.text.toString())
            {
                Toast.makeText(this,"¡El usuario ya existe!",Toast.LENGTH_LONG).show()
                return;
            }
        }
        Toast.makeText(this,"Registrado exitosamente",Toast.LENGTH_LONG).show()
        DataManager.users.add(UserData(userInput.text.toString(),passInput.text.toString()))
    }


    private fun GoBack()
    {
        finish()
    }
}