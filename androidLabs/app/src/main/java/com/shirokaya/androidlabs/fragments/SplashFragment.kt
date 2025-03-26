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

    fun checkOutLoginPage()
    {
        val storage = requireActivity().getSharedPreferences("data", Context.MODE_PRIVATE)
        storage.edit().putBoolean("rememberMe", false).apply()
    }

    // можно вызвать чтобы попасть на страницу регистрации, если она уже пройдена
    fun checkOutRegisterPage()
    {
        checkOutLoginPage()
        val storage = requireActivity().getSharedPreferences("data", Context.MODE_PRIVATE)
        storage.edit().putBoolean("isRegistered", false).apply()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_splash, container, false)

        val navController = NavHostFragment.findNavController(this)
        val storage = requireActivity().getSharedPreferences("data", Context.MODE_PRIVATE)

        if (storage.getBoolean("isRegistered", false)) {
            if (storage.getBoolean("rememberMe", false)) {
                navController.navigate(R.id.oneFragment)
            }
            else {
                navController.navigate(R.id.loginFragment)
            }
        }
        else {
            navController.navigate(R.id.registerFragment)
        }
        return root
    }
}