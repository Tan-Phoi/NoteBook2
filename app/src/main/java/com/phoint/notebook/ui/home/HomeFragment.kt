package com.phoint.notebook.ui.home

import android.annotation.SuppressLint
import android.app.*
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.navigation.fragment.findNavController
import com.phoint.notebook.R
import com.phoint.notebook.data.local.model.NoteBook
import com.phoint.notebook.databinding.FragmentHomeBinding
import com.phoint.notebook.ui.base.BaseFragment
import com.phoint.notebook.ui.widget.ActionBarViewBinding
import com.phoint.notebook.util.setOnSingClickListener
import java.util.*


class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    private val listNote = ArrayList<NoteBook>()
    private var adapter: AdapterNote? = null

    override fun layoutRes(): Int {
        return R.layout.fragment_home
    }

    override fun viewModelClass(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun initView() {
        adapter = AdapterNote(listNote)
        binding.rcvNoteBook.adapter = adapter

        binding.btnInsertNote.setOnSingClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_insertNoteFragment)
        }

        val userId = viewModel.getUserId()
        viewModel.getJoinData(userId)

        adapter?.itemOnClick = {
            val bundle = Bundle().apply {
                putParcelable("idNote", it)
            }
            findNavController().navigate(R.id.action_homeFragment_to_updateUserFragment, bundle)
        }

        viewModel.noteList.observe(this) {
            listNote.addAll(it)
            adapter?.notifyDataSetChanged()
        }

        binding.svNotebooks.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    viewModel.searchNotebooks(newText)
                    viewModel.getSearchResult().observe(viewLifecycleOwner) {
                        adapter?.setData(it)
                    }
                }
                return true
            }
        })

        binding.actionBarViewBinding.onMenuItemClickListener =
            object : ActionBarViewBinding.OnMenuItemClickListener {
                override fun onMenuItemClick(menuItem: MenuItem): Boolean {
                    when (menuItem.itemId) {
                        R.id.mnuTTND -> {
                            findNavController().navigate(R.id.action_homeFragment_to_userProfileFragment)
                            return true
                        }

                        R.id.mnuDangXuat -> {
                            findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
                            return true
                        }

                        R.id.mnuThoat -> {
                            activity?.finish()
                            return true
                        }
                    }
                    return false
                }
            }

        adapter?.itemOnClickDelect = {
            viewModel.delete(it.idNote!!)
            listNote.remove(it)
            adapter?.notifyDataSetChanged()
        }
    }
}
