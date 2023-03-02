package com.phoint.notebook.ui.insertNote

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.phoint.notebook.R
import com.phoint.notebook.data.local.model.NoteBook
import com.phoint.notebook.data.local.model.User
import com.phoint.notebook.databinding.FragmentInsertNoteBinding
import com.phoint.notebook.ui.base.BaseFragment
import com.phoint.notebook.ui.home.HomeViewModel

class InsertNoteFragment : BaseFragment<FragmentInsertNoteBinding, InsertNoteViewModel>() {

    override fun layoutRes(): Int {
        return R.layout.fragment_insert_note
    }

    override fun viewModelClass(): Class<InsertNoteViewModel> {
        return InsertNoteViewModel::class.java
    }

    override fun initView() {

        binding.abvNote.setOnClickSrcLeft {
            findNavController().navigate(R.id.action_insertNoteFragment_to_homeFragment)
        }

        val userId = arguments?.getInt("id", -1)
        if (userId != null) {
            viewModel.setUserId(userId)
        }

        binding.abvNote.setOnClickSrcRight {
            val title = binding.edtTitle.text.toString().trim()
            val noteTask = binding.edtNoteTask.text.toString().trim()

            val notebook = NoteBook().apply {
                idNote = View.generateViewId()
                userOwnerID = viewModel.userId
                titleNote = title
                taskNote = noteTask
                dateTimeNote = " "
            }
            if (title.isNotEmpty() && noteTask.isNotEmpty()) {
                if (id != null) {
                    viewModel.insertNote(notebook)
                    Toast.makeText(requireContext(), "Thêm note thành công!", Toast.LENGTH_SHORT)
                        .show()
                    findNavController().navigate(R.id.action_insertNoteFragment_to_homeFragment)
                } else {
                    Toast.makeText(requireContext(), "Không thể thêm note!", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(
                    requireContext(), "Vui lòng nhập title và content!", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}