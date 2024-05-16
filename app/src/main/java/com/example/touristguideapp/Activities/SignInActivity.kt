package com.example.touristguideapp.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.touristguideapp.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var SignInBtn: TextView
    private lateinit var user_email : TextInputEditText
    private lateinit var user_pass : TextInputEditText

    private lateinit var SignUpBtn : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        SignInBtn = findViewById(R.id.singinbtn)
        user_email = findViewById(R.id.emailEt)
        user_pass = findViewById(R.id.passET)
        SignUpBtn = findViewById(R.id.textView)

        firebaseAuth = FirebaseAuth.getInstance()

        SignUpBtn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        SignInBtn.setOnClickListener {
            val email = user_email.text.toString()
            val pass = user_pass.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {

                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, HomePageActivity::class.java)
                        startActivity(intent)
                        Toast.makeText(this, "Sign In Done Successfully", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()

            }
        }
    }

    override fun onStart() {
        super.onStart()

        if(firebaseAuth.currentUser != null){
            val intent = Intent(this, HomePageActivity::class.java)
            startActivity(intent)
        }
    }
}