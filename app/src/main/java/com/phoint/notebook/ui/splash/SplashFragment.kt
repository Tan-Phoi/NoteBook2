package com.phoint.notebook.ui.splash

import androidx.navigation.fragment.findNavController
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import com.phoint.notebook.R
import com.phoint.notebook.databinding.FragmentSplashBinding
import com.phoint.notebook.ui.base.BaseFragment

class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>() {
    override fun layoutRes(): Int = R.layout.fragment_splash

    override fun viewModelClass(): Class<SplashViewModel> = SplashViewModel::class.java

    override fun initView() {
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        }, 3000)
    }
}