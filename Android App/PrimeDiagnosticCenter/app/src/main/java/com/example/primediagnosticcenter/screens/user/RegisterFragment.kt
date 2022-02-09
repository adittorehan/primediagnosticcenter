package com.example.primediagnosticcenter.screens.user

import android.os.Bundle
import android.provider.SyncStateContract.Helpers.insert
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.primediagnosticcenter.R
import com.example.primediagnosticcenter.database.MyDatabase
import com.example.primediagnosticcenter.database.MyDatabaseDao
import com.example.primediagnosticcenter.database.User
import com.example.primediagnosticcenter.databinding.FragmentRegisterBinding
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var dataSource: MyDatabaseDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_register,
            container,
            false
        )

        binding.registerText.setOnClickListener {
            findNavController().navigateUp()
        }


        val application = requireNotNull(this.activity).application
        // Create an instance of the ViewModel Factory.
        dataSource = MyDatabase.getInstance(application).myDatabaseDao

        binding.registerButton.setOnClickListener {
            register(dataSource)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            for (user in dataSource.getAllUsers()){
                println(user)
            }
        }

        return binding.root
    }

    private fun register(database: MyDatabaseDao) {
        val email = binding.registerEmail.text.toString().trim { it <= ' ' }
        val pass1 = binding.registerPassword1.text.toString().trim { it <= ' ' }
        val pass2 = binding.registerPassword2.text.toString().trim { it <= ' ' }
        if (pass1 != pass2) {
            Toast.makeText(
                this.context,
                "Passwords do not match please try again.",
                Toast.LENGTH_SHORT
            ).show()
        } else if (!TextUtils.isEmpty(email)
            && !TextUtils.isEmpty(pass1)
            && !TextUtils.isEmpty(pass2)
        ) {

            println("User Started")
            viewLifecycleOwner.lifecycleScope.launch {
                val user = User(email = email, password = pass1, admin_status = true)
                database.insert(user)
                findNavController().navigateUp()
                println("User: $user")
            }
            println("User Ended")

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