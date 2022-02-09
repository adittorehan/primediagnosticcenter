package com.example.primediagnosticcenter.screens.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.primediagnosticcenter.R
import com.example.primediagnosticcenter.databinding.FragmentAdminBinding


class AdminFragment : Fragment() {
    private lateinit var binding: FragmentAdminBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_admin,
            container,
            false)

        binding.medicinesBtn.setOnClickListener {
            findNavController().navigate(AdminFragmentDirections.actionAdminFragmentToMedicinesFragment())
        }

        binding.appointments.setOnClickListener {
            findNavController().navigate(AdminFragmentDirections.actionAdminFragmentToManageAppointmentsFragment())
        }
        return binding.root
    }



}