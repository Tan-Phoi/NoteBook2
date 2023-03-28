package com.phoint.notebook.ui.home

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.phoint.notebook.data.local.AppPreferences
import com.phoint.notebook.data.local.LocalRepository
import com.phoint.notebook.data.local.model.NoteBook
import com.phoint.notebook.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val localRepository: LocalRepository,
    private val appPreferences: AppPreferences
) : BaseViewModel() {
    var noteList = MutableLiveData<List<NoteBook>>()
    private var searchQueryResult  = MutableLiveData<List<NoteBook>>()
    init {

    }
    fun getUserId(): String{
        return appPreferences.getUserId()
    }

    @SuppressLint("SuspiciousIndentation")
    fun getJoinData(userId : String) {
        viewModelScope.launch(Dispatchers.IO) {
            val note = localRepository.getJoinData(userId)
                noteList.postValue(note)
        }
    }

    fun searchNotebooks(query: String){
        viewModelScope.launch(Dispatchers.IO){
            val searchResult = localRepository.searchNotebooks(query)
            searchQueryResult.postValue(searchResult)
        }
    }

    fun getSearchResult(): LiveData<List<NoteBook>> {
        return searchQueryResult
    }

    fun delete(id : Int){
        viewModelScope.launch(Dispatchers.IO){
            localRepository.deleteNote(id)
        }
    }
}
