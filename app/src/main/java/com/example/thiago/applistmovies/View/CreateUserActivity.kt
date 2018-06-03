package com.example.thiago.applistmovies.View

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.example.thiago.applistmovies.R
import com.example.thiago.applistmovies.ViewModel.LoginViewModel
import kotlinx.android.synthetic.main.activity_create_user.*

class CreateUserActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)
        confirm_button_create_user.setOnClickListener(){
            LoginViewModel.cadastrate(email_text_create_user.text.toString(),
                    password_text_create_user.text.toString(), confirm_text_create_user.text.toString()){
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
        email_text_create_user.setText("")
        password_text_create_user.setText("")
        confirm_text_create_user.setText("")
    }

}