package com.phoint.notebook.ui.updateNote

import androidx.lifecycle.viewModelScope
import com.phoint.notebook.data.local.LocalRepository
import com.phoint.notebook.data.local.model.NoteBook
import com.phoint.notebook.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class UpdateNoteViewModel @Inject constructor(
    private val localRepository: LocalRepository
) : BaseViewModel() {
    init {

    }

    fun updateNote(noteBook: NoteBook){
        viewModelScope.launch(Dispatchers.IO) {
            localRepository.updateNote(noteBook)
        }
    }

}