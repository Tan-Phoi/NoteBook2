package com.phoint.notebook.data.local.dao

import androidx.room.*
import com.phoint.notebook.data.local.model.User

@Dao
interface UserDao {

    @Query("select * from User where emailUser=:email")
    suspend fun getAllUserEmail(email : String) : User

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser( user: User)

    @Query("SELECT * FROM user WHERE emailUser = :emailUser AND passwordUser = :password")
    suspend fun getUserByUsernameAndPassword(emailUser: String, password: String): User
}