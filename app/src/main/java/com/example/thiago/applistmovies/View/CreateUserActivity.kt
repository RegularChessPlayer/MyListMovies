package com.example.thiago.applistmovies.View

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import com.example.thiago.applistmovies.Modelos.User
import com.example.thiago.applistmovies.R
import com.example.thiago.applistmovies.ViewModel.LoginViewModel

class CreateUserActivity: AppCompatActivity(){

    lateinit var loginField: TextView
    lateinit var passwordField: TextView
    lateinit var confirmPasswordField: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)

        val confirmButton = findViewById(R.id.confirm_button_create_user) as Button

        loginField = findViewById(R.id.email_text_create_user)
        passwordField = findViewById(R.id.password_text_create_user)
        confirmPasswordField = findViewById(R.id.confirm_text_create_user)

        confirmButton.setOnClickListener(){
            LoginViewModel.cadastrate(loginField.text.toString(),
                    passwordField.text.toString(), confirmPasswordField.text.toString()){
                status: Boolean, message: String? ->
                if(status){
                    val builder =  AlertDialog.Builder(this@CreateUserActivity)
                    builder.setTitle("Atenção")
                    builder.setMessage("Usuário cadastrado com sucesso")
                    builder.create().show()
                    clearFields()
                }else{
                    val builder =  AlertDialog.Builder(this@CreateUserActivity)
                    builder.setTitle("Atenção")
                    builder.setMessage(message)
                    builder.create().show()
                }

            }

        }
    }

    fun clearFields(){
        loginField.text = ""
        passwordField.text = ""
        confirmPasswordField.text = ""
    }


}