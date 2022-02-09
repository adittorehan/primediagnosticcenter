package com.example.primediagnosticcenter.screens.homepage

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.primediagnosticcenter.R
import com.example.primediagnosticcenter.databinding.FragmentHomeBinding
import com.smarteist.autoimageslider.SliderView


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )
        val images = arrayListOf(R.drawable.slider_1, R.drawable.slider_2, R.drawable.slider_3)
        val headers =
            arrayListOf(R.string.headers_1, R.string.headers_2, R.string.headers_3)
        val subHeaders =
            arrayListOf(R.string.subheaders_1, R.string.subheaders_2, R.string.subheaders_3)
        binding.imageSlider.setSliderAdapter(
            DoctorSliderAdapter(
                images.toIntArray(),
                headers.toIntArray(),
                subHeaders.toIntArray()
            )
        )
        binding.imageSlider.startAutoCycle()

        val doctorImages =
            arrayListOf(R.drawable.doctor_1, R.drawable.doctor_2, R.drawable.doctor_3)
        val doctorNames =
            arrayListOf(R.string.doctor_name_1, R.string.doctor_name_2, R.string.doctor_name_3)
        val doctorDetails =
            arrayListOf(
                R.string.doctor_detail_1,
                R.string.doctor_detail_2,
                R.string.doctor_detail_3
            )
        binding.doctorImageSlider.setSliderAdapter(
            DoctorSliderAdapter(
                doctorImages.toIntArray(),
                doctorNames.toIntArray(),
                doctorDetails.toIntArray()
            )
        )
        binding.doctorImageSlider.startAutoCycle()
        setHasOptionsMenu(true)

        binding.contactUsButton.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToContactFragment())
        }

        binding.fabWhatsapp.setOnClickListener {
            val wpurl = "https://wa.me/+8801892445308?text=Is there anyone available?"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(wpurl)
            startActivity(intent)
        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.navdrawer_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

}