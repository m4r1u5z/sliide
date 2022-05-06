package com.example.sliide.presentation.ui

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.example.sliide.R

class RemoveUserDialog(
    context: Context,
    private val callback: () -> Unit,
) {
    private val dialog: AlertDialog = AlertDialog.Builder(context)
        .setTitle(R.string.remove_user_confirmation)
        .setPositiveButton(R.string.ok) { dialog, _ ->
            callback()
            dialog.dismiss()
        }
        .setNegativeButton(R.string.cancel) { dialog, _ ->
            dialog.dismiss()
        }
        .setCancelable(false)
        .create()

    fun show() {
        dialog.show()
    }
}
