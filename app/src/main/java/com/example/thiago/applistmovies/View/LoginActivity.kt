package com.example.thiago.applistmovies.View

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Button
import android.widget.TextView
import com.example.thiago.applistmovies.R
import com.example.thiago.applistmovies.ViewModel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    val loginViewModel = LoginViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        fillCamps()
        //bind elements
        login_button_login.setOnClickListener{
            updateCampsViewModel()
            loginViewModel.login { status, message ->
                if(status){
                    val intent = Intent(this, FavoriteActivity::class.java)
                    startActivity(intent)
                }else{
                    val builder =  AlertDialog.Builder(this@LoginActivity)
                    builder.setTitle("Atenção")
                    builder.setMessage(message)
                    builder.create().show()
                }
            }
        }

        password_login.setOnClickListener{
            val intent = Intent(this, CreateUserActivity::class.java)
            startActivity(intent)
        }

    }

    fun updateCampsViewModel(){
        loginViewModel.setUserParamenters(user_login.text.toString(), password_login.text.toString())
    }

    fun fillCamps(){
        user_login.setText(loginViewModel.user.login)
        password_login.setText(loginViewModel.user.password)
    }

}
