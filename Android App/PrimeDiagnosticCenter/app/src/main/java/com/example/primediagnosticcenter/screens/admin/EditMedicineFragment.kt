package com.example.primediagnosticcenter.screens.admin

import android.os.Bundle
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
import com.example.primediagnosticcenter.databinding.FragmentEditMedicineBinding
import kotlinx.coroutines.launch
import java.lang.Exception


class EditMedicineFragment : Fragment() {

    private lateinit var binding: FragmentEditMedicineBinding
    private lateinit var database: MyDatabaseDao


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_edit_medicine,
            container,
            false
        )

        val application = requireNotNull(this.activity).application
        database = MyDatabase.getInstance(application).myDatabaseDao
        val id = requireArguments().getLong("medicineId")
        var medicine: Medicine? = null
        lifecycleScope.launch {
            medicine = database.getMedicine(id)
            println("Medicine Edit: ID: $id,  $medicine")
            medicine?.let {
                binding.medicineName.setText(medicine?.name)
                binding.medicineDetail.setText(medicine?.detail)
                binding.medicinePrice.setText(medicine?.price.toString())
            }

        }
        medicine?.let {
            binding.medicineName.setText(medicine?.name)
            binding.medicineDetail.setText(medicine?.detail)
            binding.medicinePrice.setText(medicine?.price.toString())
        }

        binding.updateMedicine.setOnClickListener {
            lifecycleScope.launch {
                medicine?.let {
                    try {
                        medicine?.name = binding.medicineName.text.toString()
                        medicine?.detail = binding.medicineDetail.text.toString()
                        medicine?.price = binding.medicinePrice.text.toString().toDouble()
                        database.updateMedicine(medicine!!)
                        findNavController().navigateUp()
                        Toast.makeText(
                            context,
                            "Updated",
                            Toast.LENGTH_LONG
                        ).show()

                    } catch (e: Exception) {
                        kotlin.runCatching {
                            Toast.makeText(
                                context,
                                "Failed To Update, Please Fill Up Properly.",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }


                }
            }
        }

        return binding.root
    }
}