package com.example.filmyworld

import android.annotation.SuppressLint

import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.filmyworld.R

class kalkiDetailFragment : Fragment() {

    private lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_kalki_detail, container, false)

        webView = view.findViewById(R.id.trailerWebView)

        webView.settings.javaScriptEnabled = true
        webView.webChromeClient = object : WebChromeClient() {
            override fun onShowCustomView(view: View?, callback: CustomViewCallback?) {
                // Switch to landscape mode
                activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                super.onShowCustomView(view, callback)
            }

            override fun onHideCustomView() {
                // Switch back to portrait mode
                activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                super.onHideCustomView()
            }
        }
        webView.webViewClient = WebViewClient()

        // Set click listener on the Play Trailer button
        view.findViewById<View>(R.id.playTrailerButton).setOnClickListener {
            webView.visibility = View.VISIBLE
            webView.loadUrl("https://www.youtube.com/embed/aninoDcPWo4?autoplay=1&modestbranding=1&showinfo=0&controls=1")
        }
        // Set click listener on the Download button
        view.findViewById<View>(R.id.downloadButton).setOnClickListener {
            val downloadIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://clouda.filesdl.in/dl/BxroEUaB7pXKq2m"))
            startActivity(downloadIntent)
        }

        // Set click listener on the Open OTT button
        view.findViewById<View>(R.id.ottLinkButton).setOnClickListener {
            val ottIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.netflix.com/title/81726031"))
            startActivity(ottIntent)
        }

        // Handle back button presses
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (webView.canGoBack()) {
                    webView.goBack() // Navigate back in WebView history
                } else {
                    // If the WebView can't go back, navigate back to the previous fragment
                    parentFragmentManager.popBackStack()
                }
            }
        })

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Revert to portrait mode when leaving the fragment
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }
}
