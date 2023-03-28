package com.phoint.notebook.data.local.dao

import androidx.room.*
import com.phoint.notebook.data.local.model.User

@Dao
interface UserDao {

    @Query("select * from user where email_user =:email")
    suspend fun getGoogleEmailUser(email : String) : User

    @Update
    suspend fun updateUser(user: User)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser( user: User)

    @Query("SELECT * FROM user WHERE email_user = :emailUser AND password_user = :password")
    suspend fun getUserByUsernameAndPassword(emailUser: String, password: String): User

    @Query("Select * from user where id_user =:id")
    suspend fun getUserId(id : String) : User

    @Query("Select * from user where email_user =:email and password_user =:password")
    suspend fun getEmailAndPassword(email: String, password: String) : User
}