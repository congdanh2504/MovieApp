package com.training.movieapp.common

import android.app.Dialog
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.training.movieapp.R

class LoadingDialog(private val fragmentManager: FragmentManager) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val childDialog = Dialog(requireContext(), R.style.ProgressHUD)
        childDialog.setContentView(R.layout.progress_hud)
        val back =
            childDialog.findViewById<ImageView>(R.id.spinnerImageView).background as AnimationDrawable
        back.start()
        return childDialog
    }

    override fun onStart() {
        super.onStart()
        dialog?.setCancelable(false)
    }

    fun show() {
        show(fragmentManager, TAG)
    }

    fun safeDismiss() {
        if (isAdded) {
            this.dismiss()
        }
    }

    companion object {
        private const val TAG = "PurchaseConfirmationDialog"
    }
}