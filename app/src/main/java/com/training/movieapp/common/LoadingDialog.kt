package com.training.movieapp.common

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.widget.ImageView
import com.training.movieapp.R

class LoadingDialog(context: Context): Dialog(context, R.style.ProgressHUD) {

    override fun onStart() {
        super.onStart()
        setCancelable(false)
        setContentView(R.layout.progress_hud)
        val back = findViewById<ImageView>(R.id.spinnerImageView).background as AnimationDrawable
        back.start()
    }
}