package com.ilhamptra.absens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(3000) // Tunda 3 detik
        onTimeout()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF002B7F)),
        verticalArrangement = Arrangement.Center, // Agar konten ditata secara vertikal di tengah
        horizontalAlignment = Alignment.CenterHorizontally // Agar konten ditata secara horizontal di tengah
    ) {
        Image(
            painter = painterResource(R.drawable.logo), // Ganti dengan logo Anda
            contentDescription = "Logo"
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Absensi Mahasiswa\nPoliteknik Negeri Madiun",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = Center
        )
    }
}