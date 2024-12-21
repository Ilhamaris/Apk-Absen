package com.ilhamptra.absens

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HistoryScreen() {
    val historyList = remember { mutableStateListOf<HistoryItem>() }
    var showDialog by remember { mutableStateOf(false) }
    var selectedPhoto by remember { mutableStateOf<Bitmap?>(null) }

    fun getCurrentDateTime(): String {
        val dateTimeFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        return dateTimeFormat.format(Date())
    }

    fun getCurrentTime(): String {
        val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        return timeFormat.format(Date())
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK && result.data != null) {
            val photoBitmap = result.data?.extras?.get("data") as? Bitmap
            if (photoBitmap != null) {
                val currentDate = getCurrentDateTime()
                val currentTime = getCurrentTime()
                historyList.add(HistoryItem(currentDate, currentTime, photoBitmap))
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Bagian atas dengan latar belakang biru penuh
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Blue)
                .padding(vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Ilham Putra Arysila",
                    color = Color.White,
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = "NIM: 2333007051",
                    color = Color.White,
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "Absensi Anda",
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Daftar absensi
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
        ) {
            items(historyList) { historyItem ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            selectedPhoto = historyItem.photo
                            showDialog = true
                        },
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF5F5F5) // Abu-abu terang
                    )
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(historyItem.date)
                        Text(
                            text = "Berhasil melakukan absen",
                            color = Color.Green,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                        Text("Waktu Hadir: ${historyItem.time}")
                    }
                }
            }
        }

        // Bagian bawah dengan lingkaran untuk ikon kamera
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Blue)
                .padding(vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .background(Color.White, shape = CircleShape)
                    .clickable {
                        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                        launcher.launch(intent)
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = android.R.drawable.ic_menu_camera),
                    contentDescription = "Camera Icon",
                    tint = Color.Blue,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }

    if (showDialog && selectedPhoto != null) {
        Dialog(onDismissRequest = { showDialog = false }) {
            Surface(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Image(
                        bitmap = selectedPhoto!!.asImageBitmap(),
                        contentDescription = "Taken photo",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .padding(8.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { showDialog = false },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(text = "Close")
                    }
                }
            }
        }
    }
}

data class HistoryItem(
    val date: String,
    val time: String,
    val photo: Bitmap? // Store the photo bitmap
)

@Preview
@Composable
fun PreviewHistoryScreen() {
    HistoryScreen()
}
