package com.example.sliide.presentation.main.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sliide.databinding.ItemUserBinding
import com.example.sliide.domain.entity.User

class UsersAdapter(
    private val onLongClick: (User) -> Unit,
) : ListAdapter<User, UsersItemViewHolder>(UsersDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersItemViewHolder =
        UsersItemViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ) { position ->
            onLongClick(getItem(position))
        }

    override fun onBindViewHolder(holder: UsersItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class UsersItemViewHolder(
    private val binding: ItemUserBinding,
    private val onLongClick: (Int) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnLongClickListener {
            onLongClick(adapterPosition)
            true
        }
    }

    fun bind(user: User) {
        binding.item = user
    }
}
