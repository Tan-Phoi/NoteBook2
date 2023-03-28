package com.phoint.notebook.data.local.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
class User() : Parcelable{
    @PrimaryKey
    @ColumnInfo(name = "id_user")
    var idUser : String = ""
    var nameUser : String? = null
    var phoneUser : String? = null
    var addressUser : String? = null
    @ColumnInfo(name = "email_user")
    var emailUser : String? = null
    @ColumnInfo(name = "password_user")
    var passwordUser : String? = null
    var imgUrlUser : String? = null
    var dateOfBirthUser : String? = null
    var tokenUser : String? = null

    constructor(parcel: Parcel) : this() {
        idUser = parcel.readString().toString()
        nameUser = parcel.readString()
        phoneUser = parcel.readString()
        addressUser = parcel.readString()
        emailUser = parcel.readString()
        passwordUser = parcel.readString()
        imgUrlUser = parcel.readString()
        dateOfBirthUser = parcel.readString()
        tokenUser = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(idUser)
        parcel.writeString(nameUser)
        parcel.writeString(phoneUser)
        parcel.writeString(addressUser)
        parcel.writeString(emailUser)
        parcel.writeString(passwordUser)
        parcel.writeString(imgUrlUser)
        parcel.writeString(dateOfBirthUser)
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

