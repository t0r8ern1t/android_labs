package com.shirokaya.androidlabs.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import com.google.firebase.auth.FirebaseAuth
import com.shirokaya.androidlabs.ContentActivity
import com.shirokaya.androidlabs.R

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_login, container, false)
        var navController = NavHostFragment.findNavController(this)

        val emailPhoneEditText = root.findViewById<EditText>(R.id.emailPhoneEditText)
        val passwordEditText = root.findViewById<EditText>(R.id.passwordEditText)
        val rememberMeCheckbox = root.findViewById<CheckBox>(R.id.checkBox)
        val loginButton = root.findViewById<Button>(R.id.loginButton)

        loginButton.setOnClickListener()
        {
            val emailPhone = emailPhoneEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            val auth = FirebaseAuth.getInstance()

            val storage = requireActivity().getSharedPreferences("data", Context.MODE_PRIVATE)
            if (storage.getBoolean("rememberMe", false)) {
                navController.navigate(R.id.oneFragment)
            }
            auth.signInWithEmailAndPassword(emailPhone, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful)
                    {
                        storage.edit().putBoolean("rememberMe", rememberMeCheckbox.isChecked).apply()
                        navController.navigate(R.id.oneFragment)
                    }
                }.addOnFailureListener { exception ->
                    Toast.makeText(requireContext(), exception.localizedMessage, Toast.LENGTH_LONG).show()
                }
        }
        return root
    }
}