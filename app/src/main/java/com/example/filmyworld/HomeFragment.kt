package com.example.filmyworld

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.example.filmyworld.R


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.window?.statusBarColor = ContextCompat.getColor(requireContext(), R.color.liteblack)

        // Find the ImageView by its ID
        val kalkiImageView = view.findViewById<ImageView>(R.id.kalki) // Replace with the actual ID of your image

        // Set an OnClickListener on the ImageView
        kalkiImageView.setOnClickListener {
            // Manually replace the fragment when the image is clicked
            (activity as MainActivity).replaceFragment(kalkiDetailFragment())
        }
        val shekharhImageView = view.findViewById<ImageView>(R.id.shekharh) // Replace with the actual ID of your image

        // Set an OnClickListener on the ImageView
        shekharhImageView.setOnClickListener {
            // Manually replace the fragment when the image is clicked
            (activity as MainActivity).replaceFragment(ShekharhDetailFragment())
        }
    }
}
