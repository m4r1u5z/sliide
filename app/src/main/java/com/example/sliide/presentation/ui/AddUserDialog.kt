package com.example.sliide.presentation.ui

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.example.sliide.R
import com.example.sliide.domain.entity.User
import com.google.android.material.textfield.TextInputLayout

class AddUserDialog(
    context: Context,
    private val callback: (User) -> Unit,
) {

    private val dialog: AlertDialog

    init {
        val customView = LayoutInflater.from(context)
            .inflate(R.layout.dialog_add_user, null, false)
        val nameTil = customView.findViewById<TextInputLayout>(R.id.nameTil)
        val emailTil = customView.findViewById<TextInputLayout>(R.id.emailTil)
        dialog = AlertDialog.Builder(context)
            .setTitle(R.string.add_user)
            .setPositiveButton(R.string.add_user) { dialog, _ ->
                val name = nameTil.editText?.text.toString()
                val email = emailTil.editText?.text.toString()
                val user = User(name = name, email = email)
                callback(user)
                dialog.dismiss()
            }
            .setNegativeButton(R.string.cancel) { dialog, _ ->
                dialog.dismiss()
            }
            .setView(customView)
            .setCancelable(false)
            .create()
    }

    fun show() {
        dialog.show()
    }
}
