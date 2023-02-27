package com.phoint.notebook.data.local.dao

import androidx.room.*
import com.phoint.notebook.data.local.model.User

@Dao
interface UserDao {

    @Query("select * from User where emailUser=:email")
    suspend fun getAllUserEmail(email : String) : User

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser( user: User)

    @Query("select * from User where emailUser =:email and passwordUser =:password and idUser")
    suspend fun getAllEmailPassword(email: String, password : String) : User
}