package com.phoint.notebook.data.local.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
class User() : Parcelable{
    @PrimaryKey
    var idUser : Int? = null
    var nameUser : String? = null
    var emailUser : String? = null
    var passwordUser : String? = null
    var confirmPasswordUser : String? = null
    var imgUrlUser : String? = null
    var tokenUser : String? = null

    constructor(parcel: Parcel) : this() {
        idUser = parcel.readValue(Int::class.java.classLoader) as? Int
        nameUser = parcel.readString()
        emailUser = parcel.readString()
        passwordUser = parcel.readString()
        confirmPasswordUser = parcel.readString()
        imgUrlUser = parcel.readString()
        tokenUser = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(idUser)
        parcel.writeString(nameUser)
        parcel.writeString(emailUser)
        parcel.writeString(passwordUser)
        parcel.writeString(confirmPasswordUser)
        parcel.writeString(imgUrlUser)
        parcel.writeString(tokenUser)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }

}

