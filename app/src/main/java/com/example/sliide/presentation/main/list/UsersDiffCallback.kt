package com.example.sliide.presentation.main.list

import androidx.recyclerview.widget.DiffUtil
import com.example.sliide.domain.entity.User

class UsersDiffCallback : DiffUtil.ItemCallback<User>() {

    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
        oldItem.name == newItem.name

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
        oldItem == newItem
}
