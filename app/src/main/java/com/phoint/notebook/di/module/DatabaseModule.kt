package com.phoint.notebook.di.module

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import androidx.room.Room
import com.phoint.notebook.data.local.LocalDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(appContext: Context): LocalDatabase {
        return Room
            .databaseBuilder(
                appContext.applicationContext,
                LocalDatabase::class.java,
                "localDb"
            )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideAppPreference(appContext: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(appContext)
    }

    @Singleton
    @Provides
    fun provideUserDao(db: LocalDatabase) =
        db.userDao()

    @Singleton
    @Provides
    fun provideNoteBookDao(db: LocalDatabase) =
        db.noteBookDao()
}