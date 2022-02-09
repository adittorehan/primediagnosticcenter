package com.example.primediagnosticcenter.screens.admin

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.primediagnosticcenter.R
import com.example.primediagnosticcenter.database.Medicine
import com.example.primediagnosticcenter.database.MyDatabase
import com.example.primediagnosticcenter.database.MyDatabaseDao
import com.example.primediagnosticcenter.database.User
import com.example.primediagnosticcenter.databinding.FragmentAddMedicineBinding
import kotlinx.coroutines.launch


class AddMedicineFragment : Fragment() {
    private lateinit var binding: FragmentAddMedicineBinding
    private lateinit var database: MyDatabaseDao
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_add_medicine, container, false
        )
        val application = requireNotNull(this.activity).application
        database = MyDatabase.getInstance(application).myDatabaseDao
        binding.addMedicine.setOnClickListener { addMedicine() }
        return binding.root
    }

    private fun addMedicine() {

        val name = binding.medicineName.text.toString().trim { it <= ' ' }
        val genericName = binding.medicineGenericName.text.toString().trim { it <= ' ' }
        val strength = binding.medicineStrength.text.toString().trim { it <= ' ' }
        val company = binding.medicineCompany.text.toString().trim { it <= ' ' }
        val price = binding.medicinePrice.text.toString().trim { it <= ' ' }
        if (name.isEmpty() || genericName.isEmpty() || strength.isEmpty()
            || company.isEmpty() || price.isEmpty()
        ) {
            Toast.makeText(this.context, "Please fill up all the fields", Toast.LENGTH_LONG).show()
        } else {
            val medicine = Medicine(
                name = name,
                detail = "$genericName, ${strength.toDouble()}mg, $company",
                price = price.toDouble()
            )

            viewLifecycleOwner.lifecycleScope.launch {
                database.insertMedicine(medicine)
                println("Medicine: $medicine")
            }


            Toast.makeText(this.context, "Medicine is added", Toast.LENGTH_LONG).show()
            findNavController().navigateUp()

        }

    }
}