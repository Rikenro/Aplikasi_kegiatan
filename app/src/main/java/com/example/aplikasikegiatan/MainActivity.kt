package com.example.aplikasikegiatan


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color


data class Artwork(
    val title: String,
    val author: String,
    val year: String,
    val imageRes: Int
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Kegiatan()
        }
    }
}

@Composable
fun Kegiatan() {
    val artworks = listOf(
        Artwork("Kegiatan1", "Mengerjakan tugas ", "", R.drawable.kegiatan1),
        Artwork("Kegiatan2", "Mengikuti milad TIF 2024", "", R.drawable.kegiatan2),
        Artwork("Kegiatan3", "Makan bersama", "", R.drawable.kegiatan3),
        Artwork("Kegiatan4", "Mengikuti kelas online", "", R.drawable.kegiatan4),
        Artwork("Kegiatan5", "shalat jum'at", "", R.drawable.kegiatan5)

    )

    var currentIndex by remember { mutableStateOf(0) }

    val swipeGesture = Modifier.pointerInput(Unit) {
        detectHorizontalDragGestures { change, dragAmount ->
            change.consume()
            if (dragAmount < -50 && currentIndex < artworks.size - 1) {
                currentIndex++
            } else if (dragAmount > 50 && currentIndex > 0) {
                currentIndex--
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize().then(swipeGesture)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = artworks[currentIndex].imageRes),
                contentDescription = "Artwork",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .padding(18.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = artworks[currentIndex].title,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "${artworks[currentIndex].author}",
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "${artworks[currentIndex].year}",
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    )

                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { if (currentIndex > 0) currentIndex-- }) {
                Text("Previous")
            }
            Button(onClick = { if (currentIndex < artworks.size - 1) currentIndex++ }) {
                Text("Next")
            }
        }
    }
}

