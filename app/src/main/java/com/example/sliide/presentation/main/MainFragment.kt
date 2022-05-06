package com.example.sliide.presentation.main

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.sliide.R
import com.example.sliide.databinding.FragmentMainBinding
import com.example.sliide.domain.entity.User
import com.example.sliide.presentation.extensions.gone
import com.example.sliide.presentation.extensions.visible
import com.example.sliide.presentation.main.list.UsersAdapter
import com.example.sliide.presentation.ui.AddUserDialog
import com.example.sliide.presentation.ui.RemoveUserDialog
import com.example.sliide.presentation.ui.SpaceItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModels()
    private val usersAdapter = UsersAdapter(this::showRemoveUserDialog)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentMainBinding.inflate(inflater, container, false).also { binding ->
        this.binding = binding
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.initUI()
        observeEvents()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.addUser -> {
            showAddUserDialog()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun FragmentMainBinding.initUI() {
        usersRv.addItemDecoration(
            SpaceItemDecoration(
                verticalSpace = resources.getDimensionPixelSize(R.dimen.users_adapter_vertical_spacing)
            )
        )
        usersRv.adapter = usersAdapter
    }

    private fun observeEvents() {
        viewModel.usersState.observe { state ->
            when (state) {
                UsersFetchingState.Error -> showError()
                is UsersFetchingState.Success -> showList(state.users)
                UsersFetchingState.Loading -> showLoading()
            }
        }
        viewModel.showMessage.observe { messageResId ->
            if (messageResId != -1) {
                showMessage(getString(messageResId))
            }
        }
    }

    private fun showRemoveUserDialog(user: User) {
        RemoveUserDialog(requireContext()) {
            viewModel.removeUser(user)
        }.show()
    }

    private fun showAddUserDialog() {
        AddUserDialog(requireContext()) {
            viewModel.addUser(it)
        }.show()
    }

    private fun showMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun showError() {
        binding.progressPb.gone()
        binding.errorTv.visible()
        binding.usersRv.gone()
    }

    private fun showLoading() {
        binding.progressPb.visible()
        binding.errorTv.gone()
        binding.usersRv.gone()
    }

    private fun showList(users: List<User>) {
        binding.progressPb.gone()
        binding.errorTv.gone()
        binding.usersRv.visible()
        usersAdapter.submitList(users)
    }

    private fun <ANY> StateFlow<ANY>.observe(block: (ANY) -> Unit) {
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            this@observe.collect { block(it) }
        }
    }
}
