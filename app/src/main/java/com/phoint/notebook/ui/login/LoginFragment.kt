package com.phoint.notebook.ui.login

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.phoint.notebook.R
import com.phoint.notebook.data.local.model.User
import com.phoint.notebook.databinding.FragmentLoginBinding
import com.phoint.notebook.ui.base.BaseFragment
import com.phoint.notebook.ui.home.HomeFragment
import com.phoint.notebook.util.setOnSingClickListener

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {

//    private var mGoogleSignInClient : GoogleSignInClient? = null
//    private val startForResultGoogle = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result : ActivityResult ->
//        if (result.resultCode == Activity.RESULT_OK){
//            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
//            handleGoogleSignInResult(task)
//        }
//    }
//
//    private fun handleGoogleSignInResult(task: Task<GoogleSignInAccount>) {
//        try {
//            val account = task.getResult(ApiException::class.java)
//           // viewModel.insertUserGoogle(account)
//        }catch (e : ApiException){
//            e.printStackTrace()
//        }
//    }

//    private fun setupGoogle(){
//        val clientIn = "608297475213-22ubtl4r67gnv61h1dit4t3ejp7hqgev.apps.googleusercontent.com"
//        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestEmail()
//            .requestIdToken(clientIn)
//            .build()
//        mGoogleSignInClient = GoogleSignIn.getClient(requireContext(), gso)
//        mGoogleSignInClient?.signOut()
//    }

    override fun layoutRes(): Int {
        return R.layout.fragment_login
    }

    override fun viewModelClass(): Class<LoginViewModel> {
        return LoginViewModel::class.java
    }

    override fun initView() {


//        setupGoogle()
        binding.tvCreateLogin.setOnSingClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_createAccountFragment)
        }

        binding.btnLoginIn.setOnSingClickListener {
            val email = binding.edtEmail.text.toString().trim()
            val password = binding.edtPassword.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()){
                viewModel.getUserByUsernameAndPassword(email, password)
            }else {
                Toast.makeText(requireContext(), "Vui lòng nhập tài khoản", Toast.LENGTH_SHORT).show()
            }
        }


        viewModel.doneLoginUser.observe(this){ boolean ->
            if (boolean == true){
                viewModel.loggedInUserId.observe(this){ loggedInUserId ->
                    viewModel.setUserId(loggedInUserId ?: -1)

                    val bundle = Bundle().apply {
                        putInt("id", viewModel.userId ?: -1)
                    }

                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment, bundle)
                }
            }else {
                Toast.makeText(requireContext(), "Sai tài khoản", Toast.LENGTH_SHORT).show()
            }
        }

//        binding.btnLoginGoogle.setOnSingClickListener {
//            val intent = mGoogleSignInClient?.signInIntent
//            startForResultGoogle.launch(intent)
//        }

//        viewModel.doneUserGoogle.observe(this){b ->
//            if (b == true){
//                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
//            }else {
//                Toast.makeText(requireContext(), "Không thành công", Toast.LENGTH_SHORT).show()
//            }
//        }
    }

}