package com.shirokaya.androidlabs

import android.content.Intent
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.collection.emptyLongSet
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.shirokaya.androidlabs.ui.theme.AndroidLabsTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val emailPhoneEditText = findViewById<EditText>(R.id.emailPhoneEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val rememberMeCheckbox = findViewById<CheckBox>(R.id.checkBox)
        val loginButton = findViewById<Button>(R.id.loginButton)

        loginButton.setOnClickListener()
        {
            val emailPhone = emailPhoneEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (emailPhone.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
            }
            else
            {
                val storage = getSharedPreferences("settings", Context.MODE_PRIVATE)
                if (storage.getBoolean("rememberMe", false))
                {
                    val intent = Intent(this, ContentActivity::class.java)
                    startActivity(intent)
                }
                else if (storage.getString("emailPhone", null) == emailPhone && storage.getString("password", null) == password)
                {
                    storage.edit().putBoolean("rememberMe", rememberMeCheckbox.isChecked).apply()
                    val intent = Intent(this, ContentActivity::class.java)
                    startActivity(intent)
                }
                else
                {
                    Toast.makeText(this, "Неправильный логин или пароль", Toast.LENGTH_LONG).show()
                }
            }

        }
    }
}