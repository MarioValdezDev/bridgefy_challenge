package mx.mariovaldez.code_challenge.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class CodeChallengeApplication : Application() {

    override fun onCreate() {
        super.onCreate()

    }
}
