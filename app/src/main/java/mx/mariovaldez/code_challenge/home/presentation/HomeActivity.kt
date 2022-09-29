package mx.mariovaldez.code_challenge.home.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import mx.mariovaldez.code_challenge.R
import mx.mariovaldez.code_challenge.databinding.ActivityHomeBinding
import mx.mariovaldez.code_challenge.ktx.observe
import mx.mariovaldez.code_challenge.ktx.viewBinding

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModels()
    private val binding: ActivityHomeBinding by viewBinding(
        ActivityHomeBinding::inflate
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupViews()
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.event.observe(this, ::handle)
    }

    private fun setupViews() {
        //implement required logic
    }

    private fun handle(event: HomeViewModel.Event) {
        if (event.type == HomeViewModel.EventType.NAVIGATION) {
            handleNavigation(event)
        } else {
            // Another type of event.
        }
    }

    private fun handleNavigation(event: HomeViewModel.Event) {
        val fragment: Fragment = when (event) {
            is HomeViewModel.Event.NavigateToMovieDetail -> {
                DetailMovieFragment.newInstance()
            }
            is HomeViewModel.Event.NavigateToListMovies -> {
                ListMoviesFragment.newInstance()
            }
        }
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_container_view,
                fragment,
                fragment::class.java.simpleName
            )
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    companion object {

        fun launch(from: Context) =
            from.startActivity(Intent(from, HomeActivity::class.java))
    }
}