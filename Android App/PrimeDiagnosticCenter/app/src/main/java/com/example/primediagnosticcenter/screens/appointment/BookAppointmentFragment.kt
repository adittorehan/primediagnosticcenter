package com.example.primediagnosticcenter.screens.appointment

import android.app.DatePickerDialog
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
import com.example.primediagnosticcenter.databinding.FragmentBookAppointmentBinding
import com.example.primediagnosticcenter.network.Api
import com.example.primediagnosticcenter.network.Appointment
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class BookAppointmentFragment : Fragment() {

    private lateinit var binding: FragmentBookAppointmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_book_appointment, container, false)

        // Temp
        val id = requireArguments().get("id")
        Toast.makeText(context, "Doctor ID:$id", Toast.LENGTH_LONG).show()

        val calendar = Calendar.getInstance()
        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            update(calendar)
        }


        binding.appointmentBookDate.setOnClickListener {
            this.context?.let { it1 ->
                DatePickerDialog(
                    it1,
                    datePicker,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()

            }

        }

        binding.makeAppointment.setOnClickListener {
            // Send Post Appointment
            lifecycleScope.launch {
                try {
                    val appointment = Appointment(
                        doctor = requireArguments().get("id") as Int,
                        user_email = binding.appointmentBookEmail.text.toString(),
                        user_phone = binding.appointmentBookPhone.text.toString(),
                        date = binding.appointmentBookDate.text.toString(),
                    )

                    Api.retrofitService.postAppointment(appointment)

                    parentFragment?.view?.let { it1 -> Snackbar.make(it1, "Succesfully placed your request. Please check your email for update.", Snackbar.LENGTH_SHORT).show() }
                    findNavController().navigateUp()
                }
                catch (e: Exception){
                    Toast.makeText(context, "Failed to process your request. Please check your network.", Toast.LENGTH_LONG).show()
                }
            }

        }

        return binding.root
    }

    private fun update(calendar: Calendar) {
        val format = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(format, Locale.US)
        binding.appointmentBookDate.setText(sdf.format(calendar.time))
    }

}