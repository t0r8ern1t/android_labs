package com.shirokaya.androidlabs.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.shirokaya.androidlabs.R

class SplashFragment : Fragment() {

    fun removeData()
    {
        val storage = requireActivity().getSharedPreferences("settings", Context.MODE_PRIVATE)
        storage.edit().putString("emailPhone", "0").apply()
        storage.edit().putString("password", "0").apply()
        storage.edit().putBoolean("rememberMe", false).apply()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_splash, container, false)

        removeData()

        val navController = NavHostFragment.findNavController(this)
        val storage = requireActivity().getSharedPreferences("settings", Context.MODE_PRIVATE)

        println(storage.getString("emailPhone", "0"))
        println(storage.getString("password", "0"))
        println(storage.getBoolean("rememberMe", false))
        if (storage.getString("emailPhone", "0") != "0" && storage.getString("password", "0") != "0") {
            if (storage.getBoolean("rememberMe", false)) {
                navController.navigate(R.id.oneFragment)
                println("one")
            }
            else {
                navController.navigate(R.id.loginFragment)
                println("login")
            }
        }
        else {
            navController.navigate(R.id.registerFragment)
            println("reg")
        }
        return root
    }
}