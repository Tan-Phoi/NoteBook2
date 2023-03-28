package com.phoint.notebook.ui.userProfile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.phoint.notebook.R
import com.phoint.notebook.databinding.FragmentUserProfileBinding
import com.phoint.notebook.ui.base.BaseFragment
import com.phoint.notebook.util.setOnSingClickListener

class UserProfileFragment : BaseFragment<FragmentUserProfileBinding, UserProfileViewModel>() {
    private val startActivityForImage =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val imageUri = result.data?.data
                handleImageResult(imageUri)
            }
        }

    private fun handleImageResult(imageUri: Uri?) {
        try {
            val id =  viewModel.getUserId()
            viewModel.userData.observe(this){
                if (id == it.idUser){
                    it.imgUrlUser = imageUri.toString()
                    viewModel.updateImage(it)
                    Glide.with(requireContext()).load(it.imgUrlUser).into(binding.imgAvatar)
                }
            }
        }catch (e : Exception){
            e.printStackTrace()
        }
    }

    override fun layoutRes(): Int {
        return R.layout.fragment_user_profile
    }

    override fun viewModelClass(): Class<UserProfileViewModel> {
        return UserProfileViewModel::class.java
    }

    override fun initView() {
        configsProfileMobile()
        configsProfileAddress()
        configsChangePassword()
        configsProfileName()
        viewModel.getUserId(viewModel.getUserId())

        viewModel.userData.observe(this) {
            binding.tvID.text = it.idUser
            binding.tvName.text = it.nameUser
            binding.tvNames.text = it.nameUser
            binding.tvEmail.text = it.emailUser
            binding.tvPhone.text = it.phoneUser
            binding.tvAddress.text = it.addressUser
            binding.tvEmails.text = it.emailUser
            Glide.with(requireContext()).load(it.imgUrlUser).into(binding.imgAvatar)
        }

        binding.btnHome.setOnSingClickListener {
            findNavController().navigate(R.id.action_userProfileFragment_to_homeFragment)
        }

        binding.imgAvatar.setOnSingClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForImage.launch(intent)
        }
    }

    private fun configsProfileName() {
        binding.edtName.visibility = View.GONE

        binding.tvNames.setOnSingClickListener {
            binding.edtName.visibility = View.VISIBLE
        }

        binding.edtName.addTextChangedListener {
            val name = binding.edtName.text.toString().trim()
            if (name.isNotEmpty()) {
                binding.tvNames.text = "Done"
                binding.tvNames.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.blue
                    )
                )

                binding.tvNames.setOnSingClickListener {
                    val id = viewModel.getUserId()
                    viewModel.userData.observe(this) {
                        if (id == it.idUser) {
                            it.nameUser = binding.edtName.text.toString().trim()
                            viewModel.updateUser(it)
                            binding.tvNames.text = it.nameUser
                            binding.tvName.text = it.nameUser
                            binding.edtName.visibility = View.GONE
                            if (name.isNotEmpty()) {
                                binding.edtName.visibility = View.GONE
                            }
                        }
                    }
                }
            } else {
                binding.tvNames.text = "Change"
                binding.tvNames.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.blue
                    )
                )
                binding.edtName.visibility = View.GONE

                binding.edtName.setOnSingClickListener {
                    binding.edtName.visibility = View.VISIBLE
                }
            }
        }

        binding.tvNames.addTextChangedListener {
            val name = binding.tvNames.text.toString().trim()
            if (name.isEmpty()) {
                binding.tvNames.text = "Change"
                binding.tvNames.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.blue
                    )
                )
            } else {
                val name = binding.tvNames.text.toString().trim()
                if (name.isNotEmpty()) {
                    binding.tvNames.setOnSingClickListener {
                        binding.edtName.setText("")
                        binding.edtName.visibility = View.VISIBLE

                    }
                }
                binding.tvNames.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.gray
                    )
                )
            }
        }
    }

    private fun configsChangePassword() {
        binding.tvPassword.setOnSingClickListener {
            findNavController().navigate(R.id.action_userProfileFragment_to_changeAccountPasswordFragment)
        }
    }

    private fun configsProfileAddress() {

        binding.edtAddress.visibility = View.GONE

        binding.tvAddress.setOnSingClickListener {
            binding.edtAddress.visibility = View.VISIBLE
        }

        binding.edtAddress.addTextChangedListener {
            val address = binding.edtAddress.text.toString().trim()
            if (address.isNotEmpty()) {
                binding.tvAddress.text = "Done"
                binding.tvAddress.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.blue
                    )
                )

                binding.tvAddress.setOnSingClickListener {
                    val id = viewModel.getUserId()
                    viewModel.userData.observe(this) {
                        if (id == it.idUser) {
                            it.addressUser = binding.edtAddress.text.toString().trim()
                            viewModel.updateUser(it)
                            binding.tvAddress.text = it.addressUser
                            binding.edtAddress.visibility = View.GONE
                            if (address.isNotEmpty()) {
                                binding.edtAddress.visibility = View.GONE
                            }
                        }
                    }
                }
            } else {
                binding.tvAddress.text = "Change"
                binding.tvAddress.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.blue
                    )
                )
                binding.edtAddress.visibility = View.GONE

                binding.tvAddress.setOnSingClickListener {
                    binding.edtAddress.visibility = View.VISIBLE
                }
            }
        }

        binding.tvAddress.addTextChangedListener {
            val address = binding.tvAddress.text.toString().trim()
            if (address.isEmpty()) {
                binding.tvAddress.text = "Change"
                binding.tvAddress.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.blue
                    )
                )
            } else {
                val address = binding.tvAddress.text.toString().trim()
                if (address.isNotEmpty()) {
                    binding.tvAddress.setOnSingClickListener {
                        binding.edtAddress.setText("")
                        binding.edtAddress.visibility = View.VISIBLE

                    }
                }
                binding.tvAddress.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.gray
                    )
                )
            }
        }
    }

    private fun configsProfileMobile() {
        binding.edtPhone.visibility = View.GONE

        binding.tvPhone.setOnSingClickListener {
            binding.edtPhone.visibility = View.VISIBLE
        }

        binding.edtPhone.addTextChangedListener {
            val phone = binding.edtPhone.text.toString().trim()
            if (phone.isNotEmpty()) {
                binding.tvPhone.text = "Done"
                binding.tvPhone.setTextColor(ContextCompat.getColor(requireContext(), R.color.blue))

                binding.tvPhone.setOnSingClickListener {
                    val id = viewModel.getUserId()
                    viewModel.userData.observe(this) {
                        if (id == it.idUser) {
                            it.phoneUser = binding.edtPhone.text.toString().trim()
                            viewModel.updateUser(it)
                            binding.tvPhone.text = it.phoneUser
                            binding.edtPhone.visibility = View.GONE
                            if (phone.isNotEmpty()) {
                                binding.edtPhone.visibility = View.GONE
                            }
                        }
                    }
                }
            } else {
                binding.tvPhone.text = "Change"
                binding.tvPhone.setTextColor(ContextCompat.getColor(requireContext(), R.color.blue))
                binding.edtPhone.visibility = View.GONE

                binding.tvPhone.setOnSingClickListener {
                    binding.edtPhone.visibility = View.VISIBLE
                }
            }
        }

        binding.tvPhone.addTextChangedListener {
            val phone = binding.tvPhone.text.toString().trim()
            if (phone.isEmpty()) {
                binding.tvPhone.text = "Change"
                binding.tvPhone.setTextColor(ContextCompat.getColor(requireContext(), R.color.blue))
            } else {
                val phone = binding.tvPhone.text.toString().trim()
                if (phone.isNotEmpty()) {
                    binding.tvPhone.setOnSingClickListener {
                        binding.edtPhone.setText("")
                        binding.edtPhone.visibility = View.VISIBLE

                    }
                }
                binding.tvPhone.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray))
            }
        }
    }
}