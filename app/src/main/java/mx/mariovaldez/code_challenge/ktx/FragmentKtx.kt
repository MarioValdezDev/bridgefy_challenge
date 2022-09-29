package mx.mariovaldez.code_challenge.ktx

import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import mx.mariovaldez.code_challenge.ui.delegates.FragmentViewBindingDelegate

fun <T : ViewBinding> Fragment.viewBinding(viewBindingFactory: (View) -> T) =
    FragmentViewBindingDelegate(this, viewBindingFactory)
