package com.example.thiago.applistmovies.View

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.example.thiago.applistmovies.Modelos.Movie
import com.example.thiago.applistmovies.R
import com.example.thiago.applistmovies.ViewModel.FavoriteViewModel
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.activity_favorite.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase



class FavoriteActivity : AppCompatActivity() {

    var isFavoriteMovie: Boolean = false
    val movie = Movie("data:image/jpeg;base64,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",
            "Titanic", "Um artista pobre e uma jovem rica se conhecem e se apaixonam " +
            "na fatídica jornada do Titanic, em 1912. Embora esteja noiva do" +
            " arrogante herdeiro de uma siderúrgica, a jovem desafia sua família e amigos em busca do verdadeiro amor.")
    val favoriteViewModel = FavoriteViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        title_favorite.text = movie.title
        description_favorite.text = movie.description
        star_favorite.setOnClickListener{
            setupFavoriteIcon()
        }
    }

    fun setupFavoriteIcon(){
        isFavoriteMovie = !isFavoriteMovie
        if(isFavoriteMovie){
            favoriteViewModel.favoriteMovie(movie){ status, message ->
                if(status){
                    isFavoriteMovie = true
                    star_favorite.setImageResource(R.drawable.ic_action_name_full)
                }else{
                    isFavoriteMovie = false
                    star_favorite.setImageResource(R.drawable.ic_action_name)
                    val builder =  AlertDialog.Builder(this@FavoriteActivity)
                    builder.setTitle("Atenção")
                    builder.setMessage(message)
                    builder.create().show()
                }
            }
        }else{
            favoriteViewModel.desfavoriteMovie(movie){status, message ->
                if(status){
                    isFavoriteMovie = false
                    star_favorite.setImageResource(R.drawable.ic_action_name)
                }else{
                    isFavoriteMovie = true
                    star_favorite.setImageResource(R.drawable.ic_action_name_full)
                    val builder =  AlertDialog.Builder(this@FavoriteActivity)
                    builder.setTitle("Atenção")
                    builder.setMessage(message)
                    builder.create().show()
                }
            }
        }
    }


}
