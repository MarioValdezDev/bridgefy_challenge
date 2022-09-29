package mx.mariovaldez.code_challenge.home.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import mx.mariovaldez.code_challenge.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    companion object {

        fun launch(from: Context) =
            from.startActivity(Intent(from, HomeActivity::class.java))
    }
}