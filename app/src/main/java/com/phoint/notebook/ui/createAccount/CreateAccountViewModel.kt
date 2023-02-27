package com.phoint.notebook.ui.createAccount

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.phoint.notebook.data.local.LocalRepository
import com.phoint.notebook.data.local.model.User
import com.phoint.notebook.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.internal.userAgent
import javax.inject.Inject

class CreateAccountViewModel @Inject constructor(
    private val localRepository : LocalRepository
) : BaseViewModel() {
    var doneUser = MutableLiveData<Boolean>()
    init {

    }

    fun insertUser(email: String, user: User){
        viewModelScope.launch(Dispatchers.IO){
            val userEmail = localRepository.getAllUserEmail(email)
            if (userEmail == null){
                localRepository.insertUser(user)
                doneUser.postValue(true)
            }else {
                doneUser.postValue(false)
            }
        }
    }
}