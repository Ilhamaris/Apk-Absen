package com.ilhamptra.absens

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext

import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var loginError by remember { mutableStateOf(false) }

    // Ambil context dari LocalContext di dalam composable
    val context = LocalContext.current

    // Fungsi untuk menyimpan status login
    fun saveLoginStatus(context: Context) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("isLoggedIn", true) // Tandai sebagai sudah login
        editor.apply()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF002B7F)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Teks di bagian atas login form
        Text(
            text = "Absensi Mahasiswa\nPoliteknik Negeri Madiun",
            color = Color.White,
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 32.dp),
            lineHeight = 32.sp, // Menambah spasi antara dua baris teks
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )

        // Username TextField
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth(0.8f),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Password TextField
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(
                        imageVector = if (isPasswordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                        contentDescription = "Toggle Password"
                    )
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth(0.8f),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Login Button
        Button(
            onClick = {
                if (username == "Ilham Putra Arysila" && password == "12345678") {
                    // Simpan status login
                    saveLoginStatus(context)
                    onLoginSuccess()
                } else {
                    loginError = true
                }
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("LOGIN")
        }

        // pesan error
        if (loginError) {
            Text(
                text = "Username atau Password salah!",
                color = Color.Red,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}