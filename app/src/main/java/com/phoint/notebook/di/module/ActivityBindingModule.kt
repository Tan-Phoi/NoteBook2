package com.phoint.notebook.di.module

import com.phoint.notebook.ui.createAccount.CreateAccountFragment
import com.phoint.notebook.ui.home.HomeFragment
import com.phoint.notebook.ui.insertNote.InsertNoteFragment
import com.phoint.notebook.ui.login.LoginFragment
import com.phoint.notebook.ui.main.MainActivity
import com.phoint.notebook.ui.splash.SplashFragment
import com.phoint.notebook.ui.updateUser.UpdateUserFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindSplashFragment(): SplashFragment

    @ContributesAndroidInjector
    abstract fun bindHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun bindLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun bindCreateAccountFragment(): CreateAccountFragment

    @ContributesAndroidInjector
    abstract fun bindInsertNoteFragment(): InsertNoteFragment

    @ContributesAndroidInjector
    abstract fun bindUpdateUserFragment(): UpdateUserFragment
}