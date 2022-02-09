package com.example.primediagnosticcenter.screens.ordermedicine

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.primediagnosticcenter.R
import com.example.primediagnosticcenter.database.MyDatabase
import com.example.primediagnosticcenter.database.MyDatabaseDao
import com.example.primediagnosticcenter.database.TempOrder
import com.example.primediagnosticcenter.databinding.FragmentOrderMedicineBinding
import com.example.primediagnosticcenter.network.Api
import com.example.primediagnosticcenter.screens.appointment.MakeAppointmentFragmentDirections
import kotlinx.coroutines.launch


class OrderMedicineFragment : Fragment() {

    private lateinit var binding: FragmentOrderMedicineBinding


    private var totalAmount = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_order_medicine,
            container,
            false
        )

        val orderList = ArrayList<MedicineOrder>()
        val baseid = 190000
        lifecycleScope.launch {
            try {
                val listResult = Api.retrofitService.getMedicines()
                for (medicine in listResult) {
                    val medicineView =
                        layoutInflater.inflate(R.layout.medicine_view, binding.medicineList, false)
                    medicineView.id = baseid + medicine.id
                    val imgView = medicineView.findViewById<ImageView>(R.id.medicine_photo)
                    val imgUri = medicine.photo.toUri().buildUpon().scheme("http").build()
                    Glide.with(imgView.context).load(imgUri).into(imgView)
                    medicineView.findViewById<TextView>(R.id.medicine_name_textView).text =
                        medicine.name
                    medicineView.findViewById<TextView>(R.id.medicine_detail_textView).text =
                        medicine.detail
                    medicineView.findViewById<TextView>(R.id.medicine_price_textView).text =
                        medicine.price.toString()
                    val quantityView =
                        medicineView.findViewById<TextView>(R.id.medicine_quantity)
                    quantityView.text = "1"
                    val addToCartButton =
                        medicineView.findViewById<Button>(R.id.add_to_cart_button)
                    val removeFromCartButton =
                        medicineView.findViewById<Button>(R.id.remove_from_cart_button)

                    addToCartButton.setOnClickListener {
                        // TODO add to cart
                        addToCartButton.visibility = View.GONE
                        removeFromCartButton.visibility = View.VISIBLE
                        // TODO update total amount
                        val quantity = quantityView.text.toString().toInt()
//                        cart.add(Pair(medicine.id, quantity))
                        orderList.add(MedicineOrder(medicine.id, quantity))
                        totalAmount += medicine.price * quantity
                        binding.totalAmount.text = "Total Amount: ${totalAmount}tk"
                        quantityView.isEnabled = false


                    }
                    removeFromCartButton.setOnClickListener {
                        removeFromCartButton.visibility = View.GONE
                        addToCartButton.visibility = View.VISIBLE
                        // TODO update total amount
                        val quantity = quantityView.text.toString().toInt()
                        orderList.removeAt(orderList.size - 1)
                        totalAmount -= medicine.price * quantity
                        binding.totalAmount.text = "Total Amount: ${totalAmount}tk"
                        quantityView.isEnabled = true

                    }
                    binding.medicineList.addView(medicineView)

                }
            } catch (e: Exception) {
                val textView = TextView(context)
                textView.setText("Failure: ${e.message}")
                binding.medicineList.addView(textView)
            }
        }


        binding.medicineSearchButton.setOnClickListener {
            val nameLike = binding.searchMedicineTextView.text.toString()
            lifecycleScope.launch {

                try {
                    binding.medicineList.removeAllViews()
                    val listResult =
                        Api.retrofitService.getMedicines(nameLike)

                    for (medicine in listResult) {
                        val medicineView =
                            layoutInflater.inflate(
                                R.layout.medicine_view,
                                binding.medicineList,
                                false
                            )
                        medicineView.id = baseid + medicine.id
                        val imgView = medicineView.findViewById<ImageView>(R.id.medicine_photo)
                        val imgUri = medicine.photo.toUri().buildUpon().scheme("http").build()
                        Glide.with(imgView.context).load(imgUri).into(imgView)
                        medicineView.findViewById<TextView>(R.id.medicine_name_textView).text =
                            medicine.name
                        medicineView.findViewById<TextView>(R.id.medicine_detail_textView).text =
                            medicine.detail
                        medicineView.findViewById<TextView>(R.id.medicine_price_textView).text =
                            medicine.price.toString()
                        val quantityView =
                            medicineView.findViewById<TextView>(R.id.medicine_quantity)
                        quantityView.text ="1"
                        val addToCartButton =
                            medicineView.findViewById<Button>(R.id.add_to_cart_button)
                        val removeFromCartButton =
                            medicineView.findViewById<Button>(R.id.remove_from_cart_button)

                        addToCartButton.setOnClickListener {
                            // TODO add to cart
                            addToCartButton.visibility = View.GONE
                            removeFromCartButton.visibility = View.VISIBLE
                            // TODO update total amount
                            val quantity = quantityView.text.toString().toInt()
                            orderList.add(MedicineOrder(medicine.id, quantity))
                            totalAmount += medicine.price * quantity
                            binding.totalAmount.text = "Total Amount: ${totalAmount}tk"
                            quantityView.isEnabled = false


                        }
                        removeFromCartButton.setOnClickListener {
                            removeFromCartButton.visibility = View.GONE
                            addToCartButton.visibility = View.VISIBLE
                            // TODO update total amount
                            val quantity = quantityView.text.toString().toInt()
                            orderList.removeAt(orderList.size - 1)
                            totalAmount -= medicine.price * quantity
                            binding.totalAmount.text = "Total Amount: ${totalAmount}tk"
                            quantityView.isEnabled = true

                        }
                        binding.medicineList.addView(medicineView)

                    }
                } catch (e: Exception) {
                    val textView = TextView(context)
                    textView.setTextColor(Color.WHITE)
                    textView.setText("No medicine is found")
                    binding.medicineList.addView(textView)
                }

            }
        }

        binding.medicineOrderButton.setOnClickListener {
            findNavController().navigate(
                OrderMedicineFragmentDirections.actionOrderMedicineFragmentToGetOrderMedicineAddressFragment(
                    orderList.toTypedArray()
                )
            )

        }

        return binding.root
    }

}