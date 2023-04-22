package com.training.movieapp.common

import android.content.Context
import android.content.res.Resources

fun Context.getHeight() = Resources.getSystem().displayMetrics.heightPixels

fun Context.getWidth() = Resources.getSystem().displayMetrics.widthPixels