package com.example.touristguideapp.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.view.animation.TranslateAnimation
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.touristguideapp.R
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val introBtn = findViewById<ConstraintLayout>(R.id.introBtn);

        val transAnim = TranslateAnimation(
            0f, 0f, -1000f, 10f
        )
        transAnim.startOffset = 500
        transAnim.duration = 2500
        transAnim.fillAfter = true

        transAnim.interpolator = BounceInterpolator()
        transAnim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                introBtn.clearAnimation()
            }

            override fun onAnimationRepeat(animation: Animation?) {}
        })

        introBtn.startAnimation(transAnim)



        introBtn.setOnClickListener {
            startActivity(Intent(this@MainActivity, SignUpActivity::class.java))
        }

    }
}