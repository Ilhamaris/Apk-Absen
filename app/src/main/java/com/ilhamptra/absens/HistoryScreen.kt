package com.ilhamptra.absens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HistoryScreen() {
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
}
