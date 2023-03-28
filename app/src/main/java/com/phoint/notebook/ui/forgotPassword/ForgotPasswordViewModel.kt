package com.phoint.notebook.ui.forgotPassword

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.phoint.notebook.data.local.LocalRepository
import com.phoint.notebook.data.local.model.User
import com.phoint.notebook.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ForgotPasswordViewModel @Inject constructor(
    private val localRepository: LocalRepository
) : BaseViewModel() {
    var iscChangePassword = MutableLiveData<Boolean>()
    var isDone = MutableLiveData<Boolean>()
    var donecChangePassword = MutableLiveData<User>()
    init {

    }

    fun changePassword(email : String){
        viewModelScope.launch(Dispatchers.IO){
            val donePassword = localRepository.getGoogleEmailUser(email)
            if (donePassword != null && donePassword.emailUser == email){
                donecChangePassword.postValue(donePassword)
                iscChangePassword.postValue(true)
            }
        }
    }

    fun updateUser(user: User){
        viewModelScope.launch(Dispatchers.IO){
            localRepository.updateUser(user)
            isDone.postValue(true)
        }
    }

}
