package mx.mariovaldez.code_challenge.ui

import androidx.annotation.StringRes
import mx.mariovaldez.code_challenge.databinding.LayoutToolbarBinding
import mx.mariovaldez.code_challenge.ktx.context


internal fun LayoutToolbarBinding.bind(
    @StringRes titleId: Int? = null,
    title: String? = null,
    onBackPressed: () -> Unit,
) {
    titleTextView.text = title ?: titleId?.let { context.getString(it) } ?: ""
    backButton.setOnClickListener { onBackPressed() }
}
