package com.phoint.notebook.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.phoint.notebook.data.local.dao.NoteBookDao
import com.phoint.notebook.data.local.dao.UserDao
import com.phoint.notebook.data.local.model.NoteBook
import com.phoint.notebook.data.local.model.User

@Database(
    entities = [User::class, NoteBook::class],
    version = 1,
    exportSchema = false
)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun noteBookDao(): NoteBookDao
}