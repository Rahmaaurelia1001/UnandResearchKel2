package com.example.tbptb

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.unit.Dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(navController: NavController) {
    var taskName by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }
    var taskDeadlineDate by remember { mutableStateOf<Date?>(null) }
    var taskDeadlineTime by remember { mutableStateOf<String>("00:00") }
    var taskAssignee by remember { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tambahkan Task") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFFF5F5F5))
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .padding(16.dp)
                    .background(Color.White, RoundedCornerShape(16.dp))
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                // Judul halaman
                Text(
                    text = "Tambahkan Task",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    ),
                    color = Color.Black
                )

                // Input Judul
                TaskInputField(
                    label = "Judul",
                    value = taskName,
                    onValueChange = { taskName = it }
                )

                // Input Deskripsi
                TaskInputField(
                    label = "Deskripsi",
                    value = taskDescription,
                    onValueChange = { taskDescription = it },
                    height = 100.dp
                )

                // Input Penanggung Jawab
                TaskInputField(
                    label = "Penanggung Jawab",
                    value = taskAssignee,
                    onValueChange = { taskAssignee = it }
                )

                // Input Deadline Tanggal
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Deadline: ${taskDeadlineDate?.let { SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(it) } ?: "Pilih Tanggal"}",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.weight(1f)
                    )
                    Button(
                        onClick = { showDatePicker = true },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF469C8F))
                    ) {
                        Text("Pilih Tanggal", color = Color.White)
                    }
                }

                // Input Deadline Waktu
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Waktu: $taskDeadlineTime",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.weight(1f)
                    )
                    Button(
                        onClick = { showTimePicker = true },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF469C8F))
                    ) {
                        Text("Pilih Waktu", color = Color.White)
                    }
                }

                // Tombol Save Task
                Button(
                    onClick = {
                        // Logika untuk menyimpan tugas
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    shape = RoundedCornerShape(50)
                ) {
                    Text("Save Task", color = Color.Black, style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }

    // DatePicker Dialog
    if (showDatePicker) {
        val calendar = Calendar.getInstance()
        DatePickerDialog(
            navController.context,
            { _, year, month, dayOfMonth ->
                taskDeadlineDate = Calendar.getInstance().apply {
                    set(year, month, dayOfMonth)
                }.time
                showDatePicker = false
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    // TimePicker Dialog
    if (showTimePicker) {
        val calendar = Calendar.getInstance()
        TimePickerDialog(
            navController.context,
            { _, hourOfDay, minute ->
                taskDeadlineTime = String.format("%02d:%02d", hourOfDay, minute)
                showTimePicker = false
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        ).show()
    }
}

// Fungsi untuk membuat input form dengan background hijau muda
@Composable
fun TaskInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    height: Dp = 56.dp,
    isClickable: Boolean = false
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .background(Color(0xFFE0F7FA), RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        if (isClickable) {
            // Jika input bisa diklik (seperti untuk tanggal), buat field bisa di-click
            Text(text = value, color = Color.Gray, style = MaterialTheme.typography.bodyMedium)
        } else {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                textStyle = MaterialTheme.typography.bodyMedium,
                singleLine = true,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
