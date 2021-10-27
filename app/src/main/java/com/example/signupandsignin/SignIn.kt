package com.example.signupandsignin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import io.github.muddz.styleabletoast.StyleableToast

class SignIn : AppCompatActivity() {

    private lateinit var userName: EditText
    private lateinit var password: EditText
    private lateinit var signInButton: Button
    private val encryptingPassword by lazy { EncryptingPassword() }
    private val dbHelper by lazy { DBHelper(applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_in)

        userName= findViewById(R.id.userNameEntry)
        password= findViewById(R.id.passwordEntry)
        signInButton= findViewById(R.id.signInButton)

        signInButton.setOnClickListener{
            if (userName.text.isNotBlank() && password.text.isNotBlank()){
                //val password= encryptingPassword.encryptMsg(password.text.toString(),EncryptingPassword.secretKey).toString()
                val password= password.text.toString()
                val check= dbHelper.checkLogIn(userName.text.toString(),password)
                if (check==-1)
                    StyleableToast.makeText(this,"Wrong UserName/Password!!",R.style.mytoast).show()
                else{
                    val intent= Intent(this,Details::class.java)
                    intent.putExtra("pk",check)
                    startActivity(intent)
                }
            }
            else
                StyleableToast.makeText(this,"Please Enter Valid Values!!",R.style.mytoast).show()
        }

    }
}