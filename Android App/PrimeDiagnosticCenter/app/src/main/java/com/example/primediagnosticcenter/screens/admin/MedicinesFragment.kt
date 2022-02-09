package com.example.primediagnosticcenter.screens.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.primediagnosticcenter.R
import com.example.primediagnosticcenter.database.MyDatabase
import com.example.primediagnosticcenter.databinding.FragmentMedicinesBinding
import kotlinx.coroutines.launch

class MedicinesFragment : Fragment() {
    private lateinit var binding: FragmentMedicinesBinding
    private lateinit var medicinesAdapter: MedicinesRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_medicines, container, false)
        val application = requireNotNull(this.activity).application
        // Create an instance of the ViewModel Factory.
        val dataSource = MyDatabase.getInstance(application).myDatabaseDao
        val viewModelFactory = MedicinesViewModelFactory(dataSource, application)

        // Get a reference to the ViewModel associated with this fragment.
        val medicinesViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(MedicinesViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.medicinesViewModel = medicinesViewModel

        medicinesAdapter = MedicinesRecyclerAdapter(findNavController())
        binding.medicinesList.adapter = medicinesAdapter
        binding.addMedicineBtn.setOnClickListener {
            findNavController().navigate(MedicinesFragmentDirections.actionMedicinesFragmentToAddMedicineFragment())
            lifecycleScope.launch {
                medicinesAdapter.medicines.addAll(dataSource.getAllMedicines())
                for (medicine in dataSource.getAllMedicines()){
                    println(medicine)
                }
                medicinesAdapter.notifyDataSetChanged()
            }

        }
        lifecycleScope.launch {
            medicinesAdapter.medicines.addAll(dataSource.getAllMedicines())
            for (medicine in dataSource.getAllMedicines()){
                println(medicine)
            }
            medicinesAdapter.notifyDataSetChanged()
        }
        return binding.root
    }


}