package com.example.primediagnosticcenter.screens.admin


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.primediagnosticcenter.R
import com.example.primediagnosticcenter.databinding.FragmentAddDoctorBinding


class AddDoctorFragment : Fragment() {
    private lateinit var binding: FragmentAddDoctorBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_add_doctor, container, false
        )

        binding.addDoctor.setOnClickListener { addDoctor() }
        return binding.root
    }

    private fun addDoctor() {

        val name = binding.doctorName.text.toString().trim { it <= ' ' }
        val designation = binding.doctorDesignation.text.toString().trim { it <= ' ' }
        val department = binding.doctorDepartment.text.toString().trim { it <= ' ' }
//        if (name.isEmpty() || designation.isEmpty() || department.isEmpty()) {
//            Toast.makeText(this.context, "Please fill up all the fields", Toast.LENGTH_LONG).show()
//        } else {
//            val doctor = DoctorDataClass(
//                name = name,
//                designation = designation,
//                department = department
//            )
//
//            Log.i("AddDoctorFragment", "Doctor method called.")
//            val user: FirebaseUser? = FirebaseAuth.getInstance().currentUser
//            user?.apply {
//                Log.i("AddDoctorFragment", "User: ${user.email}")
//
//                val database =
//                    FirebaseDatabase.getInstance("https://primediagnosticcenter-7a191-default-rtdb.asia-southeast1.firebasedatabase.app")
//                database.getReference("Doctors")
//                    .get().addOnSuccessListener {
//                        if (it.exists()) {
//                            val id: Long = it.childrenCount + 1
//                            it.key?.let { it1 -> Log.i("Doctor", it1) }
//                            it.child(id.toString()).ref.setValue(doctor).addOnSuccessListener {
//                                Log.i("Doctor", "Doctor Added")
//                            }.addOnFailureListener {
//                                Log.i(
//                                    "Doctor",
//                                    "Failed to add Doctor"
//                                )
//                            }
//                        } else {
//
//                            it.child("1").ref.setValue(doctor).addOnSuccessListener {
//                                Log.i("Doctor", "First Doctor Added $doctor")
//                            }.addOnFailureListener {
//                                Log.i(
//                                    "Doctor",
//                                    "Failed to add Doctor"
//                                )
//                            }
//                        }
//                    }.addOnFailureListener {
//                        Log.i(
//                            "Doctor",
//                            "Failed to connect to internet"
//                        )
//                    }
//
//
//            }
//
//
//            Toast.makeText(this.context, "Doctor is added", Toast.LENGTH_LONG).show()
//            findNavController().navigateUp()
//
//        }

    }

}