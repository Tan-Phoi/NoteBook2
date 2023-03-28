package com.phoint.notebook.ui.changeAccountPassword

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.phoint.notebook.data.local.AppPreferences
import com.phoint.notebook.data.local.LocalRepository
import com.phoint.notebook.data.local.model.User
import com.phoint.notebook.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChangeAccountPasswordViewModel @Inject constructor(
    private val appPreferences: AppPreferences,
    private val localRepository: LocalRepository
) : BaseViewModel() {
    var userData = MutableLiveData<User>()
    init {

    }

    fun getUserId() : String{
        return appPreferences.getUserId()
    }

    fun getUserId(id : String){
        viewModelScope.launch(Dispatchers.IO){
            val user = localRepository.getUserData(id)
            if (user != null && user.idUser == id){
                userData.postValue(user)
            }
        }
    }

    fun updatePasswordUser(user: User){
        viewModelScope.launch(Dispatchers.IO){
            localRepository.updateUser(user)
        }
    }

}