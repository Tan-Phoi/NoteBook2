package com.phoint.notebook.ui.login

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.phoint.notebook.R
import com.phoint.notebook.data.local.LocalRepository
import com.phoint.notebook.data.local.model.User
import com.phoint.notebook.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val localRepository: LocalRepository
) : BaseViewModel() {
    private var _loggedInUserId = MutableLiveData<Int>()
    val loggedInUserId : LiveData<Int> = _loggedInUserId
    var userId: Int? = null
    var doneLoginUser = MutableLiveData<Boolean>()

    //var userCheck = MutableLiveData<User>()
    //var doneUserGoogle = MutableLiveData<Boolean>()


    init {

    }

    fun setUserId(id: Int) {
        userId = id
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

     fun getUserByUsernameAndPassword(email : String, password : String) {
        viewModelScope.launch(Dispatchers.IO){
            val user = localRepository.getUserByUsernameAndPassword(email, password)
            if (user != null){
                _loggedInUserId.postValue(user.idUser)
                doneLoginUser.postValue(true)
            } else {
                doneLoginUser.postValue(false)
            }
        }
    }

}