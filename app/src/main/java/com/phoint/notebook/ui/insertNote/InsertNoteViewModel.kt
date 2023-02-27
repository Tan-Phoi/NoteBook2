package com.phoint.notebook.ui.insertNote

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
    var idList = MutableLiveData<NoteBook>()
    init {

    }

    fun insertNote(noteBook: NoteBook){
        viewModelScope.launch(Dispatchers.IO){
            val id = localRepository.getID()
            localRepository.insertNote(noteBook)
                idList.postValue(id)
                doneNote.postValue(true)
        }
    }
}