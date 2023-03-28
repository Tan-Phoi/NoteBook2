package com.phoint.notebook.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.phoint.notebook.di.viewmodel.ViewModelFactory
import com.phoint.notebook.di.viewmodel.ViewModelKey
import com.phoint.notebook.ui.changeAccountPassword.ChangeAccountPasswordViewModel
import com.phoint.notebook.ui.createAccount.CreateAccountViewModel
import com.phoint.notebook.ui.forgotPassword.ForgotPasswordViewModel
import com.phoint.notebook.ui.home.HomeViewModel
import com.phoint.notebook.ui.insertNote.InsertNoteViewModel
import com.phoint.notebook.ui.login.LoginViewModel
import com.phoint.notebook.ui.main.MainViewModel
import com.phoint.notebook.ui.splash.SplashViewModel
import com.phoint.notebook.ui.updateNote.UpdateNoteViewModel
import com.phoint.notebook.ui.userProfile.UserProfileViewModel
import dagger.Module
import dagger.Binds
import dagger.multibindings.IntoMap
@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun mainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    internal abstract fun splashViewModel(viewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal abstract fun homeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    internal abstract fun loginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CreateAccountViewModel::class)
    internal abstract fun createAccountViewModel(viewModel: CreateAccountViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UpdateNoteViewModel::class)
    internal abstract fun updateNoteViewModel(viewModel: UpdateNoteViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(InsertNoteViewModel::class)
    internal abstract fun insertNoteViewModel(viewModel: InsertNoteViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ForgotPasswordViewModel::class)
    internal abstract fun forgotPasswordViewModel(viewModel: ForgotPasswordViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UserProfileViewModel::class)
    internal abstract fun userProfileViewModel(viewModel: UserProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ChangeAccountPasswordViewModel::class)
    internal abstract fun changeAccountPasswordViewModel(viewModel: ChangeAccountPasswordViewModel): ViewModel
}