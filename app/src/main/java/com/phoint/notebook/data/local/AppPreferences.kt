package com.phoint.notebook.data.local

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppPreferences @Inject constructor(private val preferences: SharedPreferences) {
    companion object{
        private const val USER_ID_KEY = "user_id_key"
        private const val NOTE_ID_KEY = "note_id_key"
    }

    fun saveUserId(userId : String){
        preferences.edit().putString(USER_ID_KEY, userId).apply()
    }

    fun getUserId() : String{
        return preferences.getString(USER_ID_KEY, "") ?: ""
    }

    fun saveNoteId(noteId : Int){
        preferences.edit().putInt(NOTE_ID_KEY, noteId).apply()
    }

    fun getNoteId() : Int{
        return preferences.getInt(NOTE_ID_KEY, 0) ?: 0
    }
}