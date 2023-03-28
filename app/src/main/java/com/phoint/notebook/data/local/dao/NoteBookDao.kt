package com.phoint.notebook.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.phoint.notebook.data.local.model.NoteBook

@Dao
interface NoteBookDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNote(noteBook: NoteBook)

    @Query("SELECT * FROM notebook WHERE user_id = :userId order by idNote asc")
    suspend fun getJoinData(userId: String) : List<NoteBook>

    @Query("SELECT * FROM notebook WHERE user_id = :userId")
    suspend fun setNoteNotification(userId: String) : NoteBook

    @Update
    suspend fun updateNote(noteBook: NoteBook)

    @Query("SELECT * FROM notebook where title_note LIKE :searchQuery or task_note LIKE :searchQuery")
    suspend fun searchNotebooks(searchQuery : String) : List<NoteBook>

    @Query("DELETE FROM notebook WHERE idNote =:id")
    suspend fun deleteNote(id : Int)

}