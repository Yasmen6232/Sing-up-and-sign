package com.example.signupandsignin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.signIn).setOnClickListener{
            startActivity(Intent(this,SignIn::class.java))
        }
        findViewById<Button>(R.id.signUp).setOnClickListener{
            startActivity(Intent(this,SignUp::class.java))
        }

    }
}