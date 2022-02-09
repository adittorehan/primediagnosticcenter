package com.example.primediagnosticcenter.screens.contact

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.primediagnosticcenter.R
import com.example.primediagnosticcenter.databinding.FragmentContactBinding


class ContactFragment : Fragment() {
    private lateinit var binding: FragmentContactBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_contact,
            container,
            false
        )
        val name = binding.name.text
        val phone = binding.phone.text
        val email = binding.email.text
        val msg = binding.message.text

        binding.contactBtn.setOnClickListener {

            if (name.isEmpty() ||
                email.isEmpty() ||
                msg.isEmpty()
            ) {
                Toast.makeText(
                    this.context,
                    "You forgot to fill one of the fields: Name, Email or Message.",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                val intent = Intent(Intent.ACTION_SENDTO)
                intent.data = Uri.parse("mailto:")
                intent.putExtra(Intent.EXTRA_EMAIL, email)
                intent.putExtra(Intent.EXTRA_SUBJECT, "Query - Prime Diagnostic Center")
                intent.putExtra(Intent.EXTRA_TEXT, "$msg, \n Your Phone: $phone")
                if (this.activity?.packageManager?.let { intent.resolveActivity(it) } != null) {
                    startActivity(intent);
                }
                findNavController().navigateUp()
            }
        }

        return binding.root
    }


}