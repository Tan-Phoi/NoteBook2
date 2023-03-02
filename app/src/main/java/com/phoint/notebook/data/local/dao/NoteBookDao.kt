package com.phoint.notebook.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.phoint.notebook.data.local.model.NoteBook
import com.phoint.notebook.data.local.model.User

@Dao
interface NoteBookDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNote(noteBook: NoteBook)

    @Query("SELECT * FROM user INNER JOIN notebook ON user_id = notebook.user_id")
    suspend fun getJoinData() : List<NoteBook>
}