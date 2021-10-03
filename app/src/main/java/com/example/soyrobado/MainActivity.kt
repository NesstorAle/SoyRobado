package com.example.soyrobado

import android.R.id
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.davemorrissey.labs.subscaleview.ImageSource
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView
import com.example.soyrobado.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationBarView


class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {
    lateinit var binding: ActivityMainBinding
    lateinit var controller: NavController
    var lastItemSelected = R.id.navigation_home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initController()
        binding.bottomNavigationView.setOnItemSelectedListener(this)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (lastItemSelected != item.itemId) {
            lastItemSelected = item.itemId
            when (item.itemId) {
                R.id.navigation_my_devices -> {
                    controller.navigate(R.id.action_global_myDevicesFragment)
                }
                R.id.navigation_home -> {
                    controller.navigate(R.id.action_global_homeFragment)
                }
            }
        }


        return true
    }

    private fun initController() {
        /*  val navHostFragment =
              supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
          controller = navHostFragment.navController*/

        controller = findNavController(R.id.fragmentContainerView)

        controller.setGraph(R.navigation.main)

        with(binding) {
            NavigationUI.setupWithNavController(bottomNavigationView, controller)

            controller.addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    /*
                    R.id.navigation_my_devices,
                    R.id.navigation_home,-> bottomNavigationView.visible()
                    else -> bottomNavigationView.gone()*/
                }
            }

        }
    }
}