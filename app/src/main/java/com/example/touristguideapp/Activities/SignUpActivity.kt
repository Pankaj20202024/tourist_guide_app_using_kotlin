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

class SignUpActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var SignInBtn:TextView
    private lateinit var user_email :TextInputEditText
    private lateinit var user_pass :TextInputEditText
    private lateinit var user_re_pass :TextInputEditText
    private lateinit var SignUpBtn :Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        SignInBtn = findViewById(R.id.textView)
        user_email = findViewById(R.id.emailEt)
        user_pass = findViewById(R.id.passET)
        user_re_pass = findViewById(R.id.confirmPassEt)
        SignUpBtn = findViewById(R.id.singupbtn)

        firebaseAuth = FirebaseAuth.getInstance()

        SignInBtn.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
        SignUpBtn.setOnClickListener{
            val email = user_email.text.toString()
            val pass = user_pass.text.toString()
            val confirmPass = user_re_pass.text.toString()
            if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
                if (pass == confirmPass) {
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val intent = Intent(this, SignInActivity::class.java)
                            startActivity(intent)
                            Toast.makeText(this, "Sign Up Done Successfully", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()

                        }
                    }
                } else {
                    Toast.makeText(this, "Password is not matching", Toast.LENGTH_SHORT).show()
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


