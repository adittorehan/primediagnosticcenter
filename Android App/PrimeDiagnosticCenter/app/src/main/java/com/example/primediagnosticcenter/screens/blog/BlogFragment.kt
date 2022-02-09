package com.example.primediagnosticcenter.screens.blog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.primediagnosticcenter.R
import com.example.primediagnosticcenter.databinding.FragmentBlogBinding
import com.example.primediagnosticcenter.network.Api
import com.example.primediagnosticcenter.network.ApiService
import kotlinx.coroutines.launch


class BlogFragment : Fragment() {

    private lateinit var binding: FragmentBlogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_blog, container, false)
        val blogId = requireArguments().get("id")
        lifecycleScope.launch {
            val blog = Api.retrofitService.getBlog(blogId as Int)
            binding.blogHeadingText.text = blog.heading
            val imgUri = blog.photo.toUri().buildUpon().scheme("http").build()
            Glide.with(binding.blogImageview.context).load(imgUri).into(binding.blogImageview)
            binding.blogBodyText.text = blog.body
        }

        return binding.root
    }


}