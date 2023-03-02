package com.phoint.notebook.ui.insertNote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.phoint.notebook.data.local.LocalRepository
import com.phoint.notebook.data.local.model.NoteBook
import com.phoint.notebook.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class InsertNoteViewModel @Inject constructor(
    private val localRepository: LocalRepository
) : BaseViewModel() {
    var doneNote = MutableLiveData<Boolean>()
    var userId: Int? = null

    init {

    }
    fun insertNote(noteBook: NoteBook){
        viewModelScope.launch(Dispatchers.IO){
            localRepository.insertNote(noteBook)
                doneNote.postValue(true)
        }
    }

    fun setUserId(id: Int) {
        userId = id
    }
}