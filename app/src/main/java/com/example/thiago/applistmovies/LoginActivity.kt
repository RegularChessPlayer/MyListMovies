package com.example.thiago.applistmovies

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.thiago.applistmovies.Modelos.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    val user = User()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginField = findViewById(R.id.user_login) as TextView
        val passwordField = findViewById(R.id.password_login) as TextView
        val btnLogin = findViewById(R.id.login_button_login) as Button
        val btnSign = findViewById(R.id.signin_button_login) as Button
        val mauth = FirebaseAuth.getInstance()

        loginField.setText(user.login)
        passwordField.setText(user.password)

        btnLogin.setOnClickListener{
            btnLogin.is
            user.login = loginField.text.toString()
            user.password = passwordField.text.toString() // pass the action logic in user
            mauth.signInWithEmailAndPassword(user.login!!, user.password!!).addOnCompleteListener {task: Task<AuthResult> ->
                if(task.isSuccessful){
                    println("Login Sucesso")
                }else{
                    println("Login Falha")
                }
            }

        }


        btnSign.setOnClickListener{
            print("Go to signUp VIew!!")
        }

    }

}
