package com.example.thiago.applistmovies.Modelos

class User(var login: String? = "teste@gmail.com" , var password: String? = "123456"){

    fun printescpriton(){
        println("Meu login: $login  Minha Senha: $password" )
    }

}