package com.phoint.notebook.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.phoint.notebook.data.local.LocalRepository
import com.phoint.notebook.data.local.model.User
import com.phoint.notebook.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val localRepository: LocalRepository
) : BaseViewModel() {
    //var doneUserGoogle = MutableLiveData<Boolean>()
    var doneLoginUser = MutableLiveData<Boolean>()
    var userCheck = MutableLiveData<User>()

    init {

    }

//    fun insertUserGoogle(account: GoogleSignInAccount){
//        val id = account.id
//        val name = account.displayName
//        val email = account.email
//        val imgUrl = account.photoUrl
//        val token = account.idToken
//
//        val user = User().apply {
//            idUser = id.toString()
//            nameUser = name ?:""
//            emailUser = email ?: ""
//            imgUrlUser = imgUrl.toString()
//            tokenUser = token ?: ""
//        }
//        viewModelScope.launch(Dispatchers.IO){
//            localRepository.insertUser(user)
//            doneUserGoogle.postValue(true)
//        }
//    }

    fun getAllEmailPassword(email : String, password : String) {
        viewModelScope.launch(Dispatchers.IO){
            val user = localRepository.getAllEmailPassword(email, password)
            if (user != null){
                userCheck.postValue(user)
                doneLoginUser.postValue(true)
            } else {
                doneLoginUser.postValue(false)
            }
        }
    }
}