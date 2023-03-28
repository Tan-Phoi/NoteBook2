package com.phoint.notebook.ui.forgotPassword

import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.phoint.notebook.R
import com.phoint.notebook.databinding.FragmentForgotPasswordBinding
import com.phoint.notebook.ui.base.BaseFragment
import com.phoint.notebook.util.setOnSingClickListener

class ForgotPasswordFragment : BaseFragment<FragmentForgotPasswordBinding, ForgotPasswordViewModel>() {
    override fun layoutRes(): Int {
        return R.layout.fragment_forgot_password
    }

    override fun viewModelClass(): Class<ForgotPasswordViewModel> {
        return ForgotPasswordViewModel::class.java
    }

    override fun initView() {
        binding.edtEmail.addTextChangedListener {
            setEnable()
        }

        binding.edtPassword.addTextChangedListener {
            setEnable()
        }

        binding.edtConfirmPassword.addTextChangedListener {
            val password = binding.edtPassword.text.toString().trim()
            val confirmPassword = binding.edtConfirmPassword.text.toString().trim()
            if (password == confirmPassword) {
                binding.edtConfirmPassword.error = null
            } else {
                binding.edtConfirmPassword.error = "Password không trùng nhau"
            }
            setEnable()
        }
        binding.btnPasswordUpdate.setOnSingClickListener {
            val email = binding.edtEmail.text.toString().trim()
            val newPassword = binding.edtPassword.text.toString().trim()
            val confirmPassword = binding.edtConfirmPassword.text.toString().trim()
            if (email.isNotEmpty() && newPassword.isNotEmpty() && confirmPassword.isNotEmpty()){
                viewModel.changePassword(email)
            }else {
                Toast.makeText(requireContext(), "Yêu cầu nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.iscChangePassword.observe(this){
            val email = binding.edtEmail.text.toString().trim()
            val newPassword = binding.edtPassword.text.toString().trim()
            if (it == true){
                viewModel.donecChangePassword.observe(this){
                    it.emailUser = email
                    it.passwordUser = newPassword
                    if (it.emailUser == email){
                        viewModel.updateUser(it)
                    }
                }

            }
        }

        viewModel.isDone.observe(this){
            if (it == true){
                findNavController().navigate(R.id.action_forgotPasswordFragment_to_loginFragment)
            }
        }
    }

    private fun isEnable() : Boolean {
        val email = binding.edtEmail.text.toString().trim()
        val newPassword = binding.edtPassword.text.toString().trim()
        val confirmPassword = binding.edtConfirmPassword.text.toString().trim()
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
                email.isNotEmpty() && newPassword.isNotEmpty() && confirmPassword.isNotEmpty()
    }

    private fun setEnable() {
        binding.btnPasswordUpdate.isEnabled = isEnable()
        if (isEnable()){
            binding.btnPasswordUpdate.background = ContextCompat.getDrawable(requireContext(), R.drawable.shape_button_login)
        }else {
            binding.btnPasswordUpdate.background = ContextCompat.getDrawable(requireContext(), R.drawable.shape_button_login_false)
        }
    }
}