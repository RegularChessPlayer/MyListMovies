package com.example.thiago.applistmovies.View

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Button
import android.widget.TextView
import com.example.thiago.applistmovies.R
import com.example.thiago.applistmovies.ViewModel.LoginViewModel


class LoginActivity : AppCompatActivity() {

    val loginViewModel = LoginViewModel()
    lateinit var loginField: TextView
    lateinit var passwordField: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginField = findViewById(R.id.user_login) as TextView
        passwordField = findViewById(R.id.password_login) as TextView
        val btnLogin = findViewById(R.id.login_button_login) as Button
        val btnSign = findViewById(R.id.signin_button_login) as Button
        fillCamps()
        //bind elements
        btnLogin.setOnClickListener{
            updateCampsViewModel()
            loginViewModel.login { status, message ->
                if(status){
                    println("Sucesso") // go to list movies
                }else{
                    val builder =  AlertDialog.Builder(this@LoginActivity)
                    builder.setTitle("Atenção")
                    builder.setMessage(message)
                    builder.create().show()
                }
            }
        }

        btnSign.setOnClickListener{
            val intent = Intent(this, CreateUserActivity::class.java)
            startActivity(intent)
        }

    }

    fun updateCampsViewModel(){
        loginViewModel.setUserParamenters(loginField.text.toString(), passwordField.text.toString())
    }

    fun fillCamps(){
        loginField.setText(loginViewModel.user.login)
        passwordField.setText(loginViewModel.user.password)
    }

}
