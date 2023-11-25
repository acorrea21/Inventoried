package com.acorrea.inventoried

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cl.acorrea.inventoried.Interface.TimerCallback
import com.acorrea.inventoried.entity.Timer
import com.acorrea.inventoried.oldEntities.Utilities

class SplashActivity : AppCompatActivity(), TimerCallback
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        var timer = Timer(this,1000)

        if(Utilities.getPlayIntro(this))
        {
            timer.Start(3000)
        }
        else
        {
            onTimerFinished(Timer(this))
        }
    }

    override fun onTimerFinished(timer: Timer)
    {
        super.onTimerFinished(timer)
        val intent = Intent(this,HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}