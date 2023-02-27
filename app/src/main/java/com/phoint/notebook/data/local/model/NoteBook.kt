package com.phoint.notebook.data.local.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notebook")
class NoteBook() : Parcelable{
    @PrimaryKey
    var idNote : Int? = null
    @ColumnInfo(name ="user_id")
    var userOwnerID : Int? = null
    var titleNote : String? = null
    var taskNote : String? = null
    var dateTimeNote : String? = null

    constructor(parcel: Parcel) : this() {
        idNote = parcel.readValue(Int::class.java.classLoader) as? Int
        userOwnerID = parcel.readValue(Int::class.java.classLoader) as? Int
        titleNote = parcel.readString()
        taskNote = parcel.readString()
        dateTimeNote = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(idNote)
        parcel.writeValue(userOwnerID)
        parcel.writeString(titleNote)
        parcel.writeString(taskNote)
        parcel.writeString(dateTimeNote)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NoteBook> {
        override fun createFromParcel(parcel: Parcel): NoteBook {
            return NoteBook(parcel)
        }

        override fun newArray(size: Int): Array<NoteBook?> {
            return arrayOfNulls(size)
        }
    }
}