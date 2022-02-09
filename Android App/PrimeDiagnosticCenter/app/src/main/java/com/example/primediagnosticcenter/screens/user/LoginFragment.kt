package com.example.primediagnosticcenter.screens.user

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.primediagnosticcenter.MainActivity
import com.example.primediagnosticcenter.R
import com.example.primediagnosticcenter.database.ActiveUser
import com.example.primediagnosticcenter.database.MyDatabase
import com.example.primediagnosticcenter.database.MyDatabaseDao
import com.example.primediagnosticcenter.databinding.FragmentLoginBinding
import com.example.primediagnosticcenter.network.Api
import kotlinx.coroutines.launch


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private var start = true;
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )


        binding.loginButton.setOnClickListener {
            login()
        }
        binding.registerText.setOnClickListener {
            findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            )

        }



        return binding.root
    }

    private fun login() {
        val email = binding.loginEmail.text.toString().trim { it <= ' ' }
        val pass = binding.loginPassword.text.toString().trim { it <= ' ' }


        if (!TextUtils.isEmpty(email)
            && !TextUtils.isEmpty(pass)
        ) {

            println("Login Started")
            viewLifecycleOwner.lifecycleScope.launch {
                // TODO Implement Login Logic using Retrofit
                val s = Api.retrofitService.isAdminUser(email, pass)
                if (s.found) {
                    MainActivity.adminUser = true
                    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToAdminFragment())

                }

//                val user = dataSource.getUser(email, pass)
//                println("User: ${user ?: "nouser"}")
//
//                if (user != null) {
//                    dataSource.clearActiveUserLog()
//                    dataSource.insertUserLog(ActiveUser(user.userId, true))
//                    if (user.admin_status) {
//                        MainActivity.adminUser = true
//                        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToAdminFragment())
//                    }
//                }
            }
            println("Login Ended")

        } else {
            if (email.isEmpty()) Toast.makeText(
                this.context,
                "Please provide an email.",
                Toast.LENGTH_SHORT
            ).show()
            else {
                Toast.makeText(
                    this.context,
                    "Please provide password.",
                    Toast.LENGTH_SHORT
                ).show()

            }

        }
    }


}