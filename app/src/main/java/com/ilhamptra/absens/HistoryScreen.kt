package com.ilhamptra.absens

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HistoryScreen() {
    val historyList = remember { mutableStateListOf<Pair<String, String>>() } // List to store history

    fun getCurrentTime(): String {
        val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        return timeFormat.format(Date())
    }

    var photoTaken by remember { mutableStateOf(false) } // Track if a photo was successfully taken

    // Launcher to handle result from camera intent
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK && result.data != null) {
            // Handle the result from the camera
            photoTaken = true
            val currentTime = getCurrentTime()
            historyList.add("Berhasil melakukan absen" to currentTime)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Ilham Putra Arysila",
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = "NIM: 2333007051",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(historyList) { history ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.secondary
                        )
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(history.first)
                            Text("Waktu Hadir: ${history.second}")
                        }
                    }
                }
            }
        }

        // Camera icon at the bottom center
        Icon(
            painter = painterResource(id = android.R.drawable.ic_menu_camera),
            contentDescription = "Camera Icon",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
                .size(48.dp)
                .clickable {
                    // Open the camera when clicked
                    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    launcher.launch(intent)
                },
            tint = Color.Black
        )
    }
}
