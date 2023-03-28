package com.phoint.notebook.ui.insertNote

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.phoint.notebook.data.local.AppPreferences
import com.phoint.notebook.data.local.LocalRepository
import com.phoint.notebook.data.local.model.NoteBook
import com.phoint.notebook.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class InsertNoteViewModel @Inject constructor(
    private val localRepository: LocalRepository,
    private val appPreferences: AppPreferences,
    private val context: Context,
) : BaseViewModel() {
    var doneNote = MutableLiveData<Boolean>()
    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    init {

    }

    fun getUserId(): String {
        return appPreferences.getUserId()
    }

    fun insertNote(noteBook: NoteBook) {
        viewModelScope.launch(Dispatchers.IO) {
            localRepository.insertNote(noteBook)
            doneNote.postValue(true)
        }
    }

//    fun sendNotification() {
//        val channelId = "default_channel_id"
//        val channelName = "Default Channel"
//
//        val title = "Thông Báo"
//        val message = "Sau 24h sẽ thông báo lai ghi chú này"
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val channel = NotificationChannel(
//                channelId,
//                channelName,
//                NotificationManager.IMPORTANCE_DEFAULT
//            )
//            notificationManager.createNotificationChannel(channel)
//        }
//
//        val notificationBuilder = NotificationCompat.Builder(context, channelId)
//            .setSmallIcon(android.R.drawable.ic_dialog_info)
//            .setContentTitle(title)
//            .setContentText(message)
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//
//        notificationManager.notify(1, notificationBuilder.build())
//    }
}