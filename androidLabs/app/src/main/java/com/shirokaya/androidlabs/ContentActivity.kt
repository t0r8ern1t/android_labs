package com.shirokaya.androidlabs

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.shirokaya.androidlabs.ui.theme.AndroidLabsTheme
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment

class ContentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        val bottomNavView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNavView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment, R.id.registerFragment -> {
                    // скрываем BottomNavigationBar на экранах логина и регистрации
                    bottomNavView.visibility = View.GONE
                }

                else -> {
                    bottomNavView.visibility = View.VISIBLE
                }
            }
        }
    }
}
