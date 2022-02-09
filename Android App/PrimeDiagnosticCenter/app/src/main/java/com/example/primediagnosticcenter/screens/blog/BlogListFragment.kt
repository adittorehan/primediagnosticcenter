package com.example.primediagnosticcenter.screens.blog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.primediagnosticcenter.R
import com.example.primediagnosticcenter.databinding.FragmentBlogListBinding
import com.example.primediagnosticcenter.network.Api
import com.example.primediagnosticcenter.screens.appointment.MakeAppointmentFragmentDirections
import kotlinx.coroutines.launch


class BlogListFragment : Fragment() {
    private lateinit var binding: FragmentBlogListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_blog_list, container, false)
        // All Blogs
        val baseid = 15000
        lifecycleScope.launch {
            try {
                val listResult = Api.retrofitService.getBlogs()
                for (blog in listResult) {
                    val blogView =
                        layoutInflater.inflate(R.layout.blog_view, binding.blogList, false)
                    blogView.id = baseid + blog.id
                    val imgView = blogView.findViewById<ImageView>(R.id.blog_image)
                    val imgUri = blog.photo.toUri().buildUpon().scheme("http").build()
                    Glide.with(imgView.context).load(imgUri).into(imgView)
                    blogView.findViewById<TextView>(R.id.blog_heading).text =
                        blog.heading

                    binding.blogList.addView(blogView)
                    blogView.setOnClickListener {
                        findNavController().navigate(
                            BlogListFragmentDirections.actionBlogListFragmentToBlogFragment(
                                blogView.id - baseid
                            )
                        )
                    }
                }
            } catch (e: Exception) {
                val textView = TextView(context)
                textView.setText("Failure: ${e.message}")
                binding.blogList.addView(textView)
            }

        }
        return binding.root
    }


}