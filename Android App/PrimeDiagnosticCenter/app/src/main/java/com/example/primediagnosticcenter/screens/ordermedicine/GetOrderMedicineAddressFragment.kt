package com.example.primediagnosticcenter.screens.ordermedicine

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.primediagnosticcenter.MyDialogFragment
import com.example.primediagnosticcenter.MyDialogFragmentDirections
import com.example.primediagnosticcenter.R
import com.example.primediagnosticcenter.database.MyDatabase
import com.example.primediagnosticcenter.database.Orders
import com.example.primediagnosticcenter.database.TempOrder
import com.example.primediagnosticcenter.databinding.FragmentGetOrderMedicineAddressBinding
import com.example.primediagnosticcenter.network.Api
import com.example.primediagnosticcenter.network.ApiService
import com.example.primediagnosticcenter.network.Order
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class GetOrderMedicineAddressFragment : Fragment() {
    private lateinit var binding: FragmentGetOrderMedicineAddressBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_get_order_medicine_address,
            container,
            false
        )
        val medicineOrderList = arguments?.getParcelableArray("orderlist")!!


        binding.proceed.setOnClickListener {
            lifecycleScope.launch {
                findNavController().navigate(
                    GetOrderMedicineAddressFragmentDirections.actionGetOrderMedicineAddressFragmentToMyDialogFragment()
                )
            }
            lifecycleScope.launch {
                // Getting Order Object
                try {

                    val order = Api.retrofitService.postOrder(
                        Order(
                            id = null,
                            email = binding.shippingEmail.text.toString(),
                            address = binding.shippingAddress.text.toString(),
                            phone = binding.shippingPhone.text.toString(),
                            status = 0
                        )
                    )
                    println(order.id)
//                 Adding Medicines to the order
                    order.let {
                        for (item in medicineOrderList) {
                            val medicineOrder = item as MedicineOrder
                            val temp = com.example.primediagnosticcenter.network.MedicineOrder(
                                medicineOrder.id,
                                order.id ?: 1,
                                medicineOrder.quantity
                            )
                            println(temp)
                            val t = Api.retrofitService.postMedicineOrder(temp)
                            println(t)
                        }
                        (activity?.supportFragmentManager?.findFragmentByTag("com.example.primediagnosticcenter.MyDialogFragment") as? DialogFragment)?.dismiss()
                        Toast.makeText(context, "Successfully placed order.", Toast.LENGTH_LONG)
                            .show()
                        findNavController().navigate(MyDialogFragmentDirections.actionMyDialogFragmentToHomeFragment())
                    }
                }
                catch(e: Exception){
                    Toast.makeText(context, "Failed to placed order.", Toast.LENGTH_LONG)
                        .show()
                    findNavController().navigate(MyDialogFragmentDirections.actionMyDialogFragmentToHomeFragment())
                }
            }

        }

        return binding.root
    }


}