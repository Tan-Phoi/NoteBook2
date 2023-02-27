package com.phoint.notebook.ui.home

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.phoint.notebook.data.local.LocalRepository
import com.phoint.notebook.data.local.model.NoteBook
import com.phoint.notebook.data.local.model.User
import com.phoint.notebook.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val localRepository: LocalRepository
) : BaseViewModel() {
    var noteList = MutableLiveData<List<NoteBook>>()
    init {
        getJoinData()
    }

   private fun getJoinData(){
        viewModelScope.launch(Dispatchers.IO) {
            val note = localRepository.getJoinData()
                noteList.postValue(note)
        }
    }
}
