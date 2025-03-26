package com.shirokaya.androidlabs.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.shirokaya.androidlabs.ContentActivity
import com.shirokaya.androidlabs.R

class RegisterFragment : Fragment() {

    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_register, container, false)
        val navController = NavHostFragment.findNavController(this)

        val byMailTextView = root.findViewById<TextView>(R.id.byEmailTitle)
        val byPhoneTextView = root.findViewById<TextView>(R.id.byPhoneTitle)
        val emailPhoneEditText = root.findViewById<EditText>(R.id.emailPhoneEditText)
        val registerButton = root.findViewById<Button>(R.id.registerButton)
        val passwordEditText = root.findViewById<EditText>(R.id.passwordEditText)
        val repeatPasswordEditText = root.findViewById<EditText>(R.id.repeatPasswordEditText)
        var isEmail = true

        byMailTextView.setOnClickListener()
        {
            /*byMailTextView.setTextColor(R.color.white)
            byPhoneTextView.setTextColor(R.color.black)*/
            emailPhoneEditText.hint = "Электронная почта"
            emailPhoneEditText.inputType = InputType.TYPE_CLASS_TEXT
            isEmail = true
        }

        /*byPhoneTextView.setOnClickListener()
        {
            byMailTextView.setTextColor(R.color.black)
            byPhoneTextView.setTextColor(R.color.white)
            emailPhoneEditText.hint = "Номер телефона"
            emailPhoneEditText.inputType = InputType.TYPE_CLASS_PHONE
            isEmail = false
        }*/

        registerButton.setOnClickListener()
        {
            var isRegistratonCorrect = true
            if (isEmail)
            {
                if (!emailPhoneEditText.text.toString().contains("@"))
                {
                    Toast.makeText(requireContext(), "Некорректный email", Toast.LENGTH_LONG).show()
                    isRegistratonCorrect = false
                }
            }
            else
            {
                if (!emailPhoneEditText.text.toString().contains("+"))
                {
                    Toast.makeText(requireContext(), "Некорректный номер телефона", Toast.LENGTH_LONG).show()
                    isRegistratonCorrect = false
                }
            }
            if (passwordEditText.text.toString() == repeatPasswordEditText.text.toString())
            {
                if (passwordEditText.text.toString().length < 6)
                {
                    Toast.makeText(requireContext(), "Пароль должен содержать не менее 6 символов", Toast.LENGTH_LONG).show()
                    isRegistratonCorrect = false
                }
            }
            else
            {
                Toast.makeText(requireContext(), "Пароли не совпадают", Toast.LENGTH_LONG).show()
                isRegistratonCorrect = false
            }

            if (isRegistratonCorrect && isEmail)
            {
                val storage = requireActivity().getSharedPreferences("data", Context.MODE_PRIVATE)
                storage.edit().putBoolean("isRegistered", true).apply()
                val auth = FirebaseAuth.getInstance()
                auth.createUserWithEmailAndPassword(emailPhoneEditText.text.toString(), passwordEditText.text.toString())
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful)
                        {
                            navController.navigate(R.id.oneFragment)
                        }
                    }.addOnFailureListener { exception ->
                        Toast.makeText(requireContext(), exception.localizedMessage, Toast.LENGTH_LONG).show()
                    }
            }
        }

        return root
    }
}