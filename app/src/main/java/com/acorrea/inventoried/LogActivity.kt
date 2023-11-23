package com.acorrea.inventoried

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.acorrea.inventoried.oldEntities.DataManager
import com.acorrea.inventoried.oldEntities.UserData


class LogActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log)

        val logInButton = findViewById<Button>(R.id.logLogInButton)
        val registerButton = findViewById<Button>(R.id.logRegisButton)

        DataManager.users.add(UserData("admin","admin1234"))

        logInButton.setOnClickListener {TryLogin()}
        registerButton.setOnClickListener {TryToReg() }
    }

    private fun TryToReg()
    {
        //GoToMain
        val intent = Intent(this@LogActivity, RegActivity::class.java)
        startActivity(intent)
    }

    private fun TryLogin()
    {
        val userInput = findViewById<EditText>(R.id.logUserInput)
        val passInput = findViewById<EditText>(R.id.logPassInput)
        val users = DataManager.users

        for (userdata in users)
        {
            if(userdata.username == userInput.text.toString() && userdata.password == passInput.text.toString())
            {
                //GoToMain
                val intent = Intent(this@LogActivity, HomeActivity::class.java)
                startActivity(intent)
            }
        }
    }

}