package com.phoint.notebook.ui.login

import android.app.Activity
import android.graphics.Paint
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.phoint.notebook.R
import com.phoint.notebook.databinding.FragmentLoginBinding
import com.phoint.notebook.ui.base.BaseFragment
import com.phoint.notebook.util.setOnSingClickListener
import java.util.*

@Suppress("UNREACHABLE_CODE")
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {
    private var mGoogleSignInClient: GoogleSignInClient? = null
    private val startForResultGoogle =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                handleGoogleSignInResult(task)
            }
        }

    override fun layoutRes(): Int {
        return R.layout.fragment_login
    }

    override fun viewModelClass(): Class<LoginViewModel> {
        return LoginViewModel::class.java
    }

    override fun initView() {
        setupGoogle()
        setUnderlined()
        binding.tvForgotPassword.setOnSingClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
        }

        binding.tvCreateLogin.setOnSingClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_createAccountFragment)
        }

        binding.btnLoginIn.setOnSingClickListener {
            val email = binding.edtEmail.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.getUserByUsernameAndPassword(email, password)
            } else {
                Toast.makeText(requireContext(), "Vui lòng nhập tài khoản", Toast.LENGTH_SHORT)
                    .show()
            }
        }


        viewModel.doneLoginUser.observe(this) { boolean ->
            if (boolean == true) {
                viewModel.loggedInUserId.observe(this) { loggedInUserId ->
                    viewModel.saveUserId(loggedInUserId)
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                }
            } else {
                Toast.makeText(requireContext(), "Sai tài khoản", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnLoginGoogle.setOnSingClickListener {
            val intent = mGoogleSignInClient?.signInIntent
            startForResultGoogle.launch(intent)
        }

        viewModel.doneUserGoogle.observe(this) { b ->
            if (b == true) {
                viewModel.loggedInUserId.observe(this) {
                    viewModel.saveUserId(it)
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                }
            } else {
                Toast.makeText(requireContext(), "Không thành công", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setUnderlined() {
        binding.textView.paintFlags = binding.textView.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        binding.tvForgotPassword.paintFlags =
            binding.tvForgotPassword.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        binding.tvCreateLogin.paintFlags =
            binding.tvCreateLogin.paintFlags or Paint.UNDERLINE_TEXT_FLAG
    }

    private fun handleGoogleSignInResult(task: Task<GoogleSignInAccount>) {
        try {
            val account = task.getResult(ApiException::class.java)
            viewModel.insertUserGoogle(account)
        } catch (e: ApiException) {
            e.printStackTrace()
        }
    }

    private fun setupGoogle() {
        val clientIn = "608297475213-22ubtl4r67gnv61h1dit4t3ejp7hqgev.apps.googleusercontent.com"
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestIdToken(clientIn)
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(requireContext(), gso)
        mGoogleSignInClient?.signOut()
    }
}