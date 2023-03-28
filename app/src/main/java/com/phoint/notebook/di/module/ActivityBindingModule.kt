package com.phoint.notebook.di.module

import com.phoint.notebook.ui.changeAccountPassword.ChangeAccountPasswordFragment
import com.phoint.notebook.ui.createAccount.CreateAccountFragment
import com.phoint.notebook.ui.forgotPassword.ForgotPasswordFragment
import com.phoint.notebook.ui.home.HomeFragment
import com.phoint.notebook.ui.insertNote.InsertNoteFragment
import com.phoint.notebook.ui.login.LoginFragment
import com.phoint.notebook.ui.main.MainActivity
import com.phoint.notebook.ui.splash.SplashFragment
import com.phoint.notebook.ui.updateNote.UpdateNoteFragment
import com.phoint.notebook.ui.userProfile.UserProfileFragment
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
    abstract fun bindUpdateNoteFragment(): UpdateNoteFragment

    @ContributesAndroidInjector
    abstract fun bindForgotPasswordFragment(): ForgotPasswordFragment

    @ContributesAndroidInjector
    abstract fun bindUserProfileFragment(): UserProfileFragment

    @ContributesAndroidInjector
    abstract fun bindChangeAccountPasswordFragment(): ChangeAccountPasswordFragment
}