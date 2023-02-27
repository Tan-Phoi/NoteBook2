package com.phoint.notebook.ui.insertNote

import android.annotation.SuppressLint
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.phoint.notebook.R
import com.phoint.notebook.data.local.model.NoteBook
import com.phoint.notebook.data.local.model.User
import com.phoint.notebook.databinding.FragmentInsertNoteBinding
import com.phoint.notebook.ui.base.BaseFragment

class InsertNoteFragment : BaseFragment<FragmentInsertNoteBinding, InsertNoteViewModel>() {
    override fun layoutRes(): Int {
        return R.layout.fragment_insert_note
    }

    override fun viewModelClass(): Class<InsertNoteViewModel> {
        return InsertNoteViewModel::class.java
    }

    override fun initView() {
        binding.abvNote.setOnClickSrcRight {
            val id = arguments?.getParcelable("id") as? User

            val title = binding.edtTitle.text.toString().trim()
            val noteTask = binding.edtNoteTask.text.toString().trim()
                val notebook = NoteBook().apply {
                    idNote = View.generateViewId()
                    userOwnerID = id?.idUser
                    titleNote = title
                    taskNote = noteTask
                    dateTimeNote = "12/2/2023"
                }
                if (title.isNotEmpty() && noteTask.isNotEmpty()){
                    viewModel.insertNote(notebook)
                }
        }

        viewModel.doneNote.observe(this){
            if (it == true){
                val id = arguments?.getParcelable("id") as? User
                    findNavController().navigate(R.id.action_insertNoteFragment_to_homeFragment,
                        bundleOf(
                            Pair("id", id)
                        )
                    )
            }
        }
    }

}