package com.shirokaya.androidlabs

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity

class RegisterActivity : ComponentActivity() {
    @SuppressLint("ResourceAsColor")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val byMailTextView = findViewById<TextView>(R.id.byEmailTitle)
        val byPhoneTextView = findViewById<TextView>(R.id.byPhoneTitle)
        val emailPhoneEditText = findViewById<EditText>(R.id.emailPhoneEditText)
        val registerButton = findViewById<Button>(R.id.registerButton)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val repeatPasswordEditText = findViewById<EditText>(R.id.repeatPasswordEditText)
        var isEmail = true

        byMailTextView.setOnClickListener()
        {
            byMailTextView.setTextColor(R.color.white)
            byPhoneTextView.setTextColor(R.color.black)
            emailPhoneEditText.hint = "Email"
            emailPhoneEditText.inputType = InputType.TYPE_CLASS_TEXT
            isEmail = true
        }

        byPhoneTextView.setOnClickListener()
        {
            byMailTextView.setTextColor(R.color.black)
            byPhoneTextView.setTextColor(R.color.white)
            emailPhoneEditText.hint = "Номер телефона"
            emailPhoneEditText.inputType = InputType.TYPE_CLASS_PHONE
            isEmail = false
        }

        registerButton.setOnClickListener()
        {
            var isRegistratonCorrect = true
            if (isEmail)
            {
                if (!emailPhoneEditText.text.toString().contains("@"))
                {
                    Toast.makeText(this, "Некорректный email", Toast.LENGTH_LONG).show()
                    isRegistratonCorrect = false
                }
            }
            else
            {
                if (!emailPhoneEditText.text.toString().contains("+"))
                {
                    Toast.makeText(this, "Некорректный номер телефона", Toast.LENGTH_LONG).show()
                    isRegistratonCorrect = false
                }
            }
            if (passwordEditText.text.toString() == repeatPasswordEditText.text.toString())
            {
                if (passwordEditText.text.toString().length < 6)
                {
                    Toast.makeText(this, "Пароль должен содержать не менее 6 символов", Toast.LENGTH_LONG).show()
                    isRegistratonCorrect = false
                }
            }
            else
            {
                Toast.makeText(this, "Пароли не совпадают", Toast.LENGTH_LONG).show()
                isRegistratonCorrect = false
            }

            if (isRegistratonCorrect)
            {
                val storage = getSharedPreferences("settings", Context.MODE_PRIVATE)
                storage.edit().putString("emailPhone", emailPhoneEditText.text.toString()).apply()
                storage.edit().putString("password", passwordEditText.text.toString()).apply()

                val intent = Intent(this, ContentActivity::class.java)
                startActivity(intent)
            }
        }
    }
}