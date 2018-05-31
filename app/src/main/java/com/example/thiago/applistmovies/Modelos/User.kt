package com.example.thiago.applistmovies.Modelos

class User(var login: String? = "teste@gmail.com" , var password: String? = "123456"){

    fun printDescpriton(){
        println("Meu login: $login  Minha Senha: $password" )
    }

}