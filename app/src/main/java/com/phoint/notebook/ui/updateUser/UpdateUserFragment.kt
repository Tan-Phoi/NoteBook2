package com.phoint.notebook.ui.updateUser

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.phoint.notebook.R
import com.phoint.notebook.databinding.FragmentUpdateUserBinding
import com.phoint.notebook.ui.base.BaseFragment

class UpdateUserFragment : BaseFragment<FragmentUpdateUserBinding, UpdateUserViewModel>() {
    override fun layoutRes(): Int {
        return R.layout.fragment_update_user
    }

    override fun viewModelClass(): Class<UpdateUserViewModel> {
        return UpdateUserViewModel::class.java
    }

    override fun initView() {

    }
}