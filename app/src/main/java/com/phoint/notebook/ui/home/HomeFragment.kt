package com.phoint.notebook.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.phoint.notebook.R
import com.phoint.notebook.data.local.model.NoteBook
import com.phoint.notebook.data.local.model.User
import com.phoint.notebook.databinding.FragmentHomeBinding
import com.phoint.notebook.ui.base.BaseFragment
import com.phoint.notebook.util.setOnSingClickListener

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
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

        binding.actionBarViewBinding.setOnClickSrcLeft {
            findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
        }

        binding.btnInsertNote.setOnSingClickListener {
            val id: User? = arguments?.getParcelable("id") as? User
            val bundle = Bundle()
            bundle.putParcelable("id", id)
            findNavController().navigate(
                R.id.action_homeFragment_to_insertNoteFragment, bundle
            )
        }

        //val userOwnerID: NoteBook? = arguments?.getParcelable("userOwnerID") as? NoteBook

        viewModel.noteList.observe(this) {
            listNote.addAll(it)
            adapter?.notifyDataSetChanged()
        }
    }

}