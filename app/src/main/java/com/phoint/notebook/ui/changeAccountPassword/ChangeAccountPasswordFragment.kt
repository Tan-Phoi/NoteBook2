package com.phoint.notebook.ui.changeAccountPassword

import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.phoint.notebook.R
import com.phoint.notebook.databinding.FragmentChangeAccountPasswordBinding
import com.phoint.notebook.ui.base.BaseFragment
import com.phoint.notebook.util.setOnSingClickListener

class ChangeAccountPasswordFragment : BaseFragment<FragmentChangeAccountPasswordBinding, ChangeAccountPasswordViewModel>() {
    override fun layoutRes(): Int {
        return R.layout.fragment_change_account_password
    }

    override fun viewModelClass(): Class<ChangeAccountPasswordViewModel> {
        return ChangeAccountPasswordViewModel::class.java
    }

    override fun initView() {
        val id = viewModel.getUserId()

        viewModel.getUserId(id)

        viewModel.userData.observe(this){
            Glide.with(requireContext()).load(it.imgUrlUser).into(binding.imgAvata)
            binding.tvName.text = it.nameUser
            binding.tvEmail.text = it.emailUser
            binding.tvPhone.text = it.phoneUser
            binding.tvAddress.text = it.addressUser
            binding.tvEmails.text = it.emailUser
            binding.tvID.text = it.idUser
        }

        binding.btnConfirmChange.setOnSingClickListener {
            val currentPassword = binding.edtCurrentPassword.text.toString().trim()
            val newPassword = binding.edtNewPassword.text.toString().trim()
            viewModel.userData.observe(this){
                if (currentPassword == it.passwordUser){
                        it.passwordUser = newPassword
                    viewModel.updatePasswordUser(it)
                    findNavController().navigate(R.id.action_changeAccountPasswordFragment_to_loginFragment)
                }
            }
        }

        binding.edtCurrentPassword.addTextChangedListener {
            setEnable()
        }

        binding.edtNewPassword.addTextChangedListener {
            val newPassword = binding.edtNewPassword.text.toString().trim()
            if (newPassword.length >= 6){
                binding.edtNewPassword.error = null
            }else {
                binding.edtNewPassword.error = "Password up to six characters"
            }
            setEnable()
        }

        binding.edtConfirmPassword.addTextChangedListener {
            val newPassword = binding.edtNewPassword.text.toString().trim()
            val confirmPassword = binding.edtConfirmPassword.text.toString().trim()
            if (newPassword == confirmPassword){
                binding.edtConfirmPassword.error = null
            }else {
                binding.edtConfirmPassword.error = "Password does not match!"
            }
            setEnable()
        }

    }

    private fun isEnable() : Boolean {
        val currentPassword = binding.edtCurrentPassword.text.toString().trim()
        val newPassword = binding.edtNewPassword.text.toString().trim()
        val confirmPassword = binding.edtConfirmPassword.text.toString().trim()
        return currentPassword.isNotEmpty() && newPassword.isNotEmpty() &&
                confirmPassword.isNotEmpty()
    }

    private fun setEnable(){
        binding.btnConfirmChange.isEnabled = isEnable()
        if(isEnable()){
            binding.btnConfirmChange.background = ContextCompat.getDrawable(requireContext(), R.drawable.shape_button_login)
        }else {
            binding.btnConfirmChange.background = ContextCompat.getDrawable(requireContext(), R.drawable.shape_button_login_false)
        }
    }
}