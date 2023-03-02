package com.phoint.notebook.data.local

import androidx.lifecycle.LiveData
import com.phoint.notebook.data.local.dao.NoteBookDao
import com.phoint.notebook.data.local.dao.UserDao
import com.phoint.notebook.data.local.model.NoteBook
import com.phoint.notebook.data.local.model.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalRepository @Inject constructor(
    private val userDao: UserDao,
    private val noteBookDao: NoteBookDao
) {
// User
    suspend fun getAllUserEmail(email : String) : User{
         return userDao.getAllUserEmail(email)
    }

    suspend fun insertUser(user: User){
        userDao.insertUser(user)
    }

    suspend fun getUserByUsernameAndPassword(email: String, password : String) : User{
        return userDao.getUserByUsernameAndPassword(email, password)
    }

//...............................................................................

// NoteBook

    suspend fun insertNote(noteBook: NoteBook){
        noteBookDao.insertNote(noteBook)
    }

    suspend fun getJoinData() : List<NoteBook>{
        return noteBookDao.getJoinData()
    }
}

