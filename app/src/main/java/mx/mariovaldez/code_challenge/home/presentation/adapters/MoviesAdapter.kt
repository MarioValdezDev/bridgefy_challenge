package mx.mariovaldez.code_challenge.home.presentation.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mx.mariovaldez.code_challenge.BuildConfig
import mx.mariovaldez.code_challenge.R
import mx.mariovaldez.code_challenge.databinding.ItemMovieBinding
import mx.mariovaldez.code_challenge.home.presentation.models.MovieUI

internal class MoviesAdapter(
    private val listener: (Int) -> Unit,
) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private val movies: MutableList<MovieUI> = mutableListOf()
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        context = parent.context
        return ViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int): Unit =
        holder.bind(movies[position], position)

    override fun getItemCount(): Int = movies.size

    @SuppressLint("NotifyDataSetChanged")
    fun addMovies(movies: List<MovieUI>) {
        this.movies.clear()
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        private val binding: ItemMovieBinding,
        private val context: Context,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieUI, position: Int) = with(binding) {
            Glide.with(context)
                .load(BuildConfig.BASE_URL_IMAGES + movie.backdropPath)
                .placeholder(R.drawable.marvel_icon)
                .circleCrop()
                .error(R.drawable.marvel_icon)
                .into(movieImageView)
            titleMovieTextView.text = movie.originalTitle
            genderMovieTextView.text = "Action"
            languageMovieTextView.text = movie.originalLanguage
            rateTextView.text = movie.voteAverage.toString()
            root.setOnClickListener { listener(position) }
        }
    }
}
