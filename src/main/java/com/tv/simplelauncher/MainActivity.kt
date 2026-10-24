package com.tv.simplelauncher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainLauncherScreen()
        }
    }
}

@Composable
fun MainLauncherScreen() {
    var menuDipilih by remember { mutableStateOf("Aplikasi") }

    Row(modifier = Modifier.fillMaxSize()) {
        
        // 1. SIDEBAR KIRI (Menu Navigasi)
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .width(200.dp)
                .background(Color(0xFF1E1E1E)), // Warna abu-abu gelap
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val listMenu = listOf("Beranda", "Aplikasi", "Pengaturan")
            listMenu.forEach { namaMenu ->
                val isAktif = menuDipilih == namaMenu
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp)
                        .background(if (isAktif) Color(0xFF333333) else Color.Transparent)
                        .clickable { menuDipilih = namaMenu }
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = namaMenu, 
                        color = if (isAktif) Color.White else Color.Gray,
                        fontSize = 18.sp
                    )
                }
            }
        }

        // 2. KONTEN KANAN (Tampilan Aplikasi)
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .background(Color(0xFF121212)) // Background hitam
                .padding(24.dp)
        ) {
            if (menuDipilih == "Aplikasi") {
                DaftarAplikasiDummy()
            } else {
                Text(text = "Halaman $menuDipilih", color = Color.White, fontSize = 24.sp)
            }
        }
    }
}

@Composable
fun DaftarAplikasiDummy() {
    // Kotak aplikasi contoh (dummy grid)
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(12) { indeks ->
            Box(
                modifier = Modifier
                    .aspectRatio(16f/9f)
                    .background(Color(0xFF2D2D2D)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "App ${indeks + 1}", color = Color.White)
            }
        }
    }
}
