package com.ilhamptra.absens

import android.content.Intent
import android.provider.MediaStore
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Icon
import androidx.compose.ui.platform.LocalContext

@Composable
fun HistoryScreen() {
    val context = LocalContext.current

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
                items(10) { index ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        backgroundColor = MaterialTheme.colorScheme.secondary
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Berhasil melakukan absen")
                            Text("Waktu Hadir: 07:34:29")
                        }
                    }
                }
            }
        }

        // Camera icon at the bottom center
        Icon(
            painter = painterResource(id = android.R.drawable.ic_menu_camera), // Use the default camera icon or replace it with your own
            contentDescription = "Camera Icon",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
                .size(48.dp)
                .clickable {
                    // Open the camera when clicked
                    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    context.startActivity(intent)
                },
            tint = Color.Black // You can change the icon color here
        )
    }
}