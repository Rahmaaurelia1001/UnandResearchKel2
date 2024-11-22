package com.example.tbptb

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.* // Import Material3
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectCreationScreen(navController: NavController) {
    // States untuk input form
    var projectName by remember { mutableStateOf("") }
    var projectDescription by remember { mutableStateOf("") }
    var collaboratorEmail by remember { mutableStateOf("") }
    var projectDeadline by remember { mutableStateOf("") }
    var proposalUri by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current

    // State untuk kontrol visibilitas DatePicker
    var showDatePicker by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf("") }

    // Kolom untuk menata elemen form secara vertikal
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Judul Form
        androidx.compose.material3.Text(
            text = "Buat Project Penelitian",
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // TextField untuk Nama Project
        Text("Nama Project")
        TextField(
            value = projectName,
            onValueChange = { projectName = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Masukkan nama project") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // TextField untuk Deskripsi Project
        Text("Deskripsi Project")
        TextField(
            value = projectDescription,
            onValueChange = { projectDescription = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            label = { Text("Masukkan deskripsi project") },
            maxLines = 5
        )

        Spacer(modifier = Modifier.height(16.dp))

        // TextField untuk Email Kolaborator
        Text("Email Kolaborator")
        TextField(
            value = collaboratorEmail,
            onValueChange = { collaboratorEmail = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Masukkan email kolaborator") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Project Deadline

        // Tombol untuk upload proposal
        Text("Upload Proposal")
        Button(onClick = {
            // Implementasikan fungsi file picker di sini
            Toast.makeText(context, "Fungsi upload belum diimplementasikan", Toast.LENGTH_SHORT).show()
        }) {
            Text("Pilih Proposal")
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Tombol untuk submit form dan membuat project
        Button(
            onClick = {
                // Simulasikan pengiriman form dan buat project
                Toast.makeText(context, "Project telah dibuat!", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Buat Project")
        }
    }
}


