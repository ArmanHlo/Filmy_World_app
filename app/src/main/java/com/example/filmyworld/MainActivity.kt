package com.example.filmyworld


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.filmyworld.R
import com.example.filmyworld.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.appcheck.FirebaseAppCheck
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        val firebaseAppCheck = FirebaseAppCheck.getInstance()
        firebaseAppCheck.installAppCheckProviderFactory(
            PlayIntegrityAppCheckProviderFactory.getInstance()
        )
        window.navigationBarColor = ContextCompat.getColor(this, R.color.liteblack)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bnv.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.Home -> {
                    replaceFragment(HomeFragment())
                }
                R.id.Movies -> {
                    replaceFragment(SettingFragment())
                }
                R.id.Series -> {
                    replaceFragment(ProfileFragment())
                }
                R.id.kalki -> {
                    replaceFragment(kalkiDetailFragment())
                }
                else -> {}
            }
            return@setOnItemSelectedListener true
        }

        // Load the HomeFragment by default when the activity starts
        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
        }
    }

    fun replaceFragment(fg: Fragment) {
        val fm = supportFragmentManager
        val fTransaction = fm.beginTransaction()
        fTransaction.replace(R.id.frame_layout, fg)
        fTransaction.addToBackStack(null) // Add the transaction to the back stack
        fTransaction.commit()
    }
}
