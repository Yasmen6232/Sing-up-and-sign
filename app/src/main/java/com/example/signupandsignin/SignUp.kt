package com.example.signupandsignin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import io.github.muddz.styleabletoast.StyleableToast

class SignUp : AppCompatActivity() {

    private lateinit var userNameEntry: EditText
    private lateinit var passwordEntry: EditText
    private lateinit var locationEntry: EditText
    private lateinit var phoneEntry: EditText
    private lateinit var submitButton: Button
    private val encryptingPassword by lazy { EncryptingPassword() }
    private val dbHelper by lazy { DBHelper(applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up)

        userNameEntry= findViewById(R.id.userNameEntry)
        passwordEntry= findViewById(R.id.passwordEntry)
        locationEntry= findViewById(R.id.locationEntry)
        phoneEntry= findViewById(R.id.phoneEntry)
        submitButton= findViewById(R.id.submitButton)

        submitButton.setOnClickListener{
            if (checkEntry()){
                //val password= encryptingPassword.encryptMsg(passwordEntry.text.toString(),EncryptingPassword.generateKey()).toString()
                val password= passwordEntry.text.toString()
                val name= userNameEntry.text.toString()
                val location= locationEntry.text.toString()
                val phone= phoneEntry.text.toString().toInt()
                val check= dbHelper.addUser(Data(name,phone,location,password))
                val wrongCode: Long= -1
                if (check != wrongCode) {
                    StyleableToast.makeText(this, "Sign Up Successfully!!", R.style.mytoast).show()
                    startActivity(Intent(this,MainActivity::class.java))
                }
                else
                    StyleableToast.makeText(this,"Error!!",R.style.mytoast).show()
                clear()
            }
            else
                StyleableToast.makeText(this,"Please Enter Valid Values!!",R.style.mytoast).show()

        }
    }

    private fun clear() {
        userNameEntry.text.clear()
        passwordEntry.text.clear()
        locationEntry.text.clear()
        phoneEntry.text.clear()
    }

    private fun checkEntry(): Boolean{
        if (userNameEntry.text.isBlank())
            return false
        if (passwordEntry.text.isBlank())
            return false
        if (locationEntry.text.isBlank())
            return false
        if (phoneEntry.text.isBlank())
            return false
        return true
    }
}
