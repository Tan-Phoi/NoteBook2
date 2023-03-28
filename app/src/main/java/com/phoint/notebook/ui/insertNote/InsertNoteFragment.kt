package com.phoint.notebook.ui.insertNote

import android.app.DatePickerDialog
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.phoint.notebook.R
import com.phoint.notebook.data.local.model.NoteBook
import com.phoint.notebook.databinding.FragmentInsertNoteBinding
import com.phoint.notebook.ui.base.BaseFragment
import java.text.DateFormat
import java.util.*

class InsertNoteFragment : BaseFragment<FragmentInsertNoteBinding, InsertNoteViewModel>(),
    DatePickerDialog.OnDateSetListener {
    private val cal: Calendar = Calendar.getInstance()
    private var day = 0
    private var month = 0
    private var year = 0
    private var saveday = 0
    private var savemonth = 0
    private var saveyear = 0

    override fun layoutRes(): Int {
        return R.layout.fragment_insert_note
    }

    override fun viewModelClass(): Class<InsertNoteViewModel> {
        return InsertNoteViewModel::class.java
    }

    override fun initView() {
        startUpdatingDate()
        binding.abvNote.setOnClickSrcLeft {
            findNavController().navigate(R.id.action_insertNoteFragment_to_homeFragment)
        }

        binding.abvNote.setOnClickSrcRight {
            val title = binding.edtTitle.text.toString().trim()
            val noteTask = binding.edtNoteTask.text.toString().trim()
            val dateTime = binding.tvDateTime.text.toString().trim()
            val notebook = NoteBook().apply {
                idNote = View.generateViewId()
                userOwnerID = viewModel.getUserId()
                titleNote = title
                taskNote = noteTask
                dateTimeNote = dateTime
            }

            if (title.isNotEmpty() && noteTask.isNotEmpty()) {
                val id = viewModel.getUserId()
                if (id != null) {
                    viewModel.insertNote(notebook)
                    viewModel.doneNote.observe(this) {
                        if (it == true) {
                            findNavController().navigate(R.id.action_insertNoteFragment_to_homeFragment)
                            Toast.makeText(
                                requireContext(),
                                "Thêm note thành công!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                } else {
                    Toast.makeText(requireContext(), "Không thể thêm note!", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(
                    requireContext(),
                    "Vui lòng nhập title và content!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun getDateTimeCalendar() {
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        saveday = dayOfMonth
        savemonth = month + 1
        saveyear = year
        getDateTimeCalendar()
        binding.tvDateTime.text = "$saveday/$savemonth/$saveyear \t"
    }

    private fun startUpdatingDate() {
        val timer = Timer()
        val task = object : TimerTask() {
            override fun run() {
                cal.time = Date()
                activity?.runOnUiThread {
                    binding.tvDateTime.text = DateFormat.getDateInstance().format(cal.time)
                }
            }
        }
        timer.scheduleAtFixedRate(task, 0, 24 * 60 * 60 * 1000)
    }
}


