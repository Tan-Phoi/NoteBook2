package com.phoint.notebook.ui.updateNote

import android.app.DatePickerDialog
import android.widget.DatePicker
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.phoint.notebook.R
import com.phoint.notebook.data.local.model.NoteBook
import com.phoint.notebook.databinding.FragmentUpdateNoteBinding
import com.phoint.notebook.ui.base.BaseFragment
import java.text.DateFormat
import java.util.*

class UpdateNoteFragment : BaseFragment<FragmentUpdateNoteBinding, UpdateNoteViewModel>(), DatePickerDialog.OnDateSetListener  {
    val cal: Calendar = Calendar.getInstance()
    private var day = 0
    private var month = 0
    private var year = 0
    private var saveday = 0
    private var savemonth = 0
    private var saveyear = 0

    override fun layoutRes(): Int {
        return R.layout.fragment_update_note
    }

    override fun viewModelClass(): Class<UpdateNoteViewModel> {
        return UpdateNoteViewModel::class.java
    }

    override fun initView() {

        startUpdatingDate()
        val note = arguments?.getParcelable("idNote") as? NoteBook
        binding.edtTitle.setText(note?.titleNote)
        binding.edtNoteTask.setText(note?.taskNote)

        val title = binding.edtTitle.text.toString().trim()
        val task = binding.edtNoteTask.text.toString().trim()

        binding.abvNote.setOnClickSrcRight {
            note?.titleNote = binding.edtTitle.text.toString().trim()
            note?.taskNote = binding.edtNoteTask.text.toString().trim()
            note?.dateTimeNote = binding.tvDateTime.text.toString().trim()

            if (title.isNotEmpty() && task.isNotEmpty()){
                if (note != null) {
                    viewModel.updateNote(note)
                    Toast.makeText(requireContext(), "Cập nhật thành công note", Toast.LENGTH_SHORT)
                    .show()
                    findNavController().navigate(R.id.action_updateUserFragment_to_homeFragment)
                }else {
                    Toast.makeText(requireContext(), "Không thể cập nhật note!", Toast.LENGTH_SHORT)
                        .show()
                }
            }else {
                Toast.makeText(requireContext(), "Vui lòng nhập title và content!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.abvNote.setOnClickSrcLeft {
            findNavController().navigate(R.id.action_updateUserFragment_to_homeFragment)
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

    private fun startUpdatingDate(){
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