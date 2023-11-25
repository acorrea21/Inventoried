package com.acorrea.inventoried

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import cl.acorrea.inventoried.Interface.TimerCallback
import com.acorrea.inventoried.entity.Timer
import com.acorrea.inventoried.entity.Utilities

class SplashActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if(Utilities.getPlayIntro(this))
        {
            val imageView = findViewById<ImageView>(R.id.splashImage)

            // Cargar las animaciones desde los archivos XML
            val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fadein)

            fadeIn.setAnimationListener(object : Animation.AnimationListener
            {
                override fun onAnimationStart(animation: Animation) {
                }

                override fun onAnimationEnd(animation: Animation)
                {
                    nextActivity()
                }

                override fun onAnimationRepeat(animation: Animation) {
                }
            })

            // Aplicar las animaciones a la imagen
            imageView.startAnimation(fadeIn)
        }
        else
        {
            nextActivity()
        }
    }

    fun nextActivity()
    {
        val intent = Intent(this,HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}