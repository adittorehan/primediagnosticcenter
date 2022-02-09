package com.example.primediagnosticcenter.screens.admin

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.primediagnosticcenter.R
import com.example.primediagnosticcenter.databinding.FragmentManageAppointmentsBinding
import com.example.primediagnosticcenter.network.Api
import com.example.primediagnosticcenter.screens.appointment.MakeAppointmentFragmentDirections
import kotlinx.coroutines.launch


class ManageAppointmentsFragment : Fragment() {

    private lateinit var binding: FragmentManageAppointmentsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_manage_appointments,
            container,
            false
        )


        lifecycleScope.launch {
            val baseid = 13000
            try {
                binding.appointmentsList.removeAllViews()

                val listResult =
                    Api.retrofitService.getAppointments()
                for (appointment in listResult) {
                    val appointmentView =
                        layoutInflater.inflate(
                            R.layout.appointments_view,
                            binding.appointmentsList,
                            false
                        )


                    appointmentView.id = baseid + appointment.id

                    appointmentView.findViewById<TextView>(R.id.appointment_manage_id).text =
                        "Appointment No. ${appointment.id}"
                    appointmentView.findViewById<TextView>(R.id.appointment_manage_doctorname).text =
                        "Doctor ID: ${appointment.doctor}"
                    appointmentView.findViewById<TextView>(R.id.appointment_manage_useremail).text =
                        "User email: ${appointment.user_email}"
                    appointmentView.findViewById<TextView>(R.id.appointment_manage_phone).text =
                        "User Phone: ${appointment.user_phone}"
                    appointmentView.findViewById<TextView>(R.id.appointment_manage_date).text =
                        "Date: ${appointment.date}"

                    if (appointment.status == 1) {
                        appointmentView.setBackgroundColor(Color.RED)

                    }
                    if (appointment.status == 2) {
                        appointmentView.setBackgroundColor(Color.GREEN)

                    }

                    if (appointment.status != 0) {
                        appointmentView.findViewById<Button>(R.id.appointments_manage_accept).visibility =
                            View.GONE
                        appointmentView.findViewById<Button>(R.id.appointments_manage_reject).visibility =
                            View.GONE
                    } else {
                        appointmentView.findViewById<Button>(R.id.appointments_manage_accept)
                            .setOnClickListener {
                                lifecycleScope.launch {
                                    try {
                                        appointment.status = 2
                                        Api.retrofitService.putAppointment(
                                            appointment.id,
                                            appointment
                                        )

                                        Toast.makeText(
                                            context,
                                            "Updated Successfully",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        onStart()
                                    } catch (e: Exception) {
                                        Toast.makeText(
                                            context,
                                            "Failed to update appointment",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }

                                }

                            }
                        appointmentView.findViewById<Button>(R.id.appointments_manage_reject)
                            .setOnClickListener {  lifecycleScope.launch {
                                try {
                                    appointment.status = 1
                                    Api.retrofitService.putAppointment(
                                        appointment.id,
                                        appointment
                                    )

                                    Toast.makeText(
                                        context,
                                        "Updated Successfully",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    onStart()
                                } catch (e: Exception) {
                                    Toast.makeText(
                                        context,
                                        "Failed to update appointment",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                            } }
                    }
                    println(appointment)
                    binding.appointmentsList.addView(appointmentView)

                }
            } catch (e: Exception) {
                val textView = TextView(context)
                textView.setTextColor(Color.WHITE)
                textView.setText("No appointment is found")
                binding.appointmentsList.addView(textView)
            }
        }

        return binding.root
    }


}