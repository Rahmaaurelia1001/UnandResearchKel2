package com.example.tbptb

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateListOf

@Composable
fun ProjectScreen(navController: NavController, projects: MutableList<Project>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "UnandResearch",
            style = TextStyle(fontSize = 24.sp),
            color = Color.Black
        )
        Text(
            text = "Projects",
            style = TextStyle(fontSize = 32.sp, fontWeight = FontWeight.Bold),
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Menampilkan daftar proyek
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
                        text = project.name,
                        style = TextStyle(fontSize = 20.sp, color = Color.Black)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Kolaborator: ${project.collaborator}",
                        style = TextStyle(fontSize = 14.sp, color = Color.Black)
                    )
                    Text(
                        text = project.description.take(50) + if (project.description.length > 50) "..." else "",
                        style = TextStyle(fontSize = 14.sp, color = Color.Black)
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        // Button untuk menambah proyek
        Button(
            onClick = { navController.navigate("Buat_Project") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Buat Project Baru")
        }
    }
}
