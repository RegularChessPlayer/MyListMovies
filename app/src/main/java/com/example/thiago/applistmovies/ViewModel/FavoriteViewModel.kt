package com.example.thiago.applistmovies.ViewModel

import com.example.thiago.applistmovies.Modelos.Movie
import com.example.thiago.applistmovies.Modelos.User
import com.google.android.gms.tasks.Task
import com.google.firebase.database.FirebaseDatabase

class FavoriteViewModel{

    val user = User.instance
    val database = FirebaseDatabase.getInstance().getReference()

    fun favoriteMovie(movie: Movie, callback: (status: Boolean, message: String?) -> Unit){
        try{
            database.child("favorited_movie").child(user.uuid).child(movie.title)
                    .setValue(movie).addOnCompleteListener {task: Task<Void> ->
                        if(task.isSuccessful){
                            return@addOnCompleteListener callback(true, null)
                        }else{
                            return@addOnCompleteListener callback(false, "Não foi Possível " +
                                    "favoritar filme. Tente mais tarde")
                        }
                    }
        }catch(e: Exception){
            return  callback(false, e.message)
        }

    }

    fun desfavoriteMovie(movie: Movie, callback: (status: Boolean, message: String?) -> Unit){
        try{
            database.child("favorited_movie").child(user.uuid).child(movie.title)
                    .removeValue().addOnCompleteListener {task: Task<Void> ->
                        if(task.isSuccessful){
                            return@addOnCompleteListener callback(true, null)
                        }else{
                            return@addOnCompleteListener callback(false, "Não foi Possível " +
                                    "desfavoritar filme. Tente mais tarde")
                        }
                    }
        }catch(e: Exception){
            return  callback(false, e.message)
        }
    }

}