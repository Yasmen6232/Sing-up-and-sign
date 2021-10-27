package com.example.signupandsignin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Details : AppCompatActivity() {

    private val dbHelper by lazy { DBHelper(applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details)

        val pk= intent.extras?.getInt("pk")

        val details= dbHelper.getDetails(pk!!)

        findViewById<TextView>(R.id.welcomeTV).text= "welcome ${details.name}"
        findViewById<TextView>(R.id.userNameTV).text= " Name: ${details.name}"
        findViewById<TextView>(R.id.locationTV).text= " Location: ${details.location}"
        findViewById<TextView>(R.id.phoneNumberTV).text= " Phone: ${details.phoneNumber}"
    }

    fun signOut(view: View) {
        startActivity(Intent(this,MainActivity::class.java))
    }
}