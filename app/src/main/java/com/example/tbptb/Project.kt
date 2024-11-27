package com.example.tbptb

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ProjectScreen(navController: NavController) {
    val projects = listOf(
        "Sistem Informasi Pengelolaan Keberlanjutan",
        "Analisis Dampak Perubahan Iklim",
        "Pengaruh Penerapan Teknologi Digital",
        "Peran Sosial Media dalam Pengembangan",
        "Evaluasi Kebijakan Pengelolaan Sampah",
        "Studi Pemanfaatan Energi Terbarukan"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "UnandResearch",
                style = TextStyle(fontSize = 24.sp),
                color = Color.Black
            )
            Text(
                text = "Projects",
                style = TextStyle(fontSize = 32.sp),
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Project List
        projects.forEachIndexed { index, project ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        if (index % 2 == 0) Color(0xFFDFFBE9) else Color(0xFF5AB19F),
                        RoundedCornerShape(12.dp)
                    )
                    .padding(16.dp)
            ) {
                Column {
                    Text(
                        text = project,
                        style = TextStyle(fontSize = 20.sp, color = Color.Black)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Monitoring growth of corn with new pesticide",
                        style = TextStyle(fontSize = 14.sp, color = Color.Black)
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
