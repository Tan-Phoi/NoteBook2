package com.phoint.notebook.ui.createAccount

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.phoint.notebook.R
import com.phoint.notebook.data.local.model.User
import com.phoint.notebook.databinding.FragmentCreateAccountBinding
import com.phoint.notebook.ui.base.BaseFragment
import com.phoint.notebook.util.setOnSingClickListener


class CreateAccountFragment : BaseFragment<FragmentCreateAccountBinding, CreateAccountViewModel>() {

    override fun layoutRes(): Int {
        return R.layout.fragment_create_account
    }

    override fun viewModelClass(): Class<CreateAccountViewModel> {
        return CreateAccountViewModel::class.java
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun initView() {
        binding.tvSignIn.setOnSingClickListener {
            findNavController().navigate(R.id.action_createAccountFragment_to_loginFragment)
        }

        binding.edtEmail.addTextChangedListener {
            val email = binding.edtEmail.text.toString().trim()
            if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.edtEmail.error = null
            } else {
                binding.edtEmail.error = "Email không hợp lệ"
            }
            setEnable()
        }

        binding.edtName.addTextChangedListener {
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

        binding.btnCreateAccount.setOnSingClickListener {
            val name = binding.edtName.text.toString().trim()
            val email = binding.edtEmail.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()
            val confirmPassword = binding.edtConfirmPassword.text.toString().trim()

            val user = User().apply {
                idUser = System.currentTimeMillis().toInt()
                nameUser = name
                emailUser = email
                passwordUser = password
                confirmPasswordUser = confirmPassword
            }

            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
                viewModel.insertUser(email, user)
            } else {
                Toast.makeText(
                    requireContext(),
                    "Vui lòng nhập thông tin người dùng",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        viewModel.doneUser.observe(this) {
            if (it == true) {
                findNavController().navigate(R.id.action_createAccountFragment_to_loginFragment)
            } else {
                Toast.makeText(requireContext(), "Email đã được sử dụng", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isCheckEnable(): Boolean {
        val email = binding.edtEmail.text.toString().trim()
        val name = binding.edtName.text.toString().trim()
        val password = binding.edtPassword.text.toString().trim()
        val confirmPassword = binding.edtConfirmPassword.text.toString().trim()

        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
                name.isNotEmpty() &&
                email.isNotEmpty() &&
                password == confirmPassword &&
                password.isNotEmpty() &&
                confirmPassword.isNotEmpty()
    }

    private fun setEnable() {
        binding.btnCreateAccount.isEnabled = isCheckEnable()
        if (isCheckEnable()) {
            binding.btnCreateAccount.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.shape_button_login)
        } else {
            binding.btnCreateAccount.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.shape_button_login_false)
        }
    }
}