package com.example.primediagnosticcenter.screens.appointment

import android.graphics.Color
import android.media.Image
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.primediagnosticcenter.R
import com.example.primediagnosticcenter.databinding.FragmentMakeAppointmentBinding
import com.example.primediagnosticcenter.network.Api
import kotlinx.coroutines.launch


class MakeAppointmentFragment : Fragment() {

    private lateinit var binding: FragmentMakeAppointmentBinding

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_make_appointment,
            container,
            false
        )


        binding.searchDoctor.setOnClickListener {
            Toast.makeText(this.context, "Search Doctor", Toast.LENGTH_SHORT).show()
            lifecycleScope.launch {

            }
        }


        val baseid = 90000

        // All Doctors
        lifecycleScope.launch {
            try {
                val listResult = Api.retrofitService.getDoctors()
                for (doctor in listResult) {
                    val doctorView =
                        layoutInflater.inflate(R.layout.doctor_view, binding.doctorList, false)
                    doctorView.id = baseid + doctor.id
                    val imgView = doctorView.findViewById<ImageView>(R.id.appointment_doctor_photo)
                    val imgUri = doctor.photo.toUri().buildUpon().scheme("http").build()
                    Glide.with(imgView.context).load(imgUri).into(imgView)
                    doctorView.findViewById<TextView>(R.id.appointment_doctor_name).text =
                        doctor.name
                    doctorView.findViewById<TextView>(R.id.appointment_doctor_designation).text =
                        doctor.designation
                    doctorView.findViewById<TextView>(R.id.appointment_doctor_department).text =
                        doctor.department
                    binding.doctorList.addView(doctorView)
                    doctorView.setOnClickListener {
                        findNavController().navigate(
                            MakeAppointmentFragmentDirections.actionMakeAppointmentFragmentToBookAppointmentFragment(
                                doctorView.id - baseid
                            )
                        )
                    }
                }
            } catch (e: Exception) {
                val textView = TextView(context)
                textView.setText("Failure: ${e.message}")
                binding.doctorList.addView(textView)
            }

        }

        // Searching Doctors
        binding.appointmentSearchDoctor.setOnClickListener {
            lifecycleScope.launch {
                try {
                    binding.doctorList.removeAllViews()
                    val listResult =
                        Api.retrofitService.getDoctors(binding.searchDoctor.text.toString())
                    for (doctor in listResult) {
                        val doctorView =
                            layoutInflater.inflate(R.layout.doctor_view, binding.doctorList, false)
                        doctorView.id = baseid + doctor.id
                        val imgView =
                            doctorView.findViewById<ImageView>(R.id.appointment_doctor_photo)
                        val imgUri = doctor.photo.toUri().buildUpon().scheme("http").build()
                        Glide.with(imgView.context).load(imgUri).into(imgView)
                        doctorView.findViewById<TextView>(R.id.appointment_doctor_name).text =
                            doctor.name
                        doctorView.findViewById<TextView>(R.id.appointment_doctor_designation).text =
                            doctor.designation
                        doctorView.findViewById<TextView>(R.id.appointment_doctor_department).text =
                            doctor.department
                        binding.doctorList.addView(doctorView)
                        doctorView.setOnClickListener {
                            findNavController().navigate(
                                MakeAppointmentFragmentDirections.actionMakeAppointmentFragmentToBookAppointmentFragment(
                                    doctorView.id - baseid
                                )
                            )
                        }
                    }
                } catch (e: Exception) {
                    val textView = TextView(context)
                    textView.setTextColor(Color.WHITE)
                    textView.setText("No doctor is found")
                    binding.doctorList.addView(textView)
                }

            }

            binding.appointmentSearchDoctor.text = ""
        }



        return binding.root
    }


}