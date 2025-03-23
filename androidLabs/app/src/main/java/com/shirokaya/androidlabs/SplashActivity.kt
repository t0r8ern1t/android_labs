package com.shirokaya.androidlabs

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.shirokaya.androidlabs.ui.theme.AndroidLabsTheme

class SplashActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val storage = getSharedPreferences("settings", Context.MODE_PRIVATE)
        val emailPhone = storage.getString("emailPhone", "0")
        val password = storage.getString("password", "0")
        val rememberMe = storage.getBoolean("rememberMe", false)
        if (emailPhone != "0" && password != "0")
        {
            if (rememberMe)
            {
                val intent = Intent(this, ContentActivity::class.java)
                startActivity(intent)
            }
            else
            {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }
        else
        {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
