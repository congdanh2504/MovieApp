package com.training.movieapp.common

import android.view.View
import android.widget.TextView

fun TextView.setErrorText(error: String) {
    visibility = View.VISIBLE
    text = error
}