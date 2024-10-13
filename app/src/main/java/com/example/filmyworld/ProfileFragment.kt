package com.example.filmyworld

import ImageSliderAdapter
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView // Add this import
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.filmyworld.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ProfileFragment : Fragment() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var slideInfoTextView: TextView
    private var totalItems = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        // Initialize ViewPager2, TabLayout, and TextView
        viewPager = view.findViewById(R.id.viewPager)
        tabLayout = view.findViewById(R.id.tabLayout)
        slideInfoTextView = view.findViewById(R.id.slideInfoTextView)

        // Set up ViewPager2 with images
        val images = listOf(R.drawable.hodnf, R.drawable.gg, R.drawable.shekharh) // Replace with your images
        viewPager.adapter = ImageSliderAdapter(images)

        // Link TabLayout with ViewPager2
        TabLayoutMediator(tabLayout, viewPager) { _, _ -> }.attach()

        // Get the total number of pages
        viewPager.adapter?.let { adapter ->
            totalItems = adapter.itemCount
        }

        // Add PageChangeCallback
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                // Calculate number of images left
                val imagesLeft = totalItems - position - 1
                // Update TextView with current slide position and number of images left
                updateSlideInfo(position + 1, totalItems, imagesLeft)
            }
        })

        return view
    }

    private fun updateSlideInfo(currentSlide: Int, totalSlides: Int, imagesLeft: Int) {
        slideInfoTextView.text = "Slide $currentSlide of $totalSlides | Images left: $imagesLeft"
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
