package com.example.thiago.applistmovies.Modelos

class User private constructor() {

    private object Holder { val INSTANCE = User() }

    companion object {
        val instance: User by lazy { Holder.INSTANCE }
    }

    var login: String? = "teste@gmail.com"
    var password: String? = "123456"
    var uuid: String? = ""

}