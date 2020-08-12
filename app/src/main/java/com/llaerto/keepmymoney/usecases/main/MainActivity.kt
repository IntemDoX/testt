package com.llaerto.keepmymoney.usecases.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.findNavController
import com.llaerto.keepmymoney.R
import com.llaerto.keepmymoney.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), LifecycleOwner {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setTheme(R.style.AppTheme)
        setContentView(binding.root)
        startNavGraph(R.id.homeFragment)
    }

    private fun startNavGraph(destination: Int) {
        with(findNavController(R.id.nav_host_fragment)) {
            graph = navInflater.inflate(R.navigation.nav_graph).apply {
                startDestination = destination
            }
        }
    }

    override fun onSupportNavigateUp() = findNavController(R.id.nav_host_fragment).navigateUp()
}
