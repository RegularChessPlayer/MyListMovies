package com.example.thiago.applistmovies.ViewModel

import com.example.thiago.applistmovies.Modelos.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class LoginViewModel{

    val user = User.instance
    val fireBaseEntity = FirebaseAuth.getInstance()
    val database = FirebaseDatabase.getInstance().getReference()

    fun setUserParamenters(login: String, password: String){
        user.login = login
        user.password = password
    }

    fun login(callback: (status: Boolean, message: String?) -> Unit){
        try{
            fireBaseEntity.signInWithEmailAndPassword(user.login!!, user.password!!)
                    .addOnCompleteListener {task: Task<AuthResult> ->
                        if(task.isSuccessful){
                            user.uuid = task.getResult().user.uid
                            database.child("user").child(user.uuid).setValue(user) // !
                            return@addOnCompleteListener callback(true, null)
                        }else{
                            return@addOnCompleteListener callback(false,  "Usuário não Válido")
                        }
                    }
        }catch(e: Exception){
           return  callback(false, e.message)
        }
    }

    companion object {
        fun cadastrate(email: String, password: String, confirmPassword: String ,
                       callback: (status: Boolean, message: String?) -> Unit){
            if(password != confirmPassword){
                return callback(false, "Campo confirmar senha diferente de senha")
            }
            try{
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task: Task<AuthResult> ->
                            if(task.isSuccessful){
                                return@addOnCompleteListener callback(true, null)
                            }else{
                                return@addOnCompleteListener callback(false,
                                        "Não cadastrado. Campo senha deva possuir no mínimo 6 dígitos")
                            }
                        }
            }catch(e: Exception){
                return  callback(false, e.message)
            }
        }
    }

}