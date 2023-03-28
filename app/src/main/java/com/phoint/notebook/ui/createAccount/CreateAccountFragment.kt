package com.phoint.notebook.ui.createAccount

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.DatePicker
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.phoint.notebook.R
import com.phoint.notebook.data.local.model.User
import com.phoint.notebook.databinding.FragmentCreateAccountBinding
import com.phoint.notebook.ui.base.BaseFragment
import com.phoint.notebook.util.setOnSingClickListener
import java.text.DateFormat
import java.util.*

class CreateAccountFragment : BaseFragment<FragmentCreateAccountBinding, CreateAccountViewModel>(), DatePickerDialog.OnDateSetListener {
    var day = 0
    var month = 0
    var year = 0

    var saveday = 0
    var savemonth = 0
    var saveyear = 0

    override fun layoutRes(): Int {
        return R.layout.fragment_create_account
    }

    override fun viewModelClass(): Class<CreateAccountViewModel> {
        return CreateAccountViewModel::class.java
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun initView() {
        binding.btnCreateAccount.setOnSingClickListener {
            val name = binding.edtName.text.toString().trim()
            val email = binding.edtEmail.text.toString().trim()
            val dateOfBrith = binding.tvDateOfBrith.text.toString().trim()
            val phone = binding.edtPhone.text.toString().trim()
            val address = binding.edtAddress.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()
            val confirmPassword = binding.edtConfirmPassword.text.toString().trim()

            val user = User().apply {
                idUser = System.currentTimeMillis().toString()
                nameUser = name
                emailUser = email
                dateOfBirthUser = dateOfBrith
                phoneUser = phone
                addressUser = address
                passwordUser = password
            }

            if (name.isNotEmpty() && email.isNotEmpty() && phone.isNotEmpty() && address.isNotEmpty()
                && password.isNotEmpty() && confirmPassword.isNotEmpty() && dateOfBrith.isNotEmpty()){

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

        binding.edtPhone.addTextChangedListener {
            val phone = binding.edtPhone.text.toString().trim()
            if (android.util.Patterns.PHONE.matcher(phone).matches()){
                binding.edtPhone.error = null
            }else {
                binding.edtPhone.error = "Số điện thoại không hợp lệ!"
            }
            setEnable()
        }

        binding.edtAddress.addTextChangedListener {
            setEnable()
        }


        binding.tvDateOfBrith.setOnSingClickListener {
           showDatePickerDialog()
            setEnable()
        }

        binding.edtPassword.addTextChangedListener {
            val password = binding.edtPassword.text.toString().trim()
            if(password.length >= 6){
                binding.edtPassword.error = null
            }else {
                binding.edtPassword.error = "Mật khẩu phải từ 6 ký tự trở lên"
            }
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
    }

    private fun isCheckEnable(): Boolean {
        val ngaySinh = binding.tvDateOfBrith.text.toString().trim()
        val email = binding.edtEmail.text.toString().trim()
        val name = binding.edtName.text.toString().trim()
        val phone = binding.edtPhone.text.toString().trim()
        val address = binding.edtAddress.text.toString().trim()
        val password = binding.edtPassword.text.toString().trim()
        val confirmPassword = binding.edtConfirmPassword.text.toString().trim()

        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
                android.util.Patterns.PHONE.matcher(phone).matches() &&
                name.isNotEmpty() &&
                email.isNotEmpty() &&
                password == confirmPassword &&
                password.isNotEmpty() &&
                phone.isNotEmpty() &&
                address.isNotEmpty() &&
                confirmPassword.isNotEmpty()&&
                ngaySinh.isNotEmpty()
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

    private fun getDateTimeCalendar() {
        val cal: Calendar = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH) + 1
        year = cal.get(Calendar.YEAR)
    }

private fun showDatePickerDialog() {
    getDateTimeCalendar()
    val datePickerDialog = DatePickerDialog(
        requireContext(),
        this,
        year,
        month,
        day
    )
    datePickerDialog.show()
    binding.tvDateOfBrith.setTextColor(ContextCompat.getColor(requireContext(), R.color.back))
}
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        getDateTimeCalendar()
        val selectedDate = "$dayOfMonth/${month + 1}/$year"
        binding.tvDateOfBrith.text = selectedDate
    }
}
