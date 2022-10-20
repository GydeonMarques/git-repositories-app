package br.com.android.commons.util

import android.widget.ImageView
import androidx.annotation.DrawableRes
import br.com.android.commons.R
import coil.load

fun ImageView.loadImageByUrl(url: String, @DrawableRes fallback: Int = R.drawable.ic_user) {
    load(url) {
        crossfade(true)
        fallback(fallback)
        placeholder(fallback)
    }
}