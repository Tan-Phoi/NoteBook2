package com.phoint.notebook.ui.home

import android.os.Bundle
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.phoint.notebook.R
import com.phoint.notebook.data.local.model.NoteBook
import com.phoint.notebook.databinding.FragmentHomeBinding
import com.phoint.notebook.ui.base.BaseFragment
import com.phoint.notebook.ui.insertNote.InsertNoteFragment
import com.phoint.notebook.util.setOnSingClickListener

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    private var loggedInUserId : Int? = null
    private val listNote = ArrayList<NoteBook>()
    private var adapter: AdapterNote? = null
    override fun layoutRes(): Int {
        return R.layout.fragment_home
    }

    override fun viewModelClass(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

    override fun initView() {
        adapter = AdapterNote(listNote)
        binding.rcvNoteBook.adapter = adapter

        loggedInUserId = arguments?.getInt("id", -1)

        if (loggedInUserId != null) {
            viewModel.setUserId(loggedInUserId ?: -1)
        }

        binding.actionBarViewBinding.setOnClickSrcLeft {
            findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
        }


        binding.btnInsertNote.setOnSingClickListener {
            val bundle = Bundle().apply {
                putInt("id", viewModel.userId ?: -1)
            }
            findNavController().navigate(R.id.action_homeFragment_to_insertNoteFragment, bundle)
        }

        viewModel.noteList.observe(this) {
            listNote.addAll(it)
            adapter?.notifyDataSetChanged()
        }
    }

}