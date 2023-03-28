package com.phoint.notebook.data.local

import android.provider.ContactsContract.CommonDataKinds.Note
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
    suspend fun getGoogleEmailUser(email : String) : User{
         return userDao.getGoogleEmailUser(email)
    }

    suspend fun insertUser(user: User){
        userDao.insertUser(user)
    }

    suspend fun getUserByUsernameAndPassword(email: String, password : String) : User{
        return userDao.getUserByUsernameAndPassword(email, password)
    }

    suspend fun updateUser(user: User){
        userDao.updateUser(user)
    }

    suspend fun getUserData(id : String) : User{
        return userDao.getUserId(id)
    }

    suspend fun getEmailAndPassword(email: String, password: String) : User{
        return userDao.getEmailAndPassword(email, password)
    }
//...............................................................................

// NoteBook

    suspend fun insertNote(noteBook: NoteBook){
        noteBookDao.insertNote(noteBook)
    }

    suspend fun getJoinData(userId : String) : List<NoteBook>{
        return noteBookDao.getJoinData(userId)
    }

    suspend fun updateNote(noteBook: NoteBook){
        noteBookDao.updateNote(noteBook)
    }

    suspend fun searchNotebooks(searchQuery : String) : List<NoteBook>{
        return noteBookDao.searchNotebooks("%$searchQuery%")
    }

    suspend fun deleteNote(id : Int){
         noteBookDao.deleteNote(id)
    }
}

