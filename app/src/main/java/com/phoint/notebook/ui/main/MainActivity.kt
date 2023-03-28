package com.phoint.notebook.ui.main

import com.phoint.notebook.R
import com.phoint.notebook.databinding.ActivityMainBinding
import com.phoint.notebook.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override fun layoutRes(): Int {
        return R.layout.activity_main
    }

    override fun viewModelClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun initView() {
    }

}